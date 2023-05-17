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


    public boolean isFuncionarioValido(Funcionario funcionario) throws Exception {
        return isNomeValido(funcionario.getNome()) && isMatriculaValida(funcionario.getMatricula()) && isCPFValido(funcionario.getCpf());
    }


    public boolean isCadastroFuncionarioUnico(Funcionario funcionario) throws Exception {
        Funcionario funcMatricula = funcionarioRepository.getByMatricula(funcionario.getMatricula());
        Funcionario funcCpf = funcionarioRepository.getByCpf(funcionario.getCpf());
        if (funcMatricula != null && funcionario.getId() != funcMatricula.getId()) {
            throw new Exception("Matrícula já cadastrada no sistema");
        } else {
            if (funcCpf != null && funcionario.getId() != funcCpf.getId()) {
                throw new Exception("CPF já cadastrado no sistema");
                }
            }
        return true;
    }

    public boolean isFuncionarioExistente(Funcionario funcionario) throws Exception {
        Funcionario funcId = funcionarioRepository.getById(funcionario.getId());
        if (funcId == null) {
            throw new Exception("Funcionário não cadastrado no sistema");
        }
        return true;
    }


    private boolean isNomeValido(String nome) throws Exception {
        if ((nome.isEmpty())) {
            throw new Exception("Nome vazio");
        }
        return true;
    }

    private FuncionarioRepository funcionarioRepository;

    private boolean isMatriculaValida(String matricula) throws Exception {
        if (matricula.isEmpty()) {
            throw new Exception("Matricula Vazia");
        }

        if (!matricula.matches("[0-9]+")) {
            throw new Exception("A matrícula deve conter somente números");
        } else {
            if (matricula.length() != 8) {
                throw new Exception("A matrícula deve conter 8 dígitos");
            }
        }
        return true;
    }

    public boolean isCPFValido(String cpf) throws Exception {
        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999")) {
            throw new Exception("A sequencia de CPF digitada não é válida");
        }

        if (cpf.isEmpty()) {
            throw new Exception("CPF Vazio");
        } else {
            if (!cpf.matches("[0-9]+")) {
                throw new Exception("O CPF deve conter somente números");
            } else {
                if (cpf.length() != 11) {
                    throw new Exception("O CPF deve conter 11 dígitos");
                }
            }
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return (true);
            else {
                throw new Exception("Digite um CPF válido");
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
}

