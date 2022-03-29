package api.endpoints;

import api.RestClient;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class productsEndpoint {

    RestClient restClient;

    public productsEndpoint() {
        restClient = new RestClient(Routes.base_uri);
    }

    public Response getAllProducts() {
        restClient.httpGet(Routes.products_uri);
        return restClient.getResponse();
    }

    public Response getProductByID(long id) {
        restClient.httpGet(Routes.products_uri+"/"+id);
        return restClient.getResponse();
    }

    public Response getAllProductsLimited(int limit) {
        restClient.httpGet(Routes.products_uri,"$limit",String.valueOf(limit));
        return restClient.getResponse();
    }

    public Response getAllProductsSortedByPrice(int limit) {
        restClient.httpGet(Routes.products_uri,"$sort[price]",String.valueOf(limit));
        return restClient.getResponse();
    }

    public Response getAllProductsByType(String type) {
        restClient.httpGet(Routes.products_uri,"type",type);
        return restClient.getResponse();
    }

    public Response getAllProductsByCategoryName(String name) {
        restClient.httpGet(Routes.products_uri,"category.name",name);
        return restClient.getResponse();
    }

    public Response createProduct(JSONObject body) {
        restClient.httpPost(Routes.products_uri,body);
        return restClient.getResponse();
    }

    public Response deleteProductByID(long id) {
        restClient.httpDelete(Routes.products_uri+"/"+id);
        return restClient.getResponse();
    }

    public Response updateProduct(long id,JSONObject body) {
        restClient.httpPatch(Routes.products_uri+"/"+id,body);
        return restClient.getResponse();
    }
}
