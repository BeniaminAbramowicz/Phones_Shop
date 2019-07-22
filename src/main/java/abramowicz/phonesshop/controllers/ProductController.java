package abramowicz.phonesshop.controllers;


import abramowicz.phonesshop.entities.Product;
import abramowicz.phonesshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/allproducts")
    public String displayAllProducts(Model model){
        List<Product> productList = productService.displayAllProducts();
        model.addAttribute("productList", productList);
        return "allproducts";
    }

    @GetMapping(value = "/samsung")
    public String displaySamsung(Model model){
        List<Product> productList = productService.displaySamsungPhones();
        model.addAttribute("productList", productList);
        return "samsung";
    }

    @GetMapping(value = "/xiaomi")
    public String displayXiaomi(Model model){
        List<Product> productList = productService.displayXiaomiPhones();
        model.addAttribute("productList", productList);
        return "xiaomi";
    }

    @GetMapping(value = "/lg")
    public String displayLg(Model model){
        List<Product> productList = productService.displayLgPhones();
        model.addAttribute("productList", productList);
        return "lg";
    }

    @GetMapping(value = "/apple")
    public String displayApple(Model model){
        List<Product> productList = productService.displayApplePhones();
        model.addAttribute("productList", productList);
        return "apple";
    }

    @GetMapping(value = "/oneplus")
    public String displayOneplus(Model model){
        List<Product> productList = productService.displayOneplusPhones();
        model.addAttribute("productList", productList);
        return "oneplus";
    }

    @GetMapping(value = "/accessories")
    public String displayAccessories(Model model){
        List<Product> productList = productService.displayAccessories();
        model.addAttribute("productList", productList);
        return "accessories";
    }

    @GetMapping(value = "/addproducts")
    public String newProduct(Model model){
        Product product = new Product();
        model.addAttribute("addproducts", product);
        return "addproducts";
    }

    @PostMapping(value = "/addproduct")
    public String addProduct(Model model, Product product, BindingResult result){
        productService.saveProduct(product);
        return "addproducts";
    }

    @PostMapping(value = "/allproducts/{productId}")
    public String deleteProduct(@Param("productId") int productId){
        productService.deleteProduct(productId);
        return "redirect:/allproducts";
    }

    @GetMapping(value = "/allproducts/editproduct/{productId}")
    public String editPage(Model model, @PathVariable("productId") int productId){
        Product product = productService.getProduct(productId);
        model.addAttribute("product", product);
        return "allproducts/editproduct";
    }

    @PostMapping(value = "edit")
    public String editProduct(@Param("name") String name, @Param("description") String description, @Param("price") BigDecimal price, @Param("itemsNumber") int itemsNumber, @Param("picture") String picture, @Param("isAccessory") Boolean isAccessory, @Param("productId") int productId){
        productService.editProduct(name, description, price, itemsNumber, picture, isAccessory, productId);
        return "redirect:/allproducts";
    }

}
