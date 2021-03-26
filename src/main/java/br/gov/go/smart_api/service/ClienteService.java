package br.gov.go.smart_api.service;

import br.gov.go.smart_api.domain.Cliente;
import br.gov.go.smart_api.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Carlos Henrique Lemos
 */
@Slf4j
@Service
public class ClienteService extends AbstractService<Cliente, Long> {

    protected ClienteService(final ClienteRepository repository) {
        super(repository);
    }
}
