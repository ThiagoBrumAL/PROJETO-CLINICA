package com.example.PROJETO_CLINICA.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Medico")
public class MedicoModel {
    public MedicoModel(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private int id;

    @Size(min = 3)
    @NotBlank(message = "Campo Obrigatorio")
    @Column(name="nome", length=100, nullable=true)
    private String nome;

    @Size(min = 5)
    @NotBlank(message = "Campo Obrigatorio")
    @Column(name="crm", length=20, nullable=false)
    private String crm;

    @Column(name="telefone", length=20)
    private String telefone;

    @Email(message = "Email invalido")
    @Column(name="email", length=100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "id_especialidade", referencedColumnName = "id_especialidade")
    private EspecialidadeModel especialidade;

    public MedicoModel(MedicoModel medicoModel) {
    }


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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public EspecialidadeModel getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeModel especialidade) {
        this.especialidade = especialidade;
    }
}
