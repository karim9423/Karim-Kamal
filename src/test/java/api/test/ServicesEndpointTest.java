package api.test;

import api.endpoints.CategoriesEndpoint;
import api.endpoints.ServicesEndpoint;
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

public class ServicesEndpointTest {

    ServicesEndpoint servicesEndpoint;
    ObjectMapper mapper;
    int count;
    @SuppressWarnings("unchecked")
    @BeforeClass
    public void setup() {
        ExtentTestManager.initializeReport(ServicesEndpointTest.class.getName());
        servicesEndpoint = new ServicesEndpoint();
        mapper = new ObjectMapper();
    }


    @Test(priority = 0,description = "Test Get all Serivces")
    public void GetAllServices(Method method) {
        ExtentTestManager.startTest(method.getName(), "view the list of available Services");
        Response response = servicesEndpoint.getAllServices();
        Assert.assertEquals(response.getStatusCode(), 200);
        count =response.jsonPath().getInt("total");
    }

    @Test(description = "Test Get Service By id")
    public void GetServiceByID(Method method) {
        ExtentTestManager.startTest(method.getName(), "retrieve a single Serivce By ID");
        Response response = servicesEndpoint.getServiceByID(1);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(description = "Test Get Services names")
    public void GetAllServiceNames(Method method) {
        ExtentTestManager.startTest(method.getName(), "retrive list of Services Names");
        Response response = servicesEndpoint.getAllServicesNames();
        Assert.assertEquals(response.getStatusCode(), 200);
    }


    @Test(priority = 3,description = "Test Delete Service By ID")
    public void DeleteServiceByID(Method method) {
        ExtentTestManager.startTest(method.getName(), "Delete Single Service By ID");
        Response response = servicesEndpoint.deleteService(count);
        Assert.assertEquals(response.getStatusCode(), 200);
        Category category = response.as(Category.class);
        Assert.assertEquals(category.getName(), "Test Category");
        Assert.assertEquals(Integer.parseInt(category.getId()), count);
    }

    @Test(priority = 1,description = "Test Create new Service")
    public void CreateNewService(Method method) throws JsonProcessingException, ParseException {
        ExtentTestManager.startTest(method.getName(), "Create New Service");
        JSONObject params = new JSONObject();
        params.put("name","Test Category");
        Response response = servicesEndpoint.createService(params);
        Assert.assertEquals(response.getStatusCode(), 201);
        Category category = response.as(Category.class);
        Assert.assertEquals(category.getName(), "Test Category");
    }

    @Test(description = "Test update Service By ID")
    public void updateService(Method method) throws JsonProcessingException, ParseException {
        ExtentTestManager.startTest(method.getName(), "update Service By ID");
        JSONObject params = new JSONObject();
        params.put("id","pcmcat748300579354");
        params.put("name","Test Category");
        Response response = servicesEndpoint.updateService(4,params);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @AfterClass
    public void generateReport() {
        try {
            File report = new File(Constants.USER_DIR + File.separator + "report" + File.separator + ServicesEndpointTest.class.getName() + ".html");
            if (report.exists())
                Desktop.getDesktop().open(report);
        }
        catch (IOException e) {
        }
    }
}