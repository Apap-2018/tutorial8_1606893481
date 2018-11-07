package com.apap.tutorial6.repository;

import com.apap.tutorial6.model.UserRoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by esak on 11/7/2018.
 */

@Repository
public interface UserRoleDb extends JpaRepository<UserRoleModel, Long>{
    UserRoleModel findByUsername(String username);


}
