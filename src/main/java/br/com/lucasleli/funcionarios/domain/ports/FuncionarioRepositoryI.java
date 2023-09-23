package br.com.lucasleli.funcionarios.domain.ports;

import br.com.lucasleli.funcionarios.domain.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepositoryI {
    Funcionario save(Funcionario funcionario);
    List<Funcionario> findAllNotDeleted();
    Optional<Funcionario> findByIdAndIsDeletedFalse(Long id);
    void delete(Long id);
    boolean exists(Long id);
}
