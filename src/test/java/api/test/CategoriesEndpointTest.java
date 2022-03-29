package api.test;

import api.endpoints.CategoriesEndpoint;
import api.endpoints.productsEndpoint;
import api.payloads.Category;
import api.payloads.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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

public class CategoriesEndpointTest {

    CategoriesEndpoint categoriesEndpoint;
    ObjectMapper mapper;
    String genratedID;
    File schema = new File(Constants.USER_DIR+"Schemas"+File.separator+"schema.json");
    @SuppressWarnings("unchecked")
    @BeforeClass
    public void setup() {
        ExtentTestManager.initializeReport(CategoriesEndpointTest.class.getName());
        categoriesEndpoint = new CategoriesEndpoint();
        mapper = new ObjectMapper();
        genratedID=String.valueOf(new Random().nextInt(2000));

    }


    @Test(description = "Test Get all Categories")
    public void GetAllCategories(Method method) {
        ExtentTestManager.startTest(method.getName(), "view the list of available Categories");
        Response response = categoriesEndpoint.getAllCategories();
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(),Constants.CONTENTYPE);
    }

    @Test(description = "Test Get Category By id")
    public void GetCategoryByID(Method method) {
        ExtentTestManager.startTest(method.getName(), "retrieve a single Categories By ID");
        Response response = categoriesEndpoint.getCategoryByID("abcat0010000");
        Assert.assertEquals(response.getStatusCode(), 200);
        Category category = response.as(Category.class);
        Assert.assertEquals(category.getName(), "Gift Ideas");
        Assert.assertEquals(response.contentType(),Constants.CONTENTYPE);
    }

    @Test(description = "Test Get Category By Name")
    public void GetAllCategoriesByName(Method method) {
        ExtentTestManager.startTest(method.getName(), "retrive list of Categories of Name");
        Response response = categoriesEndpoint.getAllCategoryByName("Gift Ideas");
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(),Constants.CONTENTYPE);
    }


    @Test(description = "Test Delete Category By ID")
    public void DeleteCategoriesByID(Method method) {
        ExtentTestManager.startTest(method.getName(), "Delete Single Category By ID");
        Response response = categoriesEndpoint.deleteCategoryByID(genratedID);
        Assert.assertEquals(response.getStatusCode(), 200);
        Category category = response.as(Category.class);
        Assert.assertEquals(category.getName(), "Test Category");
        Assert.assertEquals(category.getId(), genratedID);
        Assert.assertEquals(response.contentType(),Constants.CONTENTYPE);
    }

    @Test(priority = 0,description = "Test Create new Category")
    public void CreateNewCategory(Method method) throws JsonProcessingException, ParseException {
        ExtentTestManager.startTest(method.getName(), "Create New Category");
        JSONObject params = new JSONObject();
        params.put("id",genratedID);
        params.put("name","Test Category");
        Response response = categoriesEndpoint.createCategory(params);
        Assert.assertEquals(response.getStatusCode(), 201);
        Category category = response.as(Category.class);
        Assert.assertEquals(category.getName(), "Test Category");
        Assert.assertEquals(category.getId(), genratedID);
        Assert.assertEquals(response.contentType(),Constants.CONTENTYPE);
    }

    @Test(description = "Test update Category By ID")
    public void updateCategory(Method method) throws JsonProcessingException, ParseException {
        ExtentTestManager.startTest(method.getName(), "update Category By ID");
        JSONObject params = new JSONObject();
        params.put("id","pcmcat748300579354");
        params.put("name","Test Category");
        Response response = categoriesEndpoint.updateCategory("pcmcat748300579354",params);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(),Constants.CONTENTYPE);
    }

    @AfterClass
    public void generateReport() {
        try {
            File report = new File(Constants.USER_DIR + File.separator + "report" + File.separator + CategoriesEndpointTest.class.getName() + ".html");
            if (report.exists())
                Desktop.getDesktop().open(report);
        }
        catch (IOException e) {
        }
    }
}