package abramowicz.phonesshop.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "orderitemlist")
@Data
@Builder
@AllArgsConstructor
public class OrderItemList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrderItemList")
    private int idOrderItemList;

    @Column(name = "quantity")
    @NotNull
    private int quantity;

    @Column(name = "price")
    @NotNull
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "idOrderFk")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "idProductFk")
    private Product product;
}
