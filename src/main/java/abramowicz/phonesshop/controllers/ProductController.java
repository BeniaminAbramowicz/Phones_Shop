package abramowicz.phonesshop.controllers;


import abramowicz.phonesshop.entities.Product;
import abramowicz.phonesshop.entities.User;
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
public class ProductController {

    private final ProductService productService;

    private final UserService userService;

    @Autowired
    public ProductController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping(value = "/allproducts")
    public String displayAllProducts(Model model){
        List<Product> productList = productService.displayAllProducts();
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        model.addAttribute("productList", productList);
        return "allproducts";
    }

    @GetMapping(value = "/allproducts/samsung")
    public String displaySamsung(Model model){
        List<Product> productList = productService.displaySamsungPhones();
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        model.addAttribute("productList", productList);
        return "allproducts";
    }

    @GetMapping(value = "/allproducts/xiaomi")
    public String displayXiaomi(Model model){
        List<Product> productList = productService.displayXiaomiPhones();
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        model.addAttribute("productList", productList);
        return "allproducts";
    }

    @GetMapping(value = "/allproducts/lg")
    public String displayLg(Model model){
        List<Product> productList = productService.displayLgPhones();
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        model.addAttribute("productList", productList);
        return "allproducts";
    }

    @GetMapping(value = "/allproducts/apple")
    public String displayApple(Model model){
        List<Product> productList = productService.displayApplePhones();
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        model.addAttribute("productList", productList);
        return "allproducts";
    }

    @GetMapping(value = "/allproducts/oneplus")
    public String displayOneplus(Model model){
        List<Product> productList = productService.displayOneplusPhones();
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        model.addAttribute("productList", productList);
        return "allproducts";
    }

    @GetMapping(value = "/allproducts/accessories")
    public String displayAccessories(Model model){
        List<Product> productList = productService.displayAccessories();
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        model.addAttribute("productList", productList);
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
        return "redirect:/allproducts";
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
        return "redirect:/allproducts";
    }

}
