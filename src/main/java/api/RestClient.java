package api;

import java.util.Map;

import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import report.ExtentTestManager;

public class RestClient {

    RequestSpecification httpRequest;
    Response response;

    public RestClient(String baseURI) {
        RestAssured.baseURI = baseURI;
        httpRequest = RestAssured.given();
    }

    public void setBasicAuthentication(String userName, String password) {
        httpRequest.auth().basic(userName, password);
    }

    public void setOathAuthentication(String accessToken) {
        httpRequest.auth().oauth2(accessToken);
    }

    public void setHeaders(Map<String, String> headers) {
        httpRequest.headers(headers);
    }

    public void httpGet(String resource) {
        response = httpRequest.accept(ContentType.JSON).request(Method.GET, resource);
        ExtentTestManager.getTest().log(Status.INFO,"Get:"+resource+":"+response.getStatusCode()+" Response:"+getResponseBody());
    }

    public void httpGet(String resource, Map<String, String> queryParams) {
        response = httpRequest.queryParams(queryParams).request(Method.GET, resource);
    }

    public void httpGet(String resource,String key,String value) {
        response = httpRequest.queryParams(key,value).request(Method.GET, resource);
        ExtentTestManager.getTest().log(Status.INFO,"Get:"+resource+":"+response.getStatusCode()+" Response:"+getResponseBody());
    }

    public void httpPost(String resource) {
        response = httpRequest.request(Method.POST, resource);
    }

    public void httpPost(String resource,JSONObject body) {
        response = httpRequest.contentType(ContentType.JSON).accept(ContentType.JSON).body(body).request(Method.POST, resource);
        ExtentTestManager.getTest().log(Status.INFO,"Post:"+resource+":"+response.getStatusCode()+" Response:"+getResponseBody());
    }

    public void httpPut(String resource) {
        response = httpRequest.request(Method.PUT, resource);
    }

    public void httpPatch(String resource,JSONObject body) {
        response = httpRequest.body(body).request(Method.PATCH, resource);
        ExtentTestManager.getTest().log(Status.INFO,"Patch:"+resource+":"+response.getStatusCode()+" Response:"+getResponseBody());
    }
    public void httpDelete(String resource) {
        response = httpRequest.accept(ContentType.JSON).request(Method.DELETE, resource);
        ExtentTestManager.getTest().log(Status.INFO,"Delete:"+resource+":"+response.getStatusCode()+" Response:"+getResponseBody());
    }

    public int getResponseHttpStatus() {
        return response.getStatusCode();
    }

    public String getResponseBody() {
        return response.getBody().asString();
    }

    public Response getResponse() {
        response.then().log().all();
        Response res = response;
        return res;
    }

    public String getResponseHeader(String key) {
        return response.getHeader(key);
    }

    public String getResponseJsonValue(String key) {

        return response.getBody().jsonPath().getString(key);
    }
}