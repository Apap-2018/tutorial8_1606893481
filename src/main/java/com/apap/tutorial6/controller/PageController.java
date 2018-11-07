package com.apap.tutorial6.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

/**
 * Created by esak on 11/7/2018.
 */

@Controller
public class PageController {
    @RequestMapping("/")
    public String home () {
        return "home";
    }

    @RequestMapping("/login")
    public String login () {
        return "login";
    }
}
