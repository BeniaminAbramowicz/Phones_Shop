package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.OrderList;
import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.repositories.OrderListRepository;
import abramowicz.phonesshop.repositories.OrderRepository;
import abramowicz.phonesshop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    private final OrderListRepository orderListRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderListRepository orderListRepository){
        this.orderRepository = orderRepository;
        this.orderListRepository = orderListRepository;
    }

    @Override
    public void createOrder(int userId) {
        orderRepository.createOrder(userId);
    }

    @Override
    public List<Order> displayOrders(int userId) {
        return orderRepository.displayOrders(userId);
    }

    @Override
    public List<OrderList> displayOrderList(int orderId) {
        return orderListRepository.displayOrderList(orderId);
        //return orderListRepository.findAllByOrder_OrderId(orderId);
    }

    @Override
    public Order getOpenOrder() {
        return orderRepository.getOpenOrder();
    }

    @Override
    public void addItem(int quantity, BigDecimal price, int orderId, int productId){
        orderListRepository.addItem(quantity, price, orderId, productId);
    }
}
