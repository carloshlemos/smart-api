package br.gov.go.smart_api.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

/**
 * @author Carlos Henrique Lemos
 */
@Repository
public class HistoricoRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * Executa a procedure {call GEN.PKGGEN_INICIALIZA_USER.SPGEN_INICIALIZA_USER(:usuario)}
     *
     * @param usuario
     */
    public void setUsuarioSessao(String usuario) {
        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("GEN.PKGGEN_INICIALIZA_USER.SPGEN_INICIALIZA_USER");
            storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            storedProcedure.setParameter(1, usuario);
            storedProcedure.execute();
        } catch (Exception e) {
            throw new RuntimeException("Houve um erro ao tentar atribuir o usuário na sessão de auditoria", e);
        }
    }

}
