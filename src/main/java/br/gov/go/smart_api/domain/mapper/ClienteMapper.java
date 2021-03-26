package br.gov.go.smart_api.domain.mapper;

import br.gov.go.smart_api.domain.Cliente;
import br.gov.go.smart_api.domain.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Carlos Henrique Lemos
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {
    ClienteDTO toClienteDTO(Cliente cliente);
}
