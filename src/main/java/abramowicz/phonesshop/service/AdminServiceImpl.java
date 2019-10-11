package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.enums.OrderStatus;
import abramowicz.phonesshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    private final OrderRepository orderRepository;

    @Autowired
    public AdminServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getOrdersToManage() {
        return orderRepository.getOrdersForAdmin();
    }

    @Override
    public void changeOrderStatus(int orderId, OrderStatus status) {
        orderRepository.changeOrderStatus(orderId, status);
    }
}
