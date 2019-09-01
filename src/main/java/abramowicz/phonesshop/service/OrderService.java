package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.Order;

import java.util.List;

public interface OrderService {

    void createOrder(int userId);
    List<Order> displayOrders();
}
