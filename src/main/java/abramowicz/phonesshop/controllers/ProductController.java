package abramowicz.phonesshop.controllers;


import abramowicz.phonesshop.entities.Order;
import abramowicz.phonesshop.entities.Product;
import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.service.OrderService;
import abramowicz.phonesshop.service.ProductService;
import abramowicz.phonesshop.service.UserService;
import abramowicz.phonesshop.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    private final UserService userService;

    private final OrderService orderService;

    @Autowired
    public ProductController(ProductService productService, UserService userService, OrderService orderService) {
        this.productService = productService;
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping(value = "/products/{brand}")
    public String displayProductsBrand(@PathVariable String brand, Model model){
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        if(!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            Order order = orderService.getOpenOrder(user.getEmail());
            model.addAttribute("order", order);
        }
        if(brand.equals("allproducts")){
            List<Product> productList = productService.displayAllProducts();
            model.addAttribute("productList", productList);
        } else if(brand.equals("accessories")){
            List<Product> productList = productService.displayAccessories();
            model.addAttribute("productList", productList);
        } else{
            List<Product> productList = productService.getProductsByBrand(brand);
            model.addAttribute("productList", productList);
        }
        return "allproducts";
    }

    @GetMapping(value = "/addproducts")
    public String newProduct(Model model){
        Product product = new Product();
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        model.addAttribute("addproducts", product);
        return "addproducts";
    }

    @PostMapping(value = "/addproduct")
    public String addProduct(Product product){
        productService.saveProduct(product);
        return "redirect:/addproducts";
    }

    @PostMapping(value = "/allproducts/deleteproduct/{productId}")
    public String deleteProduct(@Param("productId") int productId){
        productService.deleteProduct(productId);
        return "redirect:/products/allproducts";
    }

    @GetMapping(value = "/allproducts/editproduct/{productId}")
    public String editPage(Model model, @PathVariable("productId") int productId){
        Product product = productService.getProduct(productId);
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        model.addAttribute("product", product);
        return "editproduct";
    }

    @PostMapping(value = "/edit")
    public String editProduct(@Param("name") String name, @Param("description") String description, @Param("price") BigDecimal price, @Param("itemsNumber") int itemsNumber, @Param("picture") String picture, @Param("isAccessory") Boolean isAccessory, @Param("productId") int productId){
        productService.editProduct(name, description, price, itemsNumber, picture, isAccessory, productId);
        return "redirect:/products/allproducts";
    }

}
