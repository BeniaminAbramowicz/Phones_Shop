package abramowicz.phonesshop.controllers;


import abramowicz.phonesshop.entities.Product;
import abramowicz.phonesshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/products")
    public String displayProducts(Model model){
        List<Product> productList = productService.displayProductsList();
        model.addAttribute("productList", productList);
        return "products";
    }

}
