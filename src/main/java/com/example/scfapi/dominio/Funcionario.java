package com.example.scfapi.dominio;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="funcionario")
public class Funcionario {

    @Id //define qual será a chave primaria (no caso sera o Id)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 10)
    private String matricula;
    private String nome;
    private String sexo;
    private String cpf;
    private String departamento;
    private String cargo;
    private Double salario;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date nascimento;
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataHoraCadastro;

    public Funcionario() {
        this.setDataHoraCadastro(new Date());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public Date getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(Date dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }
}
