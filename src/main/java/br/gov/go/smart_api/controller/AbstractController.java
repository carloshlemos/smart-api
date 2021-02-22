package br.gov.go.smart_api.controller;

import br.gov.go.smart_api.domain.utils.BaseEntity;
import br.gov.go.smart_api.domain.utils.PagingHeaders;
import br.gov.go.smart_api.domain.utils.PagingResponse;
import br.gov.go.smart_api.service.AbstractService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

/**
 * @author Carlos Henrique Lemos
 */
public abstract class AbstractController<T extends BaseEntity> {

    protected final AbstractService<T> service;

    public AbstractController(@NonNull AbstractService<T> service) {
        this.service = service;
    }

    @NonNull
    public ResponseEntity<T> getByID(@NonNull Long id) {
        final Optional<T> entity = service.findById(id);
        if (entity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entity.get());
    }

    @NonNull
    public ResponseEntity<T> create(@Valid @RequestBody T request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(request));
    }

    @NonNull
    public ResponseEntity<T> update(@NonNull Long id, @NonNull T request) {
        return (ResponseEntity)this.service.update(id, request).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @NonNull
    public void delete(@NonNull Long id) {
        this.service.deleteById(id);
    }

    protected HttpHeaders returnHttpHeaders(PagingResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
        headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
        headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
        headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
        headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
        return headers;
    }
}
