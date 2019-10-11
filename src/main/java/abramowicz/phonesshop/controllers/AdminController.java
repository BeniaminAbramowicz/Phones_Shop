package abramowicz.phonesshop.controllers;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.entities.enums.OrderStatus;
import abramowicz.phonesshop.service.AdminService;
import abramowicz.phonesshop.service.OrderService;
import abramowicz.phonesshop.service.UserService;
import abramowicz.phonesshop.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {

    private final AdminService adminService;

    private final UserService userService;

    private final OrderService orderService;

    @Autowired
    public AdminController(AdminService adminService, UserService userService, OrderService orderService){
        this.adminService = adminService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/manageorders")
    public String manageOrdersPage(Model model){
        List<Order> orders = adminService.getOrdersToManage();
        List<OrderStatus> enums = Arrays.asList(OrderStatus.REALIZED, OrderStatus.CANCELLED);
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("enums", enums);
        model.addAttribute("orders", orders);
        model.addAttribute("user", user);
        return "orders";
    }

    @PostMapping(value = "/manageorders/changestatus")
    public String changeOrderStatus(int orderId, OrderStatus status, RedirectAttributes redirectAttributes){
        if(orderService.getOrderById(orderId).getStatus() == OrderStatus.REALIZED || orderService.getOrderById(orderId).getStatus() == OrderStatus.CANCELLED){
            redirectAttributes.addFlashAttribute("error", "Realized and cancelled orders can't have their status changed");
            return "redirect:/manageorders";
        } else{
            adminService.changeOrderStatus(orderId, status);
            return "redirect:/manageorders";
        }
    }

}
