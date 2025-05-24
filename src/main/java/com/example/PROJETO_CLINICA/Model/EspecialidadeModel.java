package com.example.PROJETO_CLINICA.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Especialidade")
public class EspecialidadeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_especialidade")
    private int id;

    @Size(min = 4, message = "O nome deve ter no minimo 8 caracteres")
    @NotBlank(message = "Campo Obrigatorio")
    @Column(name="nome",length=100, nullable = false)
    private String nome;

    public EspecialidadeModel(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
