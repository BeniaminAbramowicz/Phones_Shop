package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.OrderList;

import java.util.List;

public interface OrderService {

    void createOrder(int userId);
    List<Order> displayOrders(int userId);
    List<OrderList> displayOrderList(int orderId);
}
