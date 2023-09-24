package br.com.lucasleli.funcionarios.web.dto.response;

import lombok.Data;

@Data
public class FuncionarioResponseDto {

    private Long id;
    private String nome;
    private String designacao;
    private Double salario;
    private String telefone;
    private String endereco;

}
