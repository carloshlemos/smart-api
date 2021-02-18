package br.gov.go.smart_api.controller;

import br.gov.go.smart_api.domain.utils.PagingHeaders;
import br.gov.go.smart_api.domain.utils.PagingResponse;
import org.springframework.http.HttpHeaders;

/**
 * @author Carlos Henrique Lemos
 */
public class AbstractController {

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
