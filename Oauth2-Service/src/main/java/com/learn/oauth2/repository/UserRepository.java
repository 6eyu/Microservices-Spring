package com.learn.oauth2.repository;

import com.learn.oauth2.model.po.TUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TUser, Integer> {

    TUser findByUsername(String username);

    TUser findByEmail(String email);
}