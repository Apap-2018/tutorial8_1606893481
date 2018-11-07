package com.apap.tutorial6.service;

import com.apap.tutorial6.model.UserRoleModel;
import org.springframework.security.core.userdetails.User;

/**
 * Created by esak on 11/7/2018.
 */
public interface UserRoleService {
    UserRoleModel addUser(UserRoleModel user);
    public String encrypt(String password);
    public UserRoleModel findByUsername(String username);
    public boolean checkOldPassword(UserRoleModel user, String oldPass);
    public void changePassword(UserRoleModel user, String newPass);
}
