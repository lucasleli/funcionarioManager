package br.com.lucasleli.funcionarios.application;

import br.com.lucasleli.funcionarios.domain.Funcionario;
import br.com.lucasleli.funcionarios.domain.ports.FuncionarioRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FuncionarioManager {

    private final FuncionarioRepositoryI repository;

    @Autowired
    public FuncionarioManager(FuncionarioRepositoryI repository) {
        this.repository = repository;
    }

    public Funcionario addFuncionario(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    public List<Funcionario> getAllFuncionariosNotDeleted() {
        return repository.findAllNotDeleted();
    }

    public Optional<Funcionario> getFuncionarioNotDeletedById(Long id) {
        Optional<Funcionario> funcionario = repository.findByIdAndIsDeletedFalse(id);
        if (funcionario.isEmpty()) {
            throw new NoSuchElementException("Funcionário não encontrado");
        }
        return funcionario;
    }

    public Funcionario updateFuncionario(Long id, Funcionario funcionario) {
        if (!repository.exists(id)) {
            throw new NoSuchElementException("Funcionário não encontrado");
        }
        funcionario.setId(id);
        return repository.save(funcionario);
    }

    public void deleteFuncionario(Long id) {
        if (!repository.exists(id)) {
            throw new NoSuchElementException("Funcionário não encontrado");
        }
        repository.delete(id);
    }

}
