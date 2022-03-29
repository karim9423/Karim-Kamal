package api.endpoints;

import api.RestClient;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class CategoriesEndpoint {
    RestClient restClient;

    public CategoriesEndpoint() {
        restClient = new RestClient(Routes.base_uri);
    }

    public Response getAllCategories() {
        restClient.httpGet(Routes.categories_uri);
        return restClient.getResponse();
    }

    public Response getCategoryByID(String id) {
        restClient.httpGet(Routes.categories_uri+"/"+id);
        return restClient.getResponse();
    }

    public Response getAllCategoryLimited(int limit) {
        restClient.httpGet(Routes.categories_uri,"$limit",String.valueOf(limit));
        return restClient.getResponse();
    }

    public Response getAllCategorySortedByPrice(int limit) {
        restClient.httpGet(Routes.categories_uri,"$sort[price]",String.valueOf(limit));
        return restClient.getResponse();
    }

    public Response getAllCategoryByName(String type) {
        restClient.httpGet(Routes.categories_uri,"name",type);
        return restClient.getResponse();
    }

    public Response getAllCategoryName(String name) {
        restClient.httpGet(Routes.categories_uri,"category.name",name);
        return restClient.getResponse();
    }

    public Response createCategory(JSONObject body) {
        restClient.httpPost(Routes.categories_uri,body);
        return restClient.getResponse();
    }

    public Response deleteCategoryByID(String id) {
        restClient.httpDelete(Routes.categories_uri+"/"+id);
        return restClient.getResponse();
    }

    public Response updateCategory(String id,JSONObject body) {
        restClient.httpPatch(Routes.categories_uri+"/"+id,body);
        return restClient.getResponse();
    }
}
