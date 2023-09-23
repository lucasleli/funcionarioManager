package br.com.lucasleli.funcionarios.domain;

import br.com.lucasleli.funcionarios.common.Auditable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Funcionario extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean isDeleted = false;
    private String nome;
    private String designacao;
    private Double salario;
    private String telefone;
    private String endereco;
}
