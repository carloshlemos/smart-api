package br.gov.go.smart_api.controller;

import br.gov.go.smart_api.domain.MenuItem;
import br.gov.go.smart_api.repository.MenuItemRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @ApiOperation(value = "Lista todos os itens do Menu")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna a lista de itens de Menu"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<MenuItem> getMenu() {
        return menuItemRepository.findAll();
    }
}
