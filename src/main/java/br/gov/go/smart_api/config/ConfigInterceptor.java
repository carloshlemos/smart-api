package br.gov.go.smart_api.config;

import br.gov.go.smart_api.interceptors.HandlerInterceptorAuditoria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Carlos Henrique Lemos
 */
@RequiredArgsConstructor
@Component
public class ConfigInterceptor implements WebMvcConfigurer {

    private final HandlerInterceptorAuditoria inperceptorAuditoria;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(inperceptorAuditoria);
    }
}