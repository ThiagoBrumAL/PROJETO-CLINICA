package com.example.PROJETO_CLINICA.Repository;

import com.example.PROJETO_CLINICA.Model.Enum.Perfil;
import com.example.PROJETO_CLINICA.Model.EspecialidadeModel;
import com.example.PROJETO_CLINICA.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DAOEspecialidade extends JpaRepository<EspecialidadeModel, Integer> {
}
