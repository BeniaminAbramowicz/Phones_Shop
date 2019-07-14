package abramowicz.phonesshop.controllers;


import abramowicz.phonesshop.entities.Product;
import abramowicz.phonesshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

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
}
