package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public void test(){orderRepository.test();}

    @Override
    public User displayUser(){
        return orderRepository.getUser();
    }
}
