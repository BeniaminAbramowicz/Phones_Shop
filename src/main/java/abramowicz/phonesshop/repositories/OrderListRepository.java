package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public interface OrderListRepository extends JpaRepository<OrderList, Integer> {

    List<OrderList> findAllByOrder_OrderId(int orderId);

    OrderList getOrderListByOrderListId(int orderListId);

    OrderList getOrderListByProduct_ProductId(int productId);

    void deleteOrderListByOrderListId(int orderListId);

    @Modifying
    @Query(value = "UPDATE OrderList o SET o.quantity=o.quantity - :quantity WHERE o.orderListId=:orderListId")
    void subQuantity(@Param("quantity") int quantity, @Param("orderListId") int orderListId);

    @Modifying
    @Query(value = "UPDATE OrderList o SET o.price=o.price - :price WHERE o.orderListId=:orderListId")
    void subPrice(@Param("price") BigDecimal price, @Param("orderListId") int orderListId);

    @Modifying
    @Query(value = "UPDATE OrderList o SET o.price=o.price + :price, o.quantity=o.quantity + :quantity WHERE o.orderListId=:orderListId")
    void addExistingItem(@Param("price") BigDecimal price, @Param("quantity") int quantity, @Param("orderListId") int orderListId);
}
