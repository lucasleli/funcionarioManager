package br.com.lucasleli.funcionarios.infrastructure.adapters;

import br.com.lucasleli.funcionarios.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaFuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findAllByIsDeletedFalse();
    Optional<Funcionario> findByIdAndIsDeletedFalse(Long id);
}
