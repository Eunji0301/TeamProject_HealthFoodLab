package  com.healthFood.lab.spring2502_heathFood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MYFridgeController {
    @RequestMapping("/usr/MYFridge/MYFridge")
    public String showRecipeList() {
        return "usr/MYFridge/MYFridge";
    }
}
