package br.com.lucasleli.funcionarios.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FuncionarioCreateRequestDTO {

    @NotBlank
    @JsonProperty
    private String nome;

    @NotBlank
    @JsonProperty
    private String designacao;

    @NotNull
    @JsonProperty
    private Double salario;

    @NotBlank
    @JsonProperty
    private String telefone;

    @NotBlank
    @JsonProperty
    private String endereco;

}
