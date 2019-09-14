package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Integer> {

    @Query(value = "SELECT o.quantity, o.price, o.order_list_id, o.order_id, o.product_id, p.name FROM `order_list` o INNER JOIN `product` p ON o.product_id = p.product_id WHERE o.order_id=:orderId", nativeQuery = true)
    List<OrderList> displayOrderList(int orderId);
}
