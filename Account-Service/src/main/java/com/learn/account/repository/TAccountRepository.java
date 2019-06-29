package com.learn.account.repository;


import com.learn.account.model.po.TAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TAccountRepository extends JpaRepository<TAccount, Integer>{
	
	TAccount findById(int id);
}
