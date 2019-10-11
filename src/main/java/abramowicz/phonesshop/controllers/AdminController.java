package abramowicz.phonesshop.controllers;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.entities.enums.OrderStatus;
import abramowicz.phonesshop.service.AdminService;
import abramowicz.phonesshop.service.UserService;
import abramowicz.phonesshop.utilities.UserUtilities;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {

    private final AdminService adminService;

    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService){
        this.adminService = adminService;
        this.userService = userService;
    }

    @GetMapping(value = "/manageorders")
    public String manageOrdersPage(Model model){
        List<Order> orders = adminService.getOrdersToManage();
        List<OrderStatus> enums = Arrays.asList(OrderStatus.FULFILLED, OrderStatus.CANCELLED);
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("enums", enums);
        model.addAttribute("orders", orders);
        model.addAttribute("user", user);
        return "orders";
    }

    @PostMapping(value = "/manageorders/changestatus")
    public String changeOrderStatus(int orderId, OrderStatus status){
        adminService.changeOrderStatus(orderId, status);
        return "redirect:/manageorders";
    }

}
