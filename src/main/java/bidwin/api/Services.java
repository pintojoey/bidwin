package bidwin.api;


import bidwin.database.*;
import bidwin.models.Bid;
import bidwin.models.Order;
import bidwin.models.Product;
import cz.zcu.kiv.server.sqlite.UserDoesNotExistException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.glassfish.jersey.media.multipart.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/***********************************************************************************************************************
 *
 * This file is part of the Workflow Designer project

 * ==========================================
 *
 * Copyright (C) 2018 by University of West Bohemia (http://www.zcu.cz/en/)
 *
 ***********************************************************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 ***********************************************************************************************************************
 *
 * Workflow, 2018/22/05 10:02 Joey Pinto
 *
 * This file hosts the embedded primary APIs for the Workflow Designer Server.
 **********************************************************************************************************************/


@Path("/services")
public class Services {
    private static Log logger = LogFactory.getLog(Services.class);

    public Services() {
    }

    @Context
    private UriInfo context;

    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts(@Context HttpHeaders httpHeaders)  {
        JSONArray productsArray;
        List<Product> products = QueryProducts.getAllProducts();
        productsArray=new JSONArray();
        for(Product product:products){
            productsArray.put(product.toJSON());
        }
        return Response.status(200)
                .entity(productsArray.toString(4)).build();
    }

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders(@Context HttpHeaders httpHeaders)  {
        JSONArray ordersArray;
        List<Order> products = QueryOrder.getAllOpenOrders();
        ordersArray=new JSONArray();
        for(Order order:products){
            ordersArray.put(order.toJSON());
        }
        return Response.status(200)
                .entity(ordersArray.toString(4)).build();
    }

    @GET
    @Path("/orders/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders(@Context HttpHeaders httpHeaders,@PathParam("orderId")long orderId)  {
        Order order = QueryOrder.getOrder(orderId);
        return Response.status(200)
                .entity(order.toJSON().toString(4)).build();
    }

    @GET
    @Path("/bids/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWinningBid(@PathParam("orderId")long orderId,@Context HttpHeaders httpHeaders)  {
        Bid bid=QueryBid.getWinningBid(orderId);
        Order order = QueryOrder.getOrder(orderId);
        {
            long closeTime=(order.getDuration()+order.getTimestamp().getTime());
            if(new Date().getTime()>(closeTime)){
                QueryOrder.updateOrder(orderId,1);//Status closed
            }
        }
        if(bid!=null)
            return Response.status(200)
                .entity(bid.toJSON().toString(4)).build();
        else return Response.status(200).entity(new JSONObject().toString()).build();
    }

    @POST
    @Path("/bids/{orderId}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeBid(
            @PathParam("orderId")long orderId,
            @FormDataParam("retailerEmail") String retailerEmail,
            @FormDataParam("price") Double price){

        Bid bid = new Bid();
        bid.setOrderId(orderId);
        bid.setPrice(price);
        bid.setRetailerEmail(retailerEmail);

        try {
            QueryBid.addBid(bid);
            Order order = QueryOrder.getOrder(orderId);
            if(order.getBuyNow()>=price){
                //wins
                QueryOrder.updateOrder(orderId,1);//Status closed
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500).entity("Database Error").build();
        }

        return Response.status(200)
                .entity(bid.getId()).build();
    }

