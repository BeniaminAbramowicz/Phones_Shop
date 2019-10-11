package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.enums.OrderStatus;

import java.util.List;

public interface AdminService {

    List<Order> getOrdersToManage();
    void changeOrderStatus(int orderId, OrderStatus status);
}
