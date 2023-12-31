package br.com.lucasleli.funcionarios.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class FuncionarioResponseDto {

    @Schema(description = "ID do Funcionário", example = "1")
    private Long id;

    @Schema(description = "Data de criação do recurso", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date createdAt;

    @Schema(description = "Data da última atualização do recurso", pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date updatedAt;

    @Schema(description = "Nome do Funcionário", example = "João Silva")
    private String nome;

    @Schema(description = "Designação do Funcionário", example = "Engenheiro")
    private String designacao;

    @Schema(description = "Salário do Funcionário", example = "5000.0")
    private Double salario;

    @Schema(description = "Telefone do Funcionário", example = "999999999")
    private String telefone;

    @Schema(description = "Endereço do Funcionário", example = "Rua A, 123")
    private String endereco;

}
