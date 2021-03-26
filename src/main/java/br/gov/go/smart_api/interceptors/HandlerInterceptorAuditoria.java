package br.gov.go.smart_api.interceptors;

import br.gov.go.smart_api.aspecto.HistoricoAspectoFuncional;
import br.gov.go.smart_api.repository.HistoricoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Carlos Henrique Lemos
 */
@Slf4j
@Component
public class HandlerInterceptorAuditoria implements HandlerInterceptor {

    @Autowired
    private HistoricoAspectoFuncional historico;

    @Autowired
    private HistoricoRepository historicoRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws RuntimeException {
        try {
            log.info("Tipo de Requisição interceptada: " + request.getMethod());
            if (request.getMethod().equals("POST") || request.getMethod().equals("PUT") || request.getMethod().equals("DELETE")) {
                historico.s = () -> SecurityContextHolder.getContext().getAuthentication().getName();
                historico.c = (final String usuario) -> historicoRepository.setUsuarioSessao(SecurityContextHolder.getContext().getAuthentication().getName());
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Ocorreu um erro ao tentar atribuir o usuário na sessão de auditoria", e);
        }
        return true;
    }
}
