package abramowicz.phonesshop.controllers;


import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.service.OrderService;
import abramowicz.phonesshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){this.orderService = orderService;}

    @GetMapping(value = "/orders")
    public String displayOrders(Model model){
        User user = orderService.displayUser();
        model.addAttribute("user", user);
        return "orders";
    }

    @PostMapping(value = "/createorder")
    public String createOrder(@Param("userId") int userId){
        orderService.createOrder(userId);
        return "redirect:/orders";
    }

    @PostMapping(value = "/test")
    public String test(){
        orderService.test();
        return "redirect:/orders";
    }



}
