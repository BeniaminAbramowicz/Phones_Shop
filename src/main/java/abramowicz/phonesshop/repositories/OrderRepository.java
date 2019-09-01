package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Modifying
    @Query(value = "INSERT INTO `order`(`order_id`, `status`, `user_id`, `total_price`) VALUES (NULL,'open',?,'0')", nativeQuery = true)
    void createOrder(@Param("userId") int userId);

}
