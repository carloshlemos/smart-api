package br.gov.go.smart_api.controller;

import br.gov.go.smart_api.domain.MenuItem;
import br.gov.go.smart_api.repository.MenuItemRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @ApiOperation(value = "Lista todos os itens do Menu")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasPermission('perfil_portal-policy','{actionid:Menu.C}')")
    public Iterable<MenuItem> getMenu() {
        return menuItemRepository.findAll();
    }
}
