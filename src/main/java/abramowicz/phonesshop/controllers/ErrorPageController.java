package abramowicz.phonesshop.controllers;

import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.service.UserService;
import abramowicz.phonesshop.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController implements ErrorController {


    private final UserService userService;

    @Autowired
    public ErrorPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/error")
    public String displayErrorPage(Model model){
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        return "error";
    }

    @Override
    public String getErrorPath(){
        return "/error";
    }
}
