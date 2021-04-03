package br.com.sgm.auth.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ROLE_ADMIN, ROLE_USER, ROLE_CITIZEN;

    @Override
    public String getAuthority() {
        return name();
    }
}
