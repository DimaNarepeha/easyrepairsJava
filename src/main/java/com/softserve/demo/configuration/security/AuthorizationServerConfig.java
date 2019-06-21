package com.softserve.demo.configuration.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JdbcTemplate jdbcTemplate;
    private final TokenStore tokenStore;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    public AuthorizationServerConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                                     CustomAuthenticationProvider customAuthenticationProvider,
                                     JdbcTemplate jdbcTemplate, TokenStore tokenStore) {
        this.userDetailsService = userDetailsService;
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.jdbcTemplate = jdbcTemplate;
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.jdbc(jdbcTemplate.getDataSource());
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(customAuthenticationProvider)
                .userDetailsService(userDetailsService);
    }
}
