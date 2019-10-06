package abramowicz.phonesshop.controllers;


import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.OrderList;
import abramowicz.phonesshop.entities.Product;
import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.service.OrderService;
import abramowicz.phonesshop.service.ProductService;
import abramowicz.phonesshop.service.UserService;
import abramowicz.phonesshop.utilities.OtherUtils;
import abramowicz.phonesshop.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping(value = "/orders")
    public String displayOrders(Model model){
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        List<Order> orders = orderService.displayOrders(user.getUserId());
        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping(value = "/orders/orderdetails")
    public String displayOrderList(Model model, @Param("orderId") int orderId, RedirectAttributes redirectAttributes){
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        List<Order> orders = orderService.displayOrders(user.getUserId());
        if(OtherUtils.userHasOrder(orders, orderId) == false){
            redirectAttributes.addFlashAttribute("error", "Order with given id doesn't exists");
            return "redirect:/orders";
        } else {
            List<OrderList> orderListItems = orderService.displayOrderList(orderId);
            model.addAttribute("user", user);
            model.addAttribute("orderListItems", orderListItems);
            return "orderlist";
        }
    }

    @PostMapping(value = "/createorder")
    public String createOrder(@Param("userId") int userId){
        orderService.createOrder(userId);
        return "redirect:/orders";
    }

    @PostMapping(value = "/orderitem")
    public String orderItem(@Param("productId") int productId, @Param("orderId") int orderId, @Param("quantity") int quantity, @Param("price") BigDecimal price, RedirectAttributes redirectAttributes){
        List<OrderList> orderListItems = orderService.displayOrderList(orderId);
        int prQuantity = productService.getProduct(productId).getItemsNumber();
        if((OtherUtils.containsItem(orderListItems, productId)) && (prQuantity >= quantity) ){
            BigDecimal summedPrice = price.multiply(new BigDecimal(quantity));
            OrderList orderList = orderService.getOrderListByProductId(productId);
            orderService.addExistingItem(summedPrice, quantity, orderList.getOrderListId());
            productService.subQuantity(quantity, productId);
            orderService.sumTotalPrice();
        } else if((OtherUtils.containsItem(orderListItems, productId) == false) && (prQuantity >= quantity)){
            price.multiply(new BigDecimal(quantity));
            orderService.addItem(quantity, price, orderId, productId);
            productService.subQuantity(quantity, productId);
            orderService.sumTotalPrice();
        } else{
            redirectAttributes.addFlashAttribute("error", "There isn't enough items in the magazine to order that amount");
        }
        return "redirect:/allproducts";
    }
    @PostMapping(value = "/orders/orderdetails/deleteitem")
    public String deleteItem(@Param("orderListId") int orderListId, int quantity, @Param("productId") int productId, RedirectAttributes redirectAttributes){
        OrderList orderList = orderService.getOrderListById(orderListId);
        Product product = productService.getProduct(productId);
        BigDecimal productPrice = product.getPrice();
        BigDecimal price = productPrice.multiply(new BigDecimal(quantity));
        String orderId = Integer.toString(orderList.getOrder().getOrderId());
        int delPosQuantity = orderList.getQuantity();
        if(quantity >= orderList.getQuantity()){
           orderService.removeFromOrder(orderListId);
           orderService.sumTotalPrice();
           productService.addQuantity(delPosQuantity, productId);
        } else if((quantity > 0) && (quantity < orderList.getQuantity())){
            orderService.subItemsInOrder(quantity, orderListId);
            orderService.subPriceInOrder(price, orderListId);
            orderService.sumTotalPrice();
            productService.addQuantity(quantity, productId);
        } else {
            redirectAttributes.addFlashAttribute("error", "Number of removed items can't be a negative value or 0");
        }
        redirectAttributes.addAttribute("orderId", orderId);
        return "redirect:/orders/orderdetails";
    }

    @PostMapping(value = "/orders/closeorder")
    public String closeOrder(@Param("orderId") int orderId, RedirectAttributes redirectAttributes){
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        String userId = Integer.toString(user.getUserId());
        orderService.closeOrder(orderId);
        redirectAttributes.addAttribute("userId", userId);
        return "redirect:/orders";
    }


}
