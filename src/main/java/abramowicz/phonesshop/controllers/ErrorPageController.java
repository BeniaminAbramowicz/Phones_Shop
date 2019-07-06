package abramowicz.phonesshop.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;

public class ErrorPageController implements ErrorController {

    @GetMapping(value = "/error")
    public String displayErrorPage(){
        return "error";
    }

    @Override
    public String getErrorPath(){
        return "/error";
    }
}
