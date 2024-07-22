package com.r3s.warehouse.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppConstant {

    public enum Privileges {
        READ,WRITE,DELETE,UPDATE
        }
    @RequiredArgsConstructor
    @Getter
    public enum Role {
        ADMIN(
                Set.of(Privileges.READ, Privileges.WRITE, Privileges.DELETE, Privileges.UPDATE)
        ),
        USER(
                Set.of(Privileges.READ, Privileges.WRITE)
        );
        private final Set<Privileges> privileges;

        public List<SimpleGrantedAuthority> getAuthorities() {
            List<SimpleGrantedAuthority> authorities = getPrivileges()
                    .stream()
                    .map(privileges -> new SimpleGrantedAuthority(privileges.name()))
                    .collect(Collectors.toList());
            authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
            return authorities;
        }
    }

    public enum TokenType {
        BEARER
    }

    public enum HttpMethodEnum {
        GET, POST, PUT, DELETE
    }
}
