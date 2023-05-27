package com.example.scfapi.resource;

import com.example.scfapi.controller.FuncionarioController;
import com.example.scfapi.dominio.Funcionario;
import com.example.scfapi.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/funcionario")
public class FuncionarioResource {
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    @GetMapping(value ="/list")
    public List<Funcionario> list() {
        return funcionarioRepository.findAll();
    }

    @PostMapping("/create")
        public ResponseEntity<Funcionario> create(@RequestBody  Funcionario funcionario) {
        FuncionarioController funcionarioController = new FuncionarioController(funcionarioRepository);
        try {
            if (funcionarioController.isFuncionarioValido(funcionario) && funcionarioController.isCadastroFuncionarioUnico(funcionario)) {
                funcionario.setDataHoraCadastro(new Date());
                funcionario = funcionarioRepository.save(funcionario);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(funcionario, HttpStatus.OK);

    }

//    @GetMapping("getById/{id}")  //original
//    public Optional<Funcionario> getById(@PathVariable(value = "id") int id) {
//        return funcionarioRepository.findById(id);
//    }

    @GetMapping("getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        FuncionarioController funcionarioController = new FuncionarioController(funcionarioRepository);
        try {
            Funcionario funcionario = new Funcionario();
            funcionario.setId(id);

            boolean funcionarioExistente = funcionarioController.isFuncionarioExistente(funcionario);

            if (!funcionarioExistente) {
                return new ResponseEntity<>("Funcionário não encontrado", HttpStatus.NOT_FOUND);
            }

            Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);

            if (optionalFuncionario.isPresent()) {
                funcionario = optionalFuncionario.get();
                return ResponseEntity.ok(funcionario);
            } else {
                return new ResponseEntity<>("Funcionário não encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit")
    public ResponseEntity<Funcionario> editar(@RequestBody Funcionario funcionario) {
        FuncionarioController funcionarioController = new FuncionarioController(funcionarioRepository);
        try {
            if (funcionarioController.isFuncionarioExistente(funcionario) && funcionarioController.isFuncionarioValido(funcionario) && funcionarioController.isCadastroFuncionarioUnico(funcionario)) {
                funcionario = funcionarioRepository.save(funcionario);
                }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity(funcionario, HttpStatus.OK);
    }

    @GetMapping("/total")
    public long getTotal() {
        return funcionarioRepository.count();
    }

    @DeleteMapping("/remove/{id}")
    public Funcionario remove(@PathVariable(value = "id") int id) {
        Optional<Funcionario> optionalAluno = funcionarioRepository.findById(id);
        funcionarioRepository.delete(optionalAluno.get());
        return optionalAluno.get();
    }




}
