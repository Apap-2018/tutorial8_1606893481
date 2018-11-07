package com.apap.tutorial6.service;

import com.apap.tutorial6.model.UserRoleModel;
import com.apap.tutorial6.repository.UserRoleDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by esak on 11/7/2018.
 */

@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleDb userRoleDb;


    @Override
    public UserRoleModel addUser(UserRoleModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userRoleDb.save(user);
    }

    @Override
    public String encrypt(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    @Transactional
    public UserRoleModel findByUsername(String username) {
        return userRoleDb.findByUsername(username);
    }

    @Override
    public boolean checkOldPassword(UserRoleModel user, String oldPass) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(oldPass,user.getPassword());
    }

    @Override
    public void changePassword(UserRoleModel user, String newPass) {
        user.setPassword(encrypt(newPass));
        userRoleDb.save(user);
    }


}
