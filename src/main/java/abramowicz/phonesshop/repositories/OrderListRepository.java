package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Integer> {

    @Query(value = "SELECT * FROM `order_list` o WHERE o.order_id=:orderId", nativeQuery = true)
    List<OrderList> displayOrderList(int orderId);
    //List<OrderList> findAllByOrder_OrderId(int orderId);
}
