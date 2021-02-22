package br.gov.go.smart_api.controller;

import br.gov.go.smart_api.domain.Cliente;
import br.gov.go.smart_api.domain.utils.PagingResponse;
import br.gov.go.smart_api.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Carlos Henrique Lemos
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/clientes", path = "/api/clientes")
@Api(value = "Retorna dados de Clientes.")
public class ClienteController extends AbstractController {

    public ClienteController(final ClienteService service) {
        super(service);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Retorna dados de Clientes de forma paginada", notes = "Retorna dados de Clientes forma paginada")
    @PreAuthorize("hasPermission('perfil_portal-policy','{actionid:Cliente.C}')")
    public ResponseEntity<List<Cliente>> getClientes(
            @And({
                    @Spec(path = "nome", params = "nome", spec = Like.class),
                    @Spec(path = "numeroCPF", params = "numeroCPF", spec = Equal.class)
            }) Specification<Cliente> specCliente,
            Sort sort,
            @RequestHeader HttpHeaders headers) {
        final PagingResponse response = service.get(specCliente, headers, sort);
        return new ResponseEntity(response.getElements(), returnHttpHeaders(response), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna dados do Cliente por ID", notes = "Retorna dados do Cliente por ID")
    @GetMapping(path = "/{clienteId}")
    @PreAuthorize("hasPermission('perfil_portal-policy','{actionid:Cliente.C}')")
    public ResponseEntity<Cliente> getById(@PathVariable(value = "clienteId") Long id) {
        return super.getByID(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('perfil_portal-policy','{actionid:Cliente.I}')")
    public ResponseEntity<Cliente> createCliente(
            @Valid @RequestBody Cliente cliente) {
        return super.create(cliente);
    }

    @PutMapping(path = "/{clienteId}", consumes = APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('perfil_portal-policy','{actionid:Cliente.A}')")
    public ResponseEntity<Cliente> updateCliente(
            @PathVariable(value = "clienteId") Long id, @Valid @RequestBody Cliente cliente) {
       return super.update(id, cliente);
    }

    @DeleteMapping("/{clienteId}")
    @PreAuthorize("hasPermission('perfil_portal-policy','{actionid:Cliente.E}')")
    public ResponseEntity<String> deleteCliente(@PathVariable Long id) {
        super.delete(id);
        return ResponseEntity.ok("Cliente was deleted!!!");
    }
}
