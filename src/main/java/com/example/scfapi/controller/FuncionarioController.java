package com.example.scfapi.controller;

import com.example.scfapi.dominio.Funcionario;
import com.example.scfapi.repository.FuncionarioRepository;
import java.util.InputMismatchException;

public class FuncionarioController {

    // Este método vai centralizar todas as validações da classe Funcionario.
    public boolean isFuncionarioValido(Funcionario funcionario) {
        return isNomeValido(funcionario) && isMatriculaValida(funcionario) && isCPFValido(funcionario);
    }

    private boolean isNomeValido(Funcionario funcionario) {
        if ((funcionario.getNome().isEmpty())) {
            return false;
        }
        return true;
    }

    private FuncionarioRepository funcionarioRepository;
    private boolean isMatriculaValida(Funcionario funcionario) {
        if (funcionario.getMatricula().isEmpty()) return false;
        if (funcionario.getMatricula().length() < 8) return false;
        if (!funcionario.getMatricula().matches("[0-9]+")) return false;
        //Funcionario funcionarioExistente = funcionarioRepository.findByMatricula(funcionario.getMatricula());
        //if (funcionarioExistente != null) {
            //return false;
        //}

        return true;
    }

    private String limparCPF(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }

    public boolean isCPFValido(Funcionario funcionario) {
        funcionario.setCpf(limparCPF(funcionario.getCpf()));
        try {
            if (funcionario.getCpf().equals("00000000000")) return false;
            if (funcionario.getCpf().equals("11111111111")) return false;
            if (funcionario.getCpf().equals("22222222222")) return false;
            if (funcionario.getCpf().equals("33333333333")) return false;
            if (funcionario.getCpf().equals("44444444444")) return false;
            if (funcionario.getCpf().equals("55555555555")) return false;
            if (funcionario.getCpf().equals("66666666666")) return false;
            if (funcionario.getCpf().equals("77777777777")) return false;
            if (funcionario.getCpf().equals("88888888888")) return false;
            if (funcionario.getCpf().equals("99999999999")) return false;
            if (funcionario.getCpf().length() != 11) return false;
            if (!funcionario.getCpf().matches("[0-9]+")) return false;

            return true;
        } catch (InputMismatchException erro) {
            return(false);
        }
    }



}
