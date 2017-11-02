package neosoft.training.neostore.model;

import com.google.gson.annotations.SerializedName;

import java.net.URL;

import neosoft.training.neostore.common.base.BaseModel;


/**
 * Created by webwerks1 on 26/10/17.
 */

public class ProductListModel extends BaseModel {

    @SerializedName("id")
    private int id;
    @SerializedName("product_category_id")
    private int product_category_id;
    @SerializedName("cost")
    private int cost;
    @SerializedName("rating")
    private int rating;
    @SerializedName("view_count")
    private int view_count;
    @SerializedName("created")
    private int created;
    @SerializedName("modified")
    private int modified;
    @SerializedName("name")
    private String name;
    @SerializedName("producer")
    private String producer;
    @SerializedName("description")
    private String description;

    @SerializedName("product_images")
    private String product_images;

    public String getProduct_images() {
        return product_images;
    }

    public void setProduct_images(String product_images) {
        this.product_images = product_images;
    }
    @SerializedName("product")
    public Product ppid;

    class Product{

      @SerializedName("id")
      private int id;
      @SerializedName("product_id")
      private int product_id;
      @SerializedName("image")
      private int image;
      @SerializedName("created")
      private int created;
      @SerializedName("modified")
      private int modified;

        @Override
        public String toString() {
            return "Pro{" +
                    "id=" + id +
                    ", product_id=" + product_id +
                    ", image=" + image +
                    ", created=" + created +
                    ", modified=" + modified +
                    '}';
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public int getImage() {
            return image;
        }

        public void setImage(int image) {
            this.image = image;
        }

        public int getCreated() {
            return created;
        }

        public void setCreated(int created) {
            this.created = created;
        }

        public int getModified() {
            return modified;
        }

        public void setModified(int modified) {
            this.modified = modified;
        }

    }

    @Override
    public String toString() {
        return "ProductListModel{" +
                "id=" + id +
                ", product_category_id=" + product_category_id +
                ", cost=" + cost +
                ", rating=" + rating +
                ", view_count=" + view_count +
                ", created=" + created +
                ", modified=" + modified +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", description='" + description + '\'' +
                '}';
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_category_id() {
        return product_category_id;
    }

    public void setProduct_category_id(int product_category_id) {
        this.product_category_id = product_category_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getView_count() {
        return view_count;
    }

    public void setView_count(int view_count) {
        this.view_count = view_count;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getModified() {
        return modified;
    }

    public void setModified(int modified) {
        this.modified = modified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
