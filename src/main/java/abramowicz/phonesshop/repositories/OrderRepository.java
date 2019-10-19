package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order getOrderByOrderId(int orderId);

    @Modifying
    @Query(value = "INSERT INTO `order`(`order_id`, `status`, `user_id`, `total_price`) VALUES (NULL,'open',?,'0')", nativeQuery = true)
    void createOrder(@Param("userId") int userId);

    List<Order> getOrdersByUserUserId(int userId);

    @Modifying
    @Query(value = "UPDATE Order o SET o.status=:status WHERE o.orderId=:orderId")
    void changeOrderStatus(int orderId, OrderStatus status);

    @Query(value = "SELECT o FROM Order o WHERE status IN('closed','realized', 'cancelled')")
    List<Order> getOrdersForAdmin();

    @Query(value = "SELECT o FROM Order o INNER JOIN o.user WHERE email=:email AND o.status='open'")
    Order getOpenOrder(String email);

    @Modifying
    @Query(value = "UPDATE `order` o INNER JOIN (SELECT order_id, SUM(price) 'sumu' FROM order_list GROUP BY order_id) ol ON o.order_id=ol.order_id SET o.total_price = ol.sumu WHERE o.order_id=:orderId", nativeQuery = true)
    void sumTotalPrice(int orderId);

    @Modifying
    @Query(value = "UPDATE Order o SET o.totalPrice = 0 WHERE o.orderId=:orderId")
    void resetOrderPrice(int orderId);

    @Modifying
    @Query(value = "UPDATE Order o SET o.status = 'closed' WHERE o.orderId=:orderId")
    void closeOrder(int orderId);



}
