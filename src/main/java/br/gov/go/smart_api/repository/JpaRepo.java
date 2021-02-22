package br.gov.go.smart_api.repository;

import br.gov.go.smart_api.domain.utils.BaseEntity;
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
public interface JpaRepo<T extends BaseEntity> extends PagingAndSortingRepository<T, Long>, JpaSpecificationExecutor<T> {
    @Transactional(
            readOnly = true
    )
    @NonNull
    Optional<T> getToDeleteById(@NonNull Long var1);

    default Optional<T> del(@NonNull Long id) {
        return this.getToDeleteById(id).map((found) -> {
            this.delete(found);
            return found;
        });
    }
}
