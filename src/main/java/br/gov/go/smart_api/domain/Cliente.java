package br.gov.go.smart_api.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Carlos Henrique Lemos
 */
@Data
@Entity
@Table(name = "DOT_CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_CLIENTE", nullable = false)
    private Long id;

    @Column(name = "NOME_CLIENTE", length = 80, nullable = false)
    private String nome;

    @Column(name = "NUMR_CPF", length = 11, nullable = false)
    private String numeroCPF;

    @Column(name = "NUMR_TELEFONE", length = 11, nullable = false)
    private String telefone;

    @Column(name = "DESC_ENDERECO", length = 200, nullable = false)
    private String endereco;
}
