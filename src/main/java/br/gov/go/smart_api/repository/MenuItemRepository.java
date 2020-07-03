package br.gov.go.smart_api.repository;

import br.gov.go.smart_api.domain.MenuItem;
import org.springframework.data.repository.CrudRepository;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
}
