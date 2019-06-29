package com.learn.account.repository;

import java.util.List;

import com.learn.account.model.po.TAccount;
import com.learn.account.model.po.TAccountUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface TAccountUserRepository extends JpaRepository<TAccountUser, Integer> {

	
	@Query(value="SELECT * FROM t_account_user WHERE account_id = ?1", nativeQuery = true)
	List<TAccountUser> findByAccountId(int account_id);
	
	TAccountUser findByEmail(String email);
	
	TAccountUser findById(int id);
	
	@Query(value="SELECT t FROM TAccountUser t WHERE t.tAccount.id = ?1", nativeQuery = false)
	Page<TAccountUser> findByAccountId(int account_id, Pageable pageable);
	
	
	Page<TAccountUser> findByTAccount(TAccount TAccount, Pageable pageable);
	

}
