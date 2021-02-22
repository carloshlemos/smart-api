package br.gov.go.smart_api.service;

import br.gov.go.smart_api.domain.utils.BaseEntity;
import br.gov.go.smart_api.domain.utils.PagingHeaders;
import br.gov.go.smart_api.domain.utils.PagingResponse;
import br.gov.go.smart_api.repository.JpaRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Carlos Henrique Lemos
 */
public class AbstractService<T extends BaseEntity> {

    protected final JpaRepo<T> repository;

    protected AbstractService(JpaRepo<T> repository) {
        this.repository = repository;
    }

    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    public T create(T entity) {
        return repository.save(entity);
    }

    public Optional<T> update(Long id, T entity) {
        repository.save(entity);
        return findById(id);
    }

    public void deleteById(Long id) {
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
    public PagingResponse get(Specification<T> spec, HttpHeaders headers, Sort sort) {
        if (isRequestPaged(headers)) {
            return get(spec, buildPageRequest(headers, sort));
        } else {
            final List<T> entities = get(spec, sort);
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
    public PagingResponse get(Specification<T> spec, Pageable pageable) {
        Page<T> page = repository.findAll(spec, pageable);
        List<T> content = page.getContent();
        return new PagingResponse(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
    }

    /**
     * get elements using Criteria.
     *
     * @param spec *
     * @return elements
     */
    public List<T> get(Specification<T> spec, Sort sort) {
        return repository.findAll(spec, sort);
    }

    protected boolean isRequestPaged(HttpHeaders headers) {
        return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
    }

    protected Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
        int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
        int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
        return PageRequest.of(page, size, sort);
    }

}
