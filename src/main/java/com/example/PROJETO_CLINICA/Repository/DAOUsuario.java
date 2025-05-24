package com.example.PROJETO_CLINICA.Repository;

import com.example.PROJETO_CLINICA.Model.Enum.Perfil;
import com.example.PROJETO_CLINICA.Model.MedicoModel;
import com.example.PROJETO_CLINICA.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DAOUsuario extends JpaRepository<UsuarioModel, Integer> {
    Optional<UsuarioModel> findByUsername(String username);
    Optional<UsuarioModel> findByPerfil(Perfil perfil);
}
