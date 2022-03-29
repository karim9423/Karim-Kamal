package api.payloads;


import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class Category {
    private String id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
    private List<Category> subCategories;
    private List<Category> categoryPath;

    public List<Category> getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(List<Category> categoryPath) {
        this.categoryPath = categoryPath;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
