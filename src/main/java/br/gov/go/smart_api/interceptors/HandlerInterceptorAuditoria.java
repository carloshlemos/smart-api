package br.gov.go.smart_api.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Carlos Henrique Lemos
 */
@Slf4j
@Component
public class HandlerInterceptorAuditoria implements HandlerInterceptor {

    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws RuntimeException {
        try{
            log.info("Tipo de Requisição interceptada: " + request.getMethod());
            if (request.getMethod().equals("POST") || request.getMethod().equals("PUT") || request.getMethod().equals("DELETE")) {
                StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("GEN.PKGGEN_INICIALIZA_USER.SPGEN_INICIALIZA_USER");
                storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
                storedProcedure.setParameter(1, SecurityContextHolder.getContext().getAuthentication().getName());
                storedProcedure.execute();
            }
        }catch(RuntimeException e){
            throw new RuntimeException("Ocorreu um erro ao tentar atribuir o usuário na sessão de auditoria",e);
        }
        return true;
    }
}
