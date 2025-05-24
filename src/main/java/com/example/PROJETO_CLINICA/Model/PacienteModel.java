package com.example.PROJETO_CLINICA.Model;

import com.example.PROJETO_CLINICA.Model.Enum.Sexo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Paciente")
public class PacienteModel {
    public PacienteModel() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_paciente")
    private int id;

    @Size(min = 3, message = "O nome deve ter no minimo 8 caracteres")
    @NotBlank(message = "Campo Obrigatorio")
    @Column(name="nome", length=100, nullable = false)
    private String nome;

    @Size(min = 11, message = "O cpf deve ter no minimo 14 caracteres")
    @NotBlank(message = "Campo Obrigatorio")
    @Column(name="cpf", length=14, nullable = false, unique = true)
    private String cpf;

    @Email
    @Column(name="email", length=100)
    private String email;

    @Column(name="telefone", length=20)
    private String telefone;

    @Column(name="data_nascimento")
    private LocalDate data_nascimento;

    @Enumerated(EnumType.STRING)
    @Column(name="sexo")
    private Sexo sexo;

    @Column(name="endereco",length=255)
    private String endereco;

    @OneToMany (mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsultaModel> consultas;


    public int getId_paciente() {
        return id;
    }

    public void setId_paciente(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
