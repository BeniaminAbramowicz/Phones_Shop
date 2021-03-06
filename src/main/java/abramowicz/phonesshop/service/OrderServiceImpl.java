package abramowicz.phonesshop.service;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.OrderList;
import abramowicz.phonesshop.repositories.OrderListRepository;
import abramowicz.phonesshop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderListRepository orderListRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderListRepository orderListRepository) {
        this.orderRepository = orderRepository;
        this.orderListRepository = orderListRepository;
    }

    @Override
    public void createOrder(int userId) {
        orderRepository.createOrder(userId);
    }

    @Override
    public List<Order> displayOrders(int userId) {
        return orderRepository.getOrdersByUserUserId(userId);
    }

    @Override
    public List<OrderList> displayOrderList(int orderId) {
        return orderListRepository.findAllByOrder_OrderId(orderId);
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderRepository.getOrderByOrderId(orderId);
    }

    @Override
    public Order getOpenOrder(String email) {
        return orderRepository.getOpenOrder(email);
    }

    @Override
    public void sumTotalPrice(int orderId) {
        orderRepository.sumTotalPrice(orderId);
    }

    @Override
    public void removeFromOrder(int orderListId) {
        orderListRepository.deleteOrderListByOrderListId(orderListId);
    }

    @Override
    public OrderList getOrderListById(int orderListId) {
        return orderListRepository.getOrderListByOrderListId(orderListId);
    }

    @Override
    public void subItemsInOrder(int quantity, int orderListId) {
        orderListRepository.subQuantity(quantity, orderListId);
    }

    @Override
    public void subPriceInOrder(BigDecimal price, int orderListId) {
        orderListRepository.subPrice(price, orderListId);
    }

    @Override
    public void addExistingItem(BigDecimal price, int quantity, int orderListId) {
        orderListRepository.addExistingItem(price, quantity, orderListId);
    }

    @Override
    public OrderList getOrderListByProductIdOrderId(int productId, int orderId) {
        return orderListRepository.getOrderListByProduct_ProductIdAndOrder_OrderId(productId, orderId);
    }

    @Override
    public void closeOrder(int orderId) {
        orderRepository.closeOrder(orderId);
    }

    @Override
    public void resetOrderPrice(int orderId) {
        orderRepository.resetOrderPrice(orderId);
    }

    @Override
    public void addItemToOrder(OrderList orderList) {
        orderListRepository.save(orderList);
    }
}
