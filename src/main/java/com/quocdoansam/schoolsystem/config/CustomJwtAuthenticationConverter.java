package com.quocdoansam.schoolsystem.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

@Configuration
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Object idObj = jwt.getClaim("id");
        Long id = idObj instanceof Integer ? ((Integer) idObj).longValue() : (Long) idObj;

        String email = jwt.getClaim("sub");
        List<String> roles = jwt.getClaimAsString("scope") != null
                ? List.of(jwt.getClaimAsString("scope").split(" "))
                : List.of();

        Set<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toSet());

        UserPrincipal principal = UserPrincipal.builder()
                .id(id)
                .email(email)
                .roles(new HashSet<>(roles))
                .build();

        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }
}
