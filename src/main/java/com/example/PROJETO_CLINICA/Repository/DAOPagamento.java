package com.example.PROJETO_CLINICA.Repository;

import com.example.PROJETO_CLINICA.Model.PagamentoModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DAOPagamento extends JpaRepository<PagamentoModel, Integer> {
}
