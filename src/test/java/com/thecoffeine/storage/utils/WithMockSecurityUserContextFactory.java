/**
 * Copyright (c) 2014-2017 by Coffeine Inc
 *
 * @author <a href = "mailto:vitaliy.tsutsman@musician-virtuoso.com>Vitaliy Tsutsman</a>
 *
 * @date 2/15/17 11:54 PM
 */

package com.thecoffeine.storage.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Security context factory.
 *
 * @version 1.0
 */
public class WithMockSecurityUserContextFactory implements WithSecurityContextFactory<WithMockSecurityUser> {

    /**
     * Create a {@link SecurityContext} given an Annotation.
     *
     * @param user the {@link Annotation} to create the {@link SecurityContext}
     *                   from. Cannot be null.
     * @return the {@link SecurityContext} to use. Cannot be null.
     */
    @Override
    public SecurityContext createSecurityContext( WithMockSecurityUser user ) {

        final SecurityContext context = SecurityContextHolder.createEmptyContext();

        List<SimpleGrantedAuthority> authorities = Stream.of(
            new SimpleGrantedAuthority("ROLE_POET")
        ).collect( Collectors.toList());

        UserDetails principal = new User( user.username(), user.password(), authorities );

        Authentication auth = new UsernamePasswordAuthenticationToken( principal, "password", principal.getAuthorities() );

        context.setAuthentication( auth );

        return context;
    }
}
