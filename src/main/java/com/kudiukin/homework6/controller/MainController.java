package com.kudiukin.homework6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    @RequestMapping(method = RequestMethod.GET)
    public String mainIndex(Model model) {
        String message = "Online shop main page";
        model.addAttribute("message", message);
        return "index";
    }
}