    @POST
    @Path("/orders")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response schedule(
            @FormDataParam("productId") int productId,
            @FormDataParam("email") String email,
            @FormDataParam("startingBid") Double startingBid,
            @FormDataParam("buyNowPrice") Double buyNow,
            @FormDataParam("minimumRating") int minRating,
            @FormDataParam("duration") String duration)  {


        Order order = new Order();
        order.setProductId(productId);
        order.setMarketId(1);
        order.setBuyNow(buyNow);
        order.setStartBid(startingBid);
        order.setMinRating(minRating);
        try {
            order.setDuration(new SimpleDateFormat("HH:mm").parse(duration).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            order.setCustomerId(QueryCustomers.getUserByEmail(email).getId());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (UserDoesNotExistException e) {
            logger.error("user does not exist",e);
            return Response.status(500).entity("Database Error").build();
        }


        try {
            QueryOrder.addOrder(order);
        } catch (SQLException e) {
            logger.error(e);
            return Response.status(500).entity("Database Error").build();
        }
        return Response.status(200)
                .entity(order.getId()).build();
    }
    /*

    *//**
     * Returns text response to indicate job scheduling success
     *
     * @return job ID
     *//*


    *//**
     * Removes completed jobs from the joblist
     *
     * @return job ID
     *//*
    @DELETE
    @Path("/schedule")
    @Produces(MediaType.TEXT_PLAIN)
    public Response clearSchedule(@Context HttpHeaders httpHeaders)  {
        String email = httpHeaders.getHeaderString("email");
        String token = httpHeaders.getHeaderString("token");
        if(Conf.getConf().getAuthEnabled()){
            try {
                if(email==null||email.equals("undefined")
                        ||token==null||token.equals("undefined")
                        ||!QueryCustomers.checkAuthorized(email,token))
                    return Response.status(403).entity("Unauthorized").build();
            } catch (SQLException e) {
                logger.error(e);
                return Response.status(500).entity("Database Error").build();
            }
        }
        else{
            email="guest@guest.com";
        }


        try {
             Manager.getInstance().clearJobs(email);
        } catch (SQLException e) {
            logger.error(e);
            return Response.status(500).entity("Database Error").build();
        }
        return Response.status(200)
                .entity("Schedule Cleared").build();
    }

    *//**
     * Returns text response to indicate job scheduling success
     *
     * @return job ID
     *//*
    @GET
    @Path("/jobs/{jobId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getJobById(@PathParam("jobId")long jobId)  {

        JSONObject job = null;
        try {
            job = Manager.getInstance().getJob(jobId);
        } catch (SQLException e) {
            logger.error(e);
            return Response.status(500).entity("Database Error").build();
        }
        return Response.status(200)
                .entity(job.toString(4)).build();
    }

    private File getSingleJarFile(FormDataMultiPart multiPart) throws IOException {
        Map<String, List<FormDataBodyPart>> map = multiPart.getFields();

        FormDataBodyPart filePart = null;
        for (Map.Entry<String, List<FormDataBodyPart>> entry : map.entrySet()) {

            for (FormDataBodyPart part : entry.getValue()) {
                if(part.getName().equals("file")){
                    filePart = part;
                    break;
                }

            }
        }
        BodyPart part=filePart;
        InputStream is = part.getEntityAs(InputStream.class);
        ContentDisposition meta = part.getContentDisposition();

        String uploadedFileLocation = UPLOAD_FOLDER + meta.getFileName();
        File outputFile = saveToFile(is, uploadedFileLocation);
        return outputFile;
    }

    private String getSingleJarFileName(FormDataMultiPart multiPart) {
        Map<String, List<FormDataBodyPart>> map = multiPart.getFields();

        for (Map.Entry<String, List<FormDataBodyPart>> entry : map.entrySet()) {

            for (FormDataBodyPart part : entry.getValue()) {
                if(part.getName().equals("file")){
                    return part.getContentDisposition().getFileName();
                }

            }
        }
       return null;
    }

    *//**
     * Utility method to save InputStream data to target location/file
     *  @param inStream
     *            - InputStream to be saved
     * @param target
     *//*
    private File saveToFile(InputStream inStream, String target)
            throws IOException {
        OutputStream out = null;
        int read = 0;
        byte[] bytes = new byte[1024];
        File file = new File(target);
        file.delete();
            out = new FileOutputStream(file);
            while ((read = inStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            return file;

    }

    *//**
     * Creates a folder to desired location if it not already exists
     *
     * @param dirName
     *            - full path to the folder
     * @throws SecurityException
     *             - in case you don't have permission to create the folder
     *//*
    public static void createFolderIfNotExists(String dirName)
            throws SecurityException {
        File theDir = new File(dirName);
        if (!theDir.exists()) {
            theDir.mkdirs();
            logger.info(theDir.getAbsolutePath()+ "Created");
        }
        else
            logger.info(theDir.getAbsolutePath()+ "Exists");
    }


    public static ClassLoader initializeClassLoader(Set<String>modules, Map<Class,String>moduleSource) throws IOException {


        int files = modules.size();
        URL[]urls=new URL[files];
        File[]outputFiles=new File[files];

        Iterator<String> iterator = modules.iterator();
        for(int i=0; i<files;i++){
            String module=iterator.next();
            String uploadedFileLocation = UPLOAD_FOLDER + module.split(":")[0];
                outputFiles[i] = new File(uploadedFileLocation);
                urls[i]=outputFiles[i].toURI().toURL();
        }
        URLClassLoader child = new URLClassLoader(urls, Services.class.getClassLoader());

        iterator = modules.iterator();


        for(int i=0;i<files;i++){
            String module=iterator.next();
            String packageName = module.split(":")[1];
            JarFile jarFile = new JarFile(outputFiles[i]);
            Enumeration e = jarFile.entries();

            while (e.hasMoreElements()) {
                JarEntry je = (JarEntry) e.nextElement();
                if(je.isDirectory() || !je.getName().endsWith(".class")){
                    continue;
                }
                if(packageName!=null&&!je.getName().startsWith(packageName.replace('.','/')))
                    continue;
                String className = je.getName().substring(0,je.getName().length()-6);
                className = className.replace('/', '.');
                try {
                    Class loadedClass = child.loadClass(className);
                    moduleSource.put(loadedClass,module);
                }
                catch (Exception|Error e1){
                    logger.error(e1.getMessage());
                }
            }

        }
        Thread.currentThread().setContextClassLoader(child);
        return child;
    }

    public static JSONArray executeJar(ClassLoader child,JSONObject workflow, Map<Class,String>moduleSource, String workflowOutputFile)throws Exception{
        Class classToLoad = Class.forName("cz.zcu.kiv.WorkflowDesigner.Workflow", true, child);
        Thread.currentThread().setContextClassLoader(child);

        Constructor<?> ctor=classToLoad.getConstructor(ClassLoader.class,Map.class,String.class,String.class);
        Method method = classToLoad.getDeclaredMethod("execute",JSONObject.class,String.class,String.class);
        Object instance = ctor.newInstance(child,moduleSource,UPLOAD_FOLDER,WORK_FOLDER);
        JSONArray result = (JSONArray)method.invoke(instance,workflow,GENERATED_FILES_FOLDER,workflowOutputFile);
        return result;
    }
    private ClassLoader initializeJarClassLoader(String packageName,File outputFile) throws IOException {



            URL url = outputFile.toURL();

            URLClassLoader child = new URLClassLoader(new URL[]{url}, this.getClass().getClassLoader());

            addJarToClassLoader(outputFile,packageName,child);

            Thread.currentThread().setContextClassLoader(child);
            return child;

    }

    public static void addJarToClassLoader(File outputFile, String packageName, ClassLoader child) throws IOException {
        JarFile jarFile = new JarFile(outputFile);
        Enumeration e = jarFile.entries();

        while (e.hasMoreElements()) {
            JarEntry je = (JarEntry) e.nextElement();
            if(je.isDirectory() || !je.getName().endsWith(".class")||(packageName!=null&&!je.getName().startsWith(packageName.replace('.','/')))){
                continue;
            }
            String className = je.getName().substring(0,je.getName().length()-6);
            className = className.replace('/', '.');
            try {
                child.loadClass(className);
            }
            catch (Exception|Error e1){
                logger.error(e1.getMessage());
            }
        }
    }*/

}
