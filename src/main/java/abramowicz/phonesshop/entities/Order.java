package abramowicz.phonesshop.entities;

import abramowicz.phonesshop.entities.enums.OrderStatus;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "`order`")
@Data
@Builder
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "status")
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "total_price")
    @NotNull
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(){
        //for JPA
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
