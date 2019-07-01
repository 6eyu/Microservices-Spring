package com.learn.oauth2.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserPrincipal extends User {

    private TUser tUser;

    public UserPrincipal(TUser tUser, boolean enabled, boolean accountNonExpired,
                         boolean credentialsNonExpired,
                         boolean accountNonLocked,
                         Collection<? extends GrantedAuthority> authorities) {
        super(tUser.getUsername(), tUser.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.tUser = tUser;
    }

    public TUser getTUser() {
        return this.tUser;
    }
}
