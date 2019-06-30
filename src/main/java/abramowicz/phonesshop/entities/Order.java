package abramowicz.phonesshop.entities;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "role")
@Data
@Builder
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idOrder")
    private int idOrder;

    @Column(name = "status")
    @NotNull
    @Size(max = 45)
    private String status;

    @Column(name = "totalPrice")
    @NotNull
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "idUserFk")
    private User user;

    public Order(){

    }
}
