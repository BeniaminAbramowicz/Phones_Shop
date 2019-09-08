package abramowicz.phonesshop.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    @Size(max = 45)
    private String status;

    @Column(name = "total_price")
    @NotNull
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(){
        //for JPA
    }
}
