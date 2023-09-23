package br.com.lucasleli.funcionarios.infrastructure.adapters;

import br.com.lucasleli.funcionarios.domain.Funcionario;
import br.com.lucasleli.funcionarios.domain.ports.FuncionarioRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FuncionarioRepositoryImpl implements FuncionarioRepositoryI {

    @Autowired
    private JpaFuncionarioRepository repository;

    @Override
    public Funcionario save(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    @Override
    public List<Funcionario> findAllNotDeleted() {
        return repository.findAllByIsDeletedFalse();
    }

    @Override
    public Optional<Funcionario> findByIdAndIsDeletedFalse(Long id) {
        return repository.findByIdAndIsDeletedFalse(id);
    }


    /**
     * Deleta um funcionário pelo ID fornecido.
     * <p>
     * Esta implementação usa exclusão lógica para evitar possíveis problemas de inconsistência
     * com registros no futuro. Em vez de remover o registro do funcionário do banco de dados,
     * ele simplesmente marca o registro como "excluído" definindo o campo {@code deleted} como {@code true}.
     * </p>
     * <p>
     * A exclusão lógica tem várias vantagens, incluindo a capacidade de recuperar registros excluídos,
     * manter a integridade referencial e fornecer um histórico completo de registros para fins de auditoria.
     * </p>
     *
     * @param id O ID do funcionário a ser excluído.
     */
    @Override
    public void delete(Long id) {
        Optional<Funcionario> funcionarioOpt = repository.findById(id);

        if (funcionarioOpt.isPresent()){
            Funcionario funcionario = funcionarioOpt.get();
            funcionario.setIsDeleted(true);
            repository.save(funcionario);
        }
    }

    @Override
    public boolean exists(Long id) {
        return repository.existsById(id);
    }
}
