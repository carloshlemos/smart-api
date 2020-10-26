package br.gov.go.smart_api.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    private static final String AUTHORITY_PREFIX = "ROLE_";
    private static final String CLAIM_ROLES = "roles";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/apis/id/**", "/csrf", "/v2/api-docs", "/swagger-resources/configuration/ui",
                        "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**",
                        "/swagger-resources/configuration/ui", "/swagger-resources/configuration/security", "/configuration/security").permitAll()
                //.anyRequest().denyAll()
                .and()
                .oauth2ResourceServer()
                .jwt();
//                .oauth2ResourceServer(oauth2ResourceServer ->
//                        oauth2ResourceServer
//                                .jwt(jwt ->
//                                        jwt.jwtAuthenticationConverter(getJwtAuthenticationConverter())));
    }

//    private Converter<Jwt, AbstractAuthenticationToken> getJwtAuthenticationConverter() {
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(getJwtGrantedAuthoritiesConverter());
//        return jwtAuthenticationConverter;
//    }
//
//    private Converter<Jwt, Collection<GrantedAuthority>> getJwtGrantedAuthoritiesConverter() {
//        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
//        converter.setAuthorityPrefix(AUTHORITY_PREFIX);
//        converter.setAuthoritiesClaimName(CLAIM_ROLES);
//        return converter;
//    }
}