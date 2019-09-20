package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.OrderList;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    void createOrder(int userId);
    List<Order> displayOrders(int userId);
    List<OrderList> displayOrderList(int orderId);
    Order getOpenOrder(String email);
    void addItem(int quantity, BigDecimal price, int orderId, int productId);
    void sumTotalPrice();
}
