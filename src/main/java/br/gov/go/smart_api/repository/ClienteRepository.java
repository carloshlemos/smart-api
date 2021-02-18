package br.gov.go.smart_api.repository;

import br.gov.go.smart_api.domain.Cliente;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Carlos Henrique Lemos
 */
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {
}
