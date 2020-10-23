package br.gov.go.smart_api.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/apis/id/**", "/csrf", "/v2/api-docs", "/swagger-resources/configuration/ui",
                        "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**",
                        "/swagger-resources/configuration/ui", "/swagger-resources/configuration/security", "/configuration/security").permitAll()
                   .mvcMatchers("/api/menu/**").hasAuthority("SCOPE_openid")
                .anyRequest().denyAll()
                .and()
                .oauth2ResourceServer()
                .jwt();
    }
}