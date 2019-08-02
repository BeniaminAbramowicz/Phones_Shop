package abramowicz.phonesshop.controllers;


import abramowicz.phonesshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {

    private final ProductService productService;

    @Autowired
    public OrderController(ProductService productService){this.productService = productService;}


}
