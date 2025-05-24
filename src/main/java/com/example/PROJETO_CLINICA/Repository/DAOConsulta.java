package com.example.PROJETO_CLINICA.Repository;

import com.example.PROJETO_CLINICA.Model.ConsultaModel;
import com.example.PROJETO_CLINICA.Model.Enum.Perfil;
import com.example.PROJETO_CLINICA.Model.Enum.StatusCon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DAOConsulta extends JpaRepository<ConsultaModel, Integer> {
}
