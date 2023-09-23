package br.com.lucasleli.funcionarios.web.controller;

import br.com.lucasleli.funcionarios.application.FuncionarioManager;
import br.com.lucasleli.funcionarios.domain.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/funcionarios")
public class FuncionarioController {


    private final FuncionarioManager service;

    @Autowired
    public FuncionarioController(FuncionarioManager service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Funcionario> create(@RequestBody Funcionario funcionario) {
        Funcionario createdFuncionario = service.addFuncionario(funcionario);
        return new ResponseEntity<>(createdFuncionario, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> getAllNotDeleted() {
        List<Funcionario> funcionarios = service.getAllFuncionariosNotDeleted();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getById(@PathVariable Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioById(id);
        return funcionario.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        Funcionario updatedFuncionario = service.updateFuncionario(id, funcionario);
        if (updatedFuncionario != null) {
            return new ResponseEntity<>(updatedFuncionario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteFuncionario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
