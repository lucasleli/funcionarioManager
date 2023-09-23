package br.com.lucasleli.funcionarios.infrastructure.adapters;

import br.com.lucasleli.funcionarios.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaFuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
