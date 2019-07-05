package abramowicz.phonesshop.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "order_list")
@Data
@Builder
@AllArgsConstructor
public class OrderList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_list_id")
    private int orderListId;

    @Column(name = "quantity")
    @NotNull
    private int quantity;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderList() {
        //for JPA
    }
}
