package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.repositories.OrderRepository;
import abramowicz.phonesshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){this.orderRepository = orderRepository;}

    @Override
    public void createOrder(int userId) {
        orderRepository.createOrder(userId);
    }

    @Override
    public List<Order> displayOrders() {
        return orderRepository.displayOrders();
    }
}
