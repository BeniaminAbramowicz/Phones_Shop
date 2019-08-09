package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "INSERT INTO order(order_id, status, user_id, total_price) VALUES(?,'open',?,'0')", nativeQuery = true)
    void createOrder(@Param("user_id") int userId);

}
