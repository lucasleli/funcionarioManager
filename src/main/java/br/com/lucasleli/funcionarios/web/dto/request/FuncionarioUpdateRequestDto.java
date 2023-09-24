package br.com.lucasleli.funcionarios.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FuncionarioUpdateRequestDto {

    @NotBlank(message = "O campo designacao é obrigatório.")
    private String designacao;

    @NotNull(message = "O campo salario é obrigatório.")
    private Double salario;

    @NotBlank(message = "O campo telefone é obrigatório.")
    private String telefone;

    @NotBlank(message = "O campo endereco é obrigatório.")
    private String endereco;

}
