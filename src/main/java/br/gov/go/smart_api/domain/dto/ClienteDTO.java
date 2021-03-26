package br.gov.go.smart_api.domain.dto;

import lombok.Data;

/**
 * @author Carlos Henrique Lemos
 */
@Data
public class ClienteDTO {
    private Long id;
    private String nome;
    private String numeroCPF;
    private String telefone;
    private String endereco;
}
