package com.example.scfapi.repository;

import com.example.scfapi.dominio.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    public Funcionario getByMatricula(String matricula);
    public Funcionario getByCpf(String cpf);
    public Funcionario getById(long id);


}