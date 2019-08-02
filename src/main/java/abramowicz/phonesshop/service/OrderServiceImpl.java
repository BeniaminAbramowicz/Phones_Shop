package abramowicz.phonesshop.service;

import abramowicz.phonesshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){this.orderRepository = orderRepository;}

    @Override
    public void createOrder(int userId) {
        orderRepository.createOrder(userId);
    }
}
