package api.endpoints;

import api.RestClient;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class ServicesEndpoint {
    RestClient restClient;

    public ServicesEndpoint() {
        restClient = new RestClient(Routes.base_uri);
    }

    public Response getAllServices() {
        restClient.httpGet(Routes.services_uri);
        return restClient.getResponse();
    }

    public Response getServiceByID(long id) {
        restClient.httpGet(Routes.services_uri+"/"+id);
        return restClient.getResponse();
    }

    public Response getAllServicesNames() {
        restClient.httpGet(Routes.services_uri,"$select[]","name");
        return restClient.getResponse();
    }

    public Response createService(JSONObject body) {
        restClient.httpPost(Routes.services_uri,body);
        return restClient.getResponse();
    }

    public Response deleteService(int id) {
        restClient.httpDelete(Routes.services_uri+"/"+id);
        return restClient.getResponse();
    }

    public Response updateService(long id,JSONObject body) {
        restClient.httpPatch(Routes.services_uri+"/"+id,body);
        return restClient.getResponse();
    }
}
