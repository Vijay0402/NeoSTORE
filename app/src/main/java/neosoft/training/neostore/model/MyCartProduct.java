package neosoft.training.neostore.model;

/**
 * Created by webwerks on 15/11/17.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyCartProduct {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("product_category")
    @Expose
    private String productCategory;
    @SerializedName("product_images")
    @Expose
    private String productImages;
    @SerializedName("sub_total")
    @Expose
    private Integer subTotal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductImages() {
        return productImages;
    }

    public void setProductImages(String productImages) {
        this.productImages = productImages;
    }

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "MyCartProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", productCategory='" + productCategory + '\'' +
                ", productImages='" + productImages + '\'' +
                ", subTotal=" + subTotal +
                '}';
    }
}