package com.apap.tutorial6.controller;

import com.apap.tutorial6.model.UserRoleModel;
import com.apap.tutorial6.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

/**
 * Created by esak on 11/7/2018.
 */

@Controller
@RequestMapping("/user")
public class UserRoleController {
    @Autowired
    private UserRoleService userService;

    @Autowired
    private UserDetailsService userDetailsService;


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    private String addUserSubmit(@ModelAttribute UserRoleModel user) {
        userService.addUser(user);
        return "home";
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole(['ADMIN','PILOT','UMUM'])")
    @Transactional
    private String updatePassword(@RequestParam("old-password") String oldPass,
                                  @RequestParam("new-password") String newPass,
                                  @RequestParam("confirm-new-password") String newPassConfirm,
                                  Model model
    ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserRoleModel user = userService.findByUsername(auth.getName());
        if (userService.checkOldPassword(user, oldPass)) {
            if(!newPass.equals(newPassConfirm)) {
                model.addAttribute("message", "new password confirmation doesn't match new password");
            } else {
                userService.changePassword(user, newPass);
                model.addAttribute("message", "change password success");
            }
        } else {
            model.addAttribute("message","old password mismatch");
        }
        return "home";
    }
}
