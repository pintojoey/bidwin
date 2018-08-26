package bidwin.base;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BaseCaller {

    public static String storeCustomer() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:3000/store").path("api");
        Response response = target.path("test")
                .request(MediaType.APPLICATION_JSON_TYPE).get();
        return response.readEntity(String.class);
    }

    public static String placeOrder() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:3000/placeOrder");
        Response response = target.path("test")
                .request(MediaType.APPLICATION_JSON_TYPE).get();
        return response.readEntity(String.class);
    }

    public static String placeBid() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:3000/placeBid");
        Response response = target.path("test")
                .request(MediaType.APPLICATION_JSON_TYPE).get();
        return response.readEntity(String.class);
    }

    public static String getBid() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:3000/getBid");
        Response response = target.path("test")
                .request(MediaType.APPLICATION_JSON_TYPE).get();
        return response.readEntity(String.class);
    }

    public static String getOrder() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:3000/getOrder");
        Response response = target.path("test")
                .request(MediaType.APPLICATION_JSON_TYPE).get();
        return response.readEntity(String.class);
    }
}
