package abramowicz.phonesshop.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping(value = "/orders")
    public String displayOrders(Model model){



        return "orders";
    }
}
