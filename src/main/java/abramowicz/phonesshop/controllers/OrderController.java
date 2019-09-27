package abramowicz.phonesshop.controllers;


import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.OrderList;
import abramowicz.phonesshop.entities.Product;
import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.service.OrderService;
import abramowicz.phonesshop.service.ProductService;
import abramowicz.phonesshop.service.UserService;
import abramowicz.phonesshop.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;

    private final UserService userService;

    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, ProductService productService){this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping(value = "/orders/{userId}")
    public String displayOrders(Model model, @PathVariable("userId") int userId){
        List<Order> orders = orderService.displayOrders(userId);
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping(value = "/orders/orderdetails/{orderId}")
    public String displayOrderList(Model model, @PathVariable("orderId") int orderId){
        List<OrderList> orderListItems = orderService.displayOrderList(orderId);
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        model.addAttribute("orderListItems", orderListItems);
        return "orderlist";
    }

    @PostMapping(value = "/createorder")
    public String createOrder(@Param("userId") int userId){
        orderService.createOrder(userId);
        return "redirect:/orders";
    }

    @PostMapping(value = "/orderitem")
    public String orderItem(@Param("productId") int productId, @Param("orderId") int orderId, @Param("quantity") int quantity, @Param("price") BigDecimal price){
        price = price.multiply(new BigDecimal(quantity));
        orderService.addItem(quantity, price, orderId, productId);
        productService.subQuantity(quantity, productId);
        orderService.sumTotalPrice();
        return "redirect:/allproducts";
    }
    @PostMapping(value = "/orders/orderdetails/deleteitem/{orderListId}")
    public String deleteItem(@Param("orderListId") int orderListId, int quantity){
        OrderList orderList = orderService.getOrderListById(orderListId);
        if(quantity >= orderList.getQuantity()){
           orderService.removeFromOrder(orderListId);
        } else {
            orderService.subItemsInOrder(quantity, orderListId);
        }
        return "redirect:/orders";
    }

}
