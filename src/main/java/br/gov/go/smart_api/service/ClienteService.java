package br.gov.go.smart_api.service;

import br.gov.go.smart_api.domain.Cliente;
import br.gov.go.smart_api.domain.utils.PagingResponse;
import br.gov.go.smart_api.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Carlos Henrique Lemos
 */
@Slf4j
@Service
public class ClienteService extends AbstractService {

    @Autowired
    private ClienteRepository repository;

    public Optional<Cliente> findById(Long id) {
        return repository.findById(id);
    }

    public Cliente createCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public Optional<Cliente> updateCliente(Long id, Cliente cliente) {
        repository.save(cliente);
        return findById(id);
    }

    public void deleteClienteById(Long id) {
        repository.deleteById(id);
    }

    /**
     * get element using Criteria.
     *
     * @param spec    *
     * @param headers pagination data
     * @param sort    sort criteria
     * @return retrieve elements with pagination
     */
    public PagingResponse get(Specification<Cliente> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            final List<Cliente> entities = get(spec, sort);
            return new PagingResponse((long) entities.size(), 0L, 0L, 0L, 0L, entities);
        }
    }

    /**
     * get elements using Criteria.
     *
     * @param spec     *
     * @param pageable pagination data
     * @return retrieve elements with pagination
     */
    public PagingResponse get(Specification<Cliente> spec, Pageable pageable) {
        Page<Cliente> page = repository.findAll(spec, pageable);
        List<Cliente> content = page.getContent();
        return new PagingResponse(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
    }

    /**
     * get elements using Criteria.
     *
     * @param spec *
     * @return elements
     */
    public List<Cliente> get(Specification<Cliente> spec, Sort sort) {
        return repository.findAll(spec, sort);
    }
}
