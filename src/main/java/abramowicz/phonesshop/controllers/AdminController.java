package abramowicz.phonesshop.controllers;

import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.OrderList;
import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.entities.enums.OrderStatus;
import abramowicz.phonesshop.service.AdminService;
import abramowicz.phonesshop.service.OrderService;
import abramowicz.phonesshop.service.ProductService;
import abramowicz.phonesshop.service.UserService;
import abramowicz.phonesshop.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {

    private final AdminService adminService;

    private final UserService userService;

    private final OrderService orderService;

    private final ProductService productService;

    @Autowired
    public AdminController(AdminService adminService, UserService userService, OrderService orderService, ProductService productService){
        this.adminService = adminService;
        this.userService = userService;
        this.orderService = orderService;
        this.productService = productService;
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

    @GetMapping(value ="/manageorders/details")
    public String manageOrderDetailsPage(int orderId, Model model){
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        OrderStatus status = orderService.getOrderById(orderId).getStatus();
        List<OrderList> orderListItems = orderService.displayOrderList(orderId);
        model.addAttribute("orderListItems", orderListItems);
        model.addAttribute("user", user);
        model.addAttribute("status", status);
        return "orderlist";
    }

    @PostMapping(value = "/manageorders/details/deleteitem")
    public String deleteItemFromOrder(int orderListId, int quantity, int productId, RedirectAttributes redirectAttributes){
        OrderList orderList = orderService.getOrderListById(orderListId);
        int orderId = orderList.getOrder().getOrderId();
        int delPosQuantity = orderList.getQuantity();
        BigDecimal productPrice = productService.getProduct(productId).getPrice();
        BigDecimal price = productPrice.multiply(new BigDecimal(quantity));
        if(quantity >= orderList.getQuantity()){
            orderService.removeFromOrder(orderListId);
            List<OrderList> orderItems = orderService.displayOrderList(orderId);
            if(orderItems.isEmpty()){
                orderService.resetOrderPrice(orderId);
            } else{
                orderService.sumTotalPrice(orderId);
            }
            productService.addQuantity(delPosQuantity, productId);
        } else if((quantity > 0) && (quantity < orderList.getQuantity())){
            orderService.subItemsInOrder(quantity, orderListId);
            orderService.subPriceInOrder(price, orderListId);
            orderService.sumTotalPrice(orderId);
            productService.addQuantity(quantity, productId);
        } else{
            redirectAttributes.addFlashAttribute("error", "Number of removed items can't be a negative value or 0");
        }
        redirectAttributes.addAttribute("orderId", orderId);
        return "redirect:/manageorders/details";
    }

}
