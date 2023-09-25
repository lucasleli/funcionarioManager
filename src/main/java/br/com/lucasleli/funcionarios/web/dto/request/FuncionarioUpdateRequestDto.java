package br.com.lucasleli.funcionarios.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FuncionarioUpdateRequestDto {

    @NotBlank
    @JsonProperty
    @Schema(description = "Nome do Funcionário", example = "João Silva")
    private String nome;

    @NotBlank
    @JsonProperty
    @Schema(description = "Designação do Funcionário", example = "Engenheiro")
    private String designacao;

    @NotNull
    @JsonProperty
    @Schema(description = "Salário do Funcionário", example = "5000.0")
    private Double salario;

    @NotBlank
    @JsonProperty
    @Schema(description = "Telefone do Funcionário", example = "5511954789869")
    private String telefone;

    @NotBlank
    @JsonProperty
    @Schema(description = "Endereço do Funcionário", example = "Rua A, 123")
    private String endereco;

}
