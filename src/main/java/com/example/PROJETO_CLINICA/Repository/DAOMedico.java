package com.example.PROJETO_CLINICA.Repository;

import com.example.PROJETO_CLINICA.Model.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DAOMedico extends JpaRepository<MedicoModel, Integer> {
    Optional<MedicoModel> findByCrm(String crm);
    Optional<MedicoModel> findByEmail(String email);
    Optional<MedicoModel> findByTelefone(String telefone);
}
