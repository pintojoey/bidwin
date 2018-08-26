package test;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bidwin.EmbeddedServer;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.json.JSONObject;
import java.io.File;

public class RestClient extends Thread{


    /**
     *  Test if server is running
     * @return
     */
    public static String test() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:"+EmbeddedServer.SERVER_PORT).path("api").path("workflow");
        Response response = target.path("test")
                .request(MediaType.APPLICATION_JSON_TYPE).get();
        return response.readEntity(String.class);
    }

    /**
     *  Upload a JAR and return response
     * @param file
     * @return
     */
	public String upload(File file, String packageName)  {
		Client client = ClientBuilder.newClient();
        client.register(MultiPartFeature.class);
		WebTarget target = client.target("http://localhost:"+EmbeddedServer.SERVER_PORT).path("api").path("workflow");
        MultiPart multiPart = new MultiPart();
        FileDataBodyPart jarFile = new FileDataBodyPart("file", file,
                MediaType.APPLICATION_OCTET_STREAM_TYPE);
        FormDataBodyPart package_name = new FormDataBodyPart("package",packageName);
        // Add body part
        multiPart.bodyPart(jarFile);
        multiPart.bodyPart(package_name);

        Response response = target.path("upload").request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA));
		return response.readEntity(String.class);
	}

    public String execute(JSONObject workflowObject)  {
        Client client = ClientBuilder.newClient();
        client.register(MultiPartFeature.class);
        WebTarget target = client.target("http://localhost:"+EmbeddedServer.SERVER_PORT).path("api").path("workflow");
        MultiPart multiPart = new MultiPart();
        FormDataBodyPart workflow = new FormDataBodyPart("workflow",workflowObject.toString());
        // Add body part
        multiPart.bodyPart(workflow);
        Response response = target.path("execute").request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA));
        return response.readEntity(String.class);

    }
}