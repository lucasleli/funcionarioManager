package br.com.lucasleli.funcionarios.web.controller;

import br.com.lucasleli.funcionarios.application.FuncionarioManager;
import br.com.lucasleli.funcionarios.domain.Funcionario;
import br.com.lucasleli.funcionarios.web.dto.request.FuncionarioCreateRequestDTO;
import br.com.lucasleli.funcionarios.web.dto.request.FuncionarioUpdateRequestDto;
import br.com.lucasleli.funcionarios.web.dto.response.FuncionarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/funcionarios")
@Tag(name = "Funcionarios", description = "API para gestão de Funcionários")
public class FuncionarioController {


    private final FuncionarioManager service;

    private final ModelMapper modelMapper;

    @Autowired
    public FuncionarioController(FuncionarioManager service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Cria um novo funcionário")
    @PostMapping
    public ResponseEntity<FuncionarioResponseDto> create(@Valid @RequestBody FuncionarioCreateRequestDTO funcionarioDto) {
        Funcionario payload = modelMapper.map(funcionarioDto, Funcionario.class);
        Funcionario createdFuncionario = service.addFuncionario(payload);
        FuncionarioResponseDto response = modelMapper.map(createdFuncionario, FuncionarioResponseDto.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Retorna todos os funcionários")
    @GetMapping
    public ResponseEntity<List<Funcionario>> getAllNotDeleted() {
        List<Funcionario> funcionarios = service.getAllFuncionariosNotDeleted();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @Operation(summary = "Retorna um funcionário específico")
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getById(@PathVariable Long id) {
        Optional<Funcionario> funcionario = service.getFuncionarioNotDeletedById(id);
        return funcionario.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Atualiza um funcionário especifico")
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> update(@PathVariable Long id, @RequestBody FuncionarioUpdateRequestDto funcionarioDto) {
        Funcionario funcionarioUpdate = modelMapper.map(funcionarioDto, Funcionario.class);
        Funcionario updatedFuncionario = service.updateFuncionario(id, funcionarioUpdate);
        if (updatedFuncionario != null) {
            return new ResponseEntity<>(updatedFuncionario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Exclui um funcionário específico")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteFuncionario(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
