package abramowicz.phonesshop.controllers;

import abramowicz.phonesshop.entities.User;
import abramowicz.phonesshop.service.UserService;
import abramowicz.phonesshop.utilities.UserUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {

    private final UserService userService;

    @Autowired
    public HomePageController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = {"/", "/index"})
    public String displayHomePage(Model model){
        String username = UserUtilities.getLoggedUsername();
        User user = userService.getUserByEmail(username);
        model.addAttribute("user", user);
        return "index";
    }
}
