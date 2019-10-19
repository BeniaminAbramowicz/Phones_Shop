package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.OrderList;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    void createOrder(int userId);
    List<Order> displayOrders(int userId);
    List<OrderList> displayOrderList(int orderId);
    Order getOrderById(int orderId);
    Order getOpenOrder(String email);
    void addItemToOrder(OrderList orderList);
    void sumTotalPrice(int orderId);
    void removeFromOrder(int orderListId);
    OrderList getOrderListById(int orderListId);
    void subItemsInOrder(int quantity, int orderListId);
    void subPriceInOrder(BigDecimal price, int orderListId);
    void addExistingItem(BigDecimal price, int quantity, int orderListId);
    OrderList getOrderListByProductIdOrderId(int productId, int orderId);
    void closeOrder(int orderId);
    void resetOrderPrice(int orderId);
}
