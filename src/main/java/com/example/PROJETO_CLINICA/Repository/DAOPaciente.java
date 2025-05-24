package com.example.PROJETO_CLINICA.Repository;

import com.example.PROJETO_CLINICA.Model.MedicoModel;
import com.example.PROJETO_CLINICA.Model.PacienteModel;
import com.example.PROJETO_CLINICA.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DAOPaciente extends JpaRepository<PacienteModel, Integer> {
    Optional<PacienteModel> findByCpf(String cpf);
    Optional<PacienteModel> findByTelefone(String telefone);
}
