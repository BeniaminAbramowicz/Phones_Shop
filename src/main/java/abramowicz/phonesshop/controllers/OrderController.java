package abramowicz.phonesshop.controllers;


import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.service.OrderService;
import abramowicz.phonesshop.service.UserService;
import abramowicz.phonesshop.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {

    private final OrderService orderService;

    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService){this.orderService = orderService;
        this.userService = userService;
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

    @PostMapping(value = "/createorder")
    public String createOrder(@Param("userId") int userId){
        orderService.createOrder(userId);
        return "redirect:/orders";
    }

}
