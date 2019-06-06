package com.softserve.demo.configuration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String SIGNING_KEY = "as466kf";

    private final JdbcTemplate jdbcTemplate;
    private final TokenStore tokenStore;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    public AuthorizationServerConfig(CustomAuthenticationProvider customAuthenticationProvider,
                                     JdbcTemplate jdbcTemplate, TokenStore tokenStore) {
        this.customAuthenticationProvider = customAuthenticationProvider;
        this.jdbcTemplate = jdbcTemplate;
        this.tokenStore = tokenStore;
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY);
        return converter;
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.jdbc(jdbcTemplate.getDataSource());
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager(customAuthenticationProvider);
    }
}
