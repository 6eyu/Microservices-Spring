package com.learn.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    /**
     * 注入authenticationManager
     * 来支持 password grant type
     */
    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${learn.security.jwt.signkey}")
    private String signkey;

    @Resource(name = "userService")
    private UserService userDetailsService;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey(signkey);

//        DefaultAccessTokenConverter defaultAccessTokenConverter = new DefaultAccessTokenConverter();
//
//        EizUserAuthenticationTokenConverter eizUserAuthenticationTokenConverter = new EizUserAuthenticationTokenConverter();
//        eizUserAuthenticationTokenConverter.setUserDetailsService(userDetailsService);
//
//        defaultAccessTokenConverter.setUserTokenConverter(new EizUserAuthenticationTokenConverter());
//        jwtAccessTokenConverter.setAccessTokenConverter(defaultAccessTokenConverter);
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
//                .checkTokenAccess("isAuthenticated()") //permitALL 可使用自定义check_token 接口
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();

    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        configurer.jdbc(dataSource)
//                .withClient("EIZ Freight")
//                .accessTokenValiditySeconds(1000)
//                .refreshTokenValiditySeconds(1000)
//                .secret(new BCryptPasswordEncoder().encode("eiz-secret"))
//                .authorizedGrantTypes("password", "refresh_token")
//                .scopes("read","write","trust")
//                .and()
                .build(); //需要使用build()链接DB table
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(tokenEnhancer(), accessTokenConverter()));

        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .accessTokenConverter(accessTokenConverter())
                .userDetailsService(userDetailsService)
                .tokenEnhancer(tokenEnhancerChain);

    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    class CustomTokenEnhancer extends JwtAccessTokenConverter {
        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

            UserPrincipal user = (UserPrincipal) authentication.getPrincipal();

            Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
            info.put("sub", user.getTUser().getTAccount().getId());
            info.put("email", user.getTUser().getEmail());
            info.put("name", user.getTUser().getName());
            info.put("accountId", user.getTUser().getTAccount().getId());

            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

            return accessToken;
        }

    }
}
