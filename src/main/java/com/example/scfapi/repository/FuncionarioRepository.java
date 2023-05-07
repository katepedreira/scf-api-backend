package com.example.scfapi.repository;

import com.example.scfapi.dominio.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    //default Funcionario findByMatricula(String matricula) {
        //return null;
    //}


}