package abramowicz.phonesshop.repositories;

import abramowicz.phonesshop.entities.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Integer> {


}
