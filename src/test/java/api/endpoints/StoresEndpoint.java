package api.endpoints;

import api.RestClient;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class StoresEndpoint {
    RestClient restClient;

    public StoresEndpoint() {
        restClient = new RestClient(Routes.base_uri);
    }

    public Response getAllStores() {
        restClient.httpGet(Routes.stores_uri);
        return restClient.getResponse();
    }

    public Response getStoreByID(long id) {
        restClient.httpGet(Routes.stores_uri+"/"+id);
        return restClient.getResponse();
    }

    public Response getAllStoresByService(String serviceName) {
        restClient.httpGet(Routes.stores_uri,"service.name",serviceName);
        return restClient.getResponse();
    }

    public Response getStoreWorkingTime(long id) {
        restClient.httpGet(Routes.stores_uri+"/"+id,"$select[]","hours");
        return restClient.getResponse();
    }
    public Response createStore(JSONObject body) {
        restClient.httpPost(Routes.services_uri,body);
        return restClient.getResponse();
    }

    public Response deleteStore(String id) {
        restClient.httpDelete(Routes.services_uri+"/"+id);
        return restClient.getResponse();
    }

    public Response updateStore(long id,JSONObject body) {
        restClient.httpPatch(Routes.services_uri+"/"+id,body);
        return restClient.getResponse();
    }
}
