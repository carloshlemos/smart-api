package br.gov.go.smart_api.aspecto;

import br.gov.go.smart_api.annotation.Historico;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Carlos Henrique Lemos
 */
@Slf4j
@Aspect
@Component
public class HistoricoAspectoFuncional {
    public Consumer<String> c;
    public Supplier<String> s;

    public HistoricoAspectoFuncional() {}

    @Before("execution(* *.*(..)) && @annotation(historico) ")
    public void inicializarUsuario(JoinPoint joinPoint, Historico historico) {
        try {
            this.c.accept(this.s.get());
        } catch (Exception var4) {
            log.error(var4.getMessage());
            throw new IllegalStateException("Não foi possível configurar o usuário na sessão!", var4);
        }
    }
}
