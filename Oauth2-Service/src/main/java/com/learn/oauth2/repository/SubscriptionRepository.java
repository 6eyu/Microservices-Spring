package com.learn.oauth2.repository;

import com.learn.oauth2.model.po.TAccount;
import com.learn.oauth2.model.po.TPlugin;
import com.learn.oauth2.model.po.TSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<TSubscription, Integer> {

    Optional<TSubscription> findByTAccountAndTPluginAndStatusIs(TAccount tAccount, TPlugin tPlugin, Integer status);
}