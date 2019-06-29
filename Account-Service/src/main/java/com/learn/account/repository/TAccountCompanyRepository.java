package com.learn.account.repository;

import javax.persistence.LockModeType;

import com.learn.account.model.po.TAccountCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TAccountCompanyRepository extends JpaRepository<TAccountCompany, Integer> {

	TAccountCompany findById(int id);
	
	@Query(value="SELECT * FROM t_account_company WHERE account_id = ?1", nativeQuery = true)
	TAccountCompany findByAccountId(int account_id);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	@Query(value="SELECT abn FROM TAccountCompany")
	String findAllABN();
}
