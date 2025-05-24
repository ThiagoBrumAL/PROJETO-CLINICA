package com.example.PROJETO_CLINICA.Model;

import com.example.PROJETO_CLINICA.Model.Enum.Perfil;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Usuario")
public class UsuarioModel {
    public UsuarioModel(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private int id;

    @Size(min = 3, message = "O username deve ter pelo menos 5 caracteres")
    @NotBlank(message = "Campo obrigatorio")
    @Column(name="username", length=255, nullable = false, unique = true)
    private String username;

    @Size(min = 10, message="A senha deve ter pelo menos 10 caracteres ")
    @NotBlank(message = "Campo obrigatorio")
    @Column(name="senha_hash", length=255, nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @jakarta.validation.constraints.NotNull(message = "Campo obrigatorio")
    @Column(name="perfil", nullable = false)
    private Perfil perfil = Perfil.ADMIN;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
