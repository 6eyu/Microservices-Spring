package com.learn.oauth2.repository;

import com.learn.oauth2.model.po.TAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<TAccount, Integer> {

}
