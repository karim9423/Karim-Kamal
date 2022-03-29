package api.test;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import api.endpoints.productsEndpoint;
import api.payloads.Helper;
import api.payloads.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.JsonParser;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import report.ExtentManager;
import report.ExtentTestManager;
import utilities.Constants;

public class ProductsEndpointTest {

    productsEndpoint productEndpoint;
    ObjectMapper mapper;
    long createdProductID;
    String contentType="application/json; charset=utf-8";
    File schema = new File(Constants.USER_DIR+"Schemas"+File.separator+"ProductsSchema.json");
    @SuppressWarnings("unchecked")
    @BeforeClass
    public void setup() {
        ExtentTestManager.initializeReport(ProductsEndpointTest.class.getName());
        productEndpoint = new productsEndpoint();
        mapper = new ObjectMapper();
    }


    @Test(description = "Test Get all products")
    public void GetAllProducts(Method method) {
        ExtentTestManager.startTest(method.getName(), "view the list of available products");
        Response response = productEndpoint.getAllProducts();
        List<Product> products = response.jsonPath().getList("data",Product.class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(),contentType);
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(schema));
    }

    @Test(priority = 3,description = "Test Get product By id")
    public void GetProductByID(Method method) {
        ExtentTestManager.startTest(method.getName(), "retrieve a single Product By ID");
        Response response = productEndpoint.getProductByID(createdProductID);
        Product product = response.as(Product.class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(product.getName(), "Test Entity");
        Assert.assertEquals(response.contentType(),contentType);
    }

    @Test(description = "Test Get products limited")
    public void GetProductsLimited(Method method) {
        ExtentTestManager.startTest(method.getName(), "retrive list of products with limited count");
        Response response = productEndpoint.getAllProductsLimited(5);
        List<Product> products = response.jsonPath().getList("data",Product.class);
        Assert.assertEquals(products.size(), 5);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(),contentType);
    }

    @Test(description = "Test Get All products sorted")
    public void GetAllProductsSorted(Method method) {
        ExtentTestManager.startTest(method.getName(), "retrive list of products Sorted By price");
        Response response = productEndpoint.getAllProductsSortedByPrice(-1);
        List<Product> products = response.jsonPath().getList("data",Product.class);
        Assert.assertEquals(products.size(), 5);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(),contentType);
        Assert.assertEquals(Helper.isProductsSorted(products),true);
    }

    @Test(description = "Test Get All products By Type Software")
    public void GetAllProductsByTypeSoftware(Method method) {
        ExtentTestManager.startTest(method.getName(), "retrive list of products of Type Software");
        Response response = productEndpoint.getAllProductsByType("Software");
        List<Product> products = response.jsonPath().getList("data",Product.class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(),contentType);
        Assert.assertEquals(Helper.isProductsSameType(products,"Software"),true);
    }

    @Test(description = "Test Get All products By Category Name Coffe Pods")
    public void GetAllProductsByCategoryCoffePods(Method method) {
        ExtentTestManager.startTest(method.getName(), "retrive list of products of Type Software");
        Response response = productEndpoint.getAllProductsByCategoryName("Coffee Pods");
        List<Product> products = response.jsonPath().getList("data",Product.class);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(),contentType);
        Assert.assertEquals(Helper.isProductsSameCategory(products,"Coffee Pods"),true);
    }

    @Test(priority = 5,description = "Test Delete Product By ID")
    public void DeleteProductByID(Method method) {
        ExtentTestManager.startTest(method.getName(), "Delete Single Product By ID");
        Response response = productEndpoint.deleteProductByID(createdProductID);
        Assert.assertEquals(response.contentType(),contentType);
        Assert.assertEquals(response.contentType(),ContentType.JSON);
    }

    @Test(priority = 0,description = "Test Create new Product")
    public void CreateNewProduct(Method method) throws JsonProcessingException, ParseException {
        ExtentTestManager.startTest(method.getName(), "Create New Product");
        Product newProduct = new Product("TestEntity","Software",20,20,"none","this is a Test","Automated","12","url","image");
        JSONObject requestParams = (JSONObject) new JSONParser().parse(mapper.writeValueAsString(newProduct));
        requestParams.remove("id");
        requestParams.remove("createdAt");
        requestParams.remove("updatedAt");
        requestParams.remove("categories");
        Response response = productEndpoint.createProduct(requestParams);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.contentType(),contentType);
        Product product = response.as(Product.class);
        createdProductID = product.getId();
    }

    @Test(priority = 1,description = "Test update product By ID")
    public void updateProduct(Method method) throws JsonProcessingException, ParseException {
        ExtentTestManager.startTest(method.getName(), "update Product By ID");
        Product newProduct = new Product("TestEntity","Software",20,20,"none","this is a Test","Automated","12","url","image");
        JSONObject requestParams = (JSONObject) new JSONParser().parse(mapper.writeValueAsString(newProduct));
        requestParams.remove("id");
        requestParams.remove("createdAt");
        requestParams.remove("updatedAt");
        requestParams.remove("categories");
        Response response = productEndpoint.updateProduct(createdProductID,requestParams);
        Assert.assertEquals(response.contentType(),contentType);
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @AfterClass
    public void generateReport() {
        try {
            File report = new File(Constants.USER_DIR + File.separator + "report" + File.separator + ProductsEndpointTest.class.getName() + ".html");
            if (report.exists())
                Desktop.getDesktop().open(report);
        }
        catch (IOException e) {
        }
    }
}