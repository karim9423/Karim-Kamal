package api.payloads;

import java.util.Date;
import java.util.List;

public class Product {

    private long id;
    private String name;
    private String type;
    private double price;
    private double shipping;
    private String upc;
    private String description;
    private String manufacturer;
    private String model;
    private String url;
    private String image;
    private Date createdAt;
    private Date updatedAt;
    private List<Category> categories;

    public Product(){}
    public Product(String name, String type, double price, double shipping, String upc, String description, String manufacturer, String model, String url, String image) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.shipping = shipping;
        this.upc = upc;
        this.description = description;
        this.manufacturer = manufacturer;
        this.model = model;
        this.url = url;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
