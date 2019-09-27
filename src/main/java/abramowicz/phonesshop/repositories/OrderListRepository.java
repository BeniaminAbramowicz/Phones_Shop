package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Integer> {

    @Query(value = "SELECT * FROM `order_list` o WHERE o.order_id=:orderId", nativeQuery = true)
    List<OrderList> displayOrderList(int orderId);
    //List<OrderList> findAllByOrder_OrderId(int orderId);

    @Modifying
    @Query(value= "INSERT INTO `order_list`(order_list_id, quantity, price, order_id, product_id) VALUES (NULL,?,?,?,?)", nativeQuery = true)
    void addItem(@Param("quantity") int quantity, @Param("price")BigDecimal price, @Param("order_id") int orderId, @Param("product_id") int productId);

    OrderList getOrderListByOrderListId(int orderListId);

    @Modifying
    @Query(value = "DELETE FROM `order_list` WHERE order_list_id=:orderListId", nativeQuery = true)
    void deleteItemFromOrder(int orderListId);

    @Modifying
    @Query(value = "UPDATE `order_list` o SET o.quantity=o.quantity - :quantity WHERE o.order_list_id=:orderListId", nativeQuery = true)
    void subQuantity(@Param("quantity") int quantity, @Param("orderListId") int orderListId);
}
