package com.example.PROJETO_CLINICA.DTO.Request;

import com.example.PROJETO_CLINICA.Model.Enum.Perfil;

public class UsuarioDTO {
    public UsuarioDTO(){};

    private int id;
    private String username;
    private String senha;
    private Perfil perfil;

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

    public String getPassword() {
        return senha;
    }

    public void setPassword(String senha) {
        this.senha = senha;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
