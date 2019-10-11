package abramowicz.phonesshop.entities;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
@Builder
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "brand")
    @NotNull
    private String brand;

    @Column(name = "model")
    @NotNull
    private String model;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @Column(name = "items_number")
    @NotNull
    private int itemsNumber;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "picture")
    private String picture;

    @Column(name = "is_accessory")
    private Boolean isAccessory;

    public Product(){
        //for JPA
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getItemsNumber() {
        return itemsNumber;
    }

    public void setItemsNumber(int itemsNumber) {
        this.itemsNumber = itemsNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getAccessory() {
        return isAccessory;
    }

    public void setAccessory(Boolean accessory) {
        isAccessory = accessory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
