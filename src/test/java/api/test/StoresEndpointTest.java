package api.test;

import api.endpoints.CategoriesEndpoint;
import api.endpoints.StoresEndpoint;
import api.payloads.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import report.ExtentTestManager;
import utilities.Constants;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Random;

public class StoresEndpointTest {
    StoresEndpoint storesEndpoint;
    ObjectMapper mapper;
    int count;
    @SuppressWarnings("unchecked")
    @BeforeClass
    public void setup() {
        ExtentTestManager.initializeReport(StoresEndpointTest.class.getName());
        storesEndpoint = new StoresEndpoint();
        mapper = new ObjectMapper();
    }


    @Test(description = "Test Get all Stores")
    public void GetAllStores(Method method) {
        ExtentTestManager.startTest(method.getName(), "view the list of available Stores");
        Response response = storesEndpoint.getAllStores();
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(description = "Test Get Store By id")
    public void GetStoreByID(Method method) {
        ExtentTestManager.startTest(method.getName(), "retrieve a single Categories By ID");
        Response response = storesEndpoint.getStoreByID(6);
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(description = "Test Get Store By Service")
    public void GetAllStoresByService(Method method) {
        ExtentTestManager.startTest(method.getName(), "retrive list of Stores by service");
        Response response = storesEndpoint.getAllStoresByService("Gift Ideas");
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(description = "Test Get Store Working hours")
    public void GetStoreWorkingHours(Method method) {
        ExtentTestManager.startTest(method.getName(), "retrive Store Working hour");
        Response response = storesEndpoint.getStoreWorkingTime(4);
        Assert.assertEquals(response.getStatusCode(), 200);
    }
    @Test(description = "Test Delete Store By ID")
    public void DeleteStoreByID(Method method) {
        ExtentTestManager.startTest(method.getName(), "Delete Single Store By ID");
        Response response = storesEndpoint.deleteStore(String.valueOf(count));
        Assert.assertEquals(response.getStatusCode(), 200);
        Category category = response.as(Category.class);
        Assert.assertEquals(category.getName(), "Test Category");
    }

    @Test(priority = 0,description = "Test Create new Store")
    public void CreateNewStore(Method method) throws JsonProcessingException, ParseException {
        ExtentTestManager.startTest(method.getName(), "Create New Stores");
        JSONObject params = new JSONObject();
        params.put("name","Test Category");
        Response response = storesEndpoint.createStore(params);
        Assert.assertEquals(response.getStatusCode(), 201);
        Category category = response.as(Category.class);
        Assert.assertEquals(category.getName(), "Test Category");
        count= Integer.parseInt(category.getId());
    }

    @Test(description = "Test update Store By ID")
    public void updateStore(Method method) throws JsonProcessingException, ParseException {
        ExtentTestManager.startTest(method.getName(), "update Store By ID");
        JSONObject params = new JSONObject();
        params.put("id","pcmcat748300579354");
        params.put("name","Test Category");
        Response response = storesEndpoint.updateStore(4,params);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(Constants.USER_DIR+"schema.json")));
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @AfterClass
    public void generateReport() {
        try {
            File report = new File(Constants.USER_DIR + File.separator + "report" + File.separator + StoresEndpointTest.class.getName() + ".html");
            if (report.exists())
                Desktop.getDesktop().open(report);
        }
        catch (IOException e) {
        }
    }
}
