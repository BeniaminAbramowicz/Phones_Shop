package abramowicz.phonesshop.controllers;


import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.OrderList;
import abramowicz.phonesshop.entities.Product;
import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.entities.enums.OrderStatus;
import abramowicz.phonesshop.service.OrderService;
import abramowicz.phonesshop.service.ProductService;
import abramowicz.phonesshop.service.UserService;
import abramowicz.phonesshop.utilities.OtherUtils;
import abramowicz.phonesshop.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        OrderStatus status = orderService.getOrderById(orderId).getStatus();
        model.addAttribute("user", user);
        if(OtherUtils.userHasOrder(orders, orderId) == false){
            redirectAttributes.addFlashAttribute("error", "Order with given id doesn't exists");
            return "redirect:/orders";
        } else {
            List<OrderList> orderListItems = orderService.displayOrderList(orderId);
            model.addAttribute("orderListItems", orderListItems);
            model.addAttribute("status", status);
            return "orderlist";
        }
    }

    @PostMapping(value = "/orderitem")
    public String orderItem(@Param("productId") int productId, @Param("quantity") int quantity, @Param("price") BigDecimal price, RedirectAttributes redirectAttributes){
        String email = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(email);
        if(orderService.getOpenOrder(email) == null){
            orderService.createOrder(user.getUserId());
        }
        Order order = orderService.getOpenOrder(email);
        List<OrderList> orderListItems = orderService.displayOrderList(order.getOrderId());
        int prQuantity = productService.getProduct(productId).getItemsNumber();
        if((OtherUtils.containsItem(orderListItems, productId)) && (prQuantity >= quantity) ){
            BigDecimal summedPrice = price.multiply(new BigDecimal(quantity));
            OrderList orderList = orderService.getOrderListByProductId(productId);
            orderService.addExistingItem(summedPrice, quantity, orderList.getOrderListId());
            productService.subQuantity(quantity, productId);
            orderService.sumTotalPrice(order.getOrderId());
        } else if((OtherUtils.containsItem(orderListItems, productId) == false) && (prQuantity >= quantity)){
            BigDecimal pricem = price.multiply(new BigDecimal(quantity));
            OrderList orderList = new OrderList();
            orderList.setOrder(orderService.getOrderById(order.getOrderId()));
            orderList.setPrice(pricem);
            orderList.setQuantity(quantity);
            orderList.setProduct(productService.getProduct(productId));
            orderService.addItemToOrder(orderList);
            productService.subQuantity(quantity, productId);
            orderService.sumTotalPrice(order.getOrderId());
        } else{
            redirectAttributes.addFlashAttribute("error", "There isn't enough items in the magazine to order that amount");
        }
        return "redirect:/products/allproducts";
    }
    @PostMapping(value = "/orders/orderdetails/deleteitem")
    public String deleteItem(@Param("orderListId") int orderListId, int quantity, @Param("productId") int productId, RedirectAttributes redirectAttributes){
        OrderList orderList = orderService.getOrderListById(orderListId);
        int orderId = orderList.getOrder().getOrderId();
        if(orderList.getOrder().getStatus() == OrderStatus.CLOSED){
            redirectAttributes.addFlashAttribute("error", "You can't remove items from closed order");
            redirectAttributes.addAttribute("orderId", orderId);
            return "redirect:/orders/orderdetails";
        } else {
            Product product = productService.getProduct(productId);
            BigDecimal productPrice = product.getPrice();
            BigDecimal price = productPrice.multiply(new BigDecimal(quantity));
            int delPosQuantity = orderList.getQuantity();
            if (quantity >= orderList.getQuantity()) {
                orderService.removeFromOrder(orderListId);
                List<OrderList> orderItems = orderService.displayOrderList(orderId);
                if (orderItems.isEmpty()) {
                    orderService.resetOrderPrice(orderId);
                } else {
                    orderService.sumTotalPrice(orderId);
                }
                productService.addQuantity(delPosQuantity, productId);
            } else if ((quantity > 0) && (quantity < orderList.getQuantity())) {
                orderService.subItemsInOrder(quantity, orderListId);
                orderService.subPriceInOrder(price, orderListId);
                orderService.sumTotalPrice(orderId);
                productService.addQuantity(quantity, productId);
            } else {
                redirectAttributes.addFlashAttribute("error", "Number of removed items can't be a negative value or 0");
            }
            redirectAttributes.addAttribute("orderId", orderId);
            return "redirect:/orders/orderdetails";
        }
    }

    @PostMapping(value = "/orders/closeorder")
    public String closeOrder(@Param("orderId") int orderId, RedirectAttributes redirectAttributes){
        if(orderService.getOrderById(orderId).getStatus() == OrderStatus.CLOSED){
            redirectAttributes.addFlashAttribute("error", "This order has already been closed");
            return "redirect:/orders";
        } else {
            orderService.closeOrder(orderId);
            return "redirect:/orders";
        }
    }


}
