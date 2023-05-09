package com.example.scfapi.controller;

import com.example.scfapi.dominio.Funcionario;
import com.example.scfapi.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;

public class FuncionarioController {

    public FuncionarioController(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    // Este método vai centralizar todas as validações da classe Funcionario.
    public boolean isFuncionarioValido(Funcionario funcionario) {
        return isNomeValido(funcionario.getNome()) && isMatriculaValida(funcionario.getMatricula()) && isCPFValido(funcionario.getCpf());
    }

    private boolean isNomeValido(String nome) {
        if ((nome.isEmpty())) {
            return false;
        }
        return true;
    }

    private FuncionarioRepository funcionarioRepository;
    private boolean isMatriculaValida(String matricula) {
        if (matricula.isEmpty()) return false;
        if (matricula.length() < 8) return false;
        if (!matricula.matches("[0-9]+")) return false;
        Funcionario funcionarioExistente = funcionarioRepository.getByMatricula(matricula);
        if (funcionarioExistente != null) {
            return false;
        }

        return true;
    }

    private String limparCPF(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }

    public boolean isCPFValido(String cpf) {
        cpf = (limparCPF(cpf));
        try {
            if (cpf.equals("00000000000")) return false;
            if (cpf.equals("11111111111")) return false;
            if (cpf.equals("22222222222")) return false;
            if (cpf.equals("33333333333")) return false;
            if (cpf.equals("44444444444")) return false;
            if (cpf.equals("55555555555")) return false;
            if (cpf.equals("66666666666")) return false;
            if (cpf.equals("77777777777")) return false;
            if (cpf.equals("88888888888")) return false;
            if (cpf.equals("99999999999")) return false;
            if (cpf.length() != 11) return false;
            if (!cpf.matches("[0-9]+")) return false;

            return true;
        } catch (InputMismatchException erro) {
            return(false);
        }
    }



}
