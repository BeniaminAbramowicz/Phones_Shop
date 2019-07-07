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
}
