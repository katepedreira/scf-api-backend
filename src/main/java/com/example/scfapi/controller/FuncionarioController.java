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
        Funcionario funcionarioExistente = funcionarioRepository.findByMatricula(funcionario.getMatricula());
        if (funcionarioExistente != null) {
            return false;
        }

        return true;
    }

    private String limparCPF(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }

    public boolean isCPFValido(Funcionario funcionario) {
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

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(funcionario.getCpf().charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(funcionario.getCpf().charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados e padroniza o cpf.
            if ((dig10 == funcionario.getCpf().charAt(9)) && (dig11 == funcionario.getCpf().charAt(10))) {
                limparCPF(funcionario.getCpf());
                return (true);
            }
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }



}
