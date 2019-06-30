package com.learn.gateway.authentication;

import com.learn.gateway.model.UsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserPrinciple extends User {


    private UsersEntity userEntity;

    public UserPrinciple(UsersEntity user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.userEntity = userEntity;
    }

    public UserPrinciple(UsersEntity userEntity, boolean enabled, boolean accountNonExpired,
                         boolean credentialsNonExpired,
                         boolean accountNonLocked,
                         Collection<? extends GrantedAuthority> authorities) {
        super(userEntity.getUsername(), userEntity.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userEntity = userEntity;
    }

    public UsersEntity getUserEntity() {
        return this.userEntity;
    }
}
