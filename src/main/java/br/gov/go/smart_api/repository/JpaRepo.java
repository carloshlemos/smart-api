package br.gov.go.smart_api.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Carlos Henrique Lemos
 */
@Transactional
@NoRepositoryBean
public interface JpaRepo<T, ID> extends PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T> {
    @Transactional(
            readOnly = true
    )
    @NonNull
    Optional<T> getToDeleteById(@NonNull ID var1);

    default Optional<T> del(@NonNull ID id) {
        return this.getToDeleteById(id).map((found) -> {
            this.delete(found);
            return found;
        });
    }
}
