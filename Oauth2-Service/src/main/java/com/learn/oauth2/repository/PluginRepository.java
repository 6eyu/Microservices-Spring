package com.learn.oauth2.repository;

import com.learn.oauth2.model.po.TPlugin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PluginRepository extends JpaRepository<TPlugin, Integer> {

    Optional<TPlugin> findByNameAndStatusIs(String name, Integer status);

}