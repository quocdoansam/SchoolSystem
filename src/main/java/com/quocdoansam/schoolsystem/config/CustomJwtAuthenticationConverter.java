package com.quocdoansam.schoolsystem.config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

@Configuration
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

        @Override
        public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
                String id = jwt.getClaim("id");
                String email = jwt.getClaim("sub");
                String name = jwt.getClaim("name");

                // Get scope like: ["STUDENT", "TEACHER"]
                List<String> roles = List.of(jwt.getClaimAsString("scope").split(" "));

                Set<GrantedAuthority> authorities = roles.stream()
                                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                                .collect(Collectors.toSet());

                UserPrincipal principal = UserPrincipal.builder()
                                .id(id)
                                .email(email)
                                .name(name)
                                .roles(new HashSet<>(roles))
                                .build();

                return new UsernamePasswordAuthenticationToken(principal, null, authorities);
        }
}
