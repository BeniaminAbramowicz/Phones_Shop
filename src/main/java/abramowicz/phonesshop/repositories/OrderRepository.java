package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Modifying
    @Query(value = "INSERT INTO `order`(`order_id`, `status`, `user_id`, `total_price`) VALUES (NULL,'open',?,'0')", nativeQuery = true)
    void createOrder(@Param("userId") int userId);

    @Query(value = "SELECT * FROM `order` WHERE user_id=:userId", nativeQuery = true)
    List<Order> displayOrders(int userId);

    @Query(value = "SELECT * FROM `order` o INNER JOIN `user` u ON u.user_id = o.user_id WHERE u.email=:email AND o.status='open' ", nativeQuery = true)
    Order getOpenOrder(String email);

    @Modifying
    @Query(value = "UPDATE `order` o INNER JOIN (SELECT order_id, SUM(price) 'sumu' FROM order_list GROUP BY order_id) ol ON o.order_id=ol.order_id SET o.total_price = ol.sumu", nativeQuery = true)
    void sumTotalPrice();



}
