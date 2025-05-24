package com.example.PROJETO_CLINICA.Service;

import com.example.PROJETO_CLINICA.DTO.Request.PagamentoDTO;
import com.example.PROJETO_CLINICA.Model.ConsultaModel;
import com.example.PROJETO_CLINICA.Model.PagamentoModel;
import com.example.PROJETO_CLINICA.Repository.DAOConsulta;
import com.example.PROJETO_CLINICA.Repository.DAOPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagamentoService {

    @Autowired
    DAOPagamento DAOpagamento;
    @Autowired
    DAOConsulta DAOconsulta;


    //Get
    public List<PagamentoDTO> listar(){
        return DAOpagamento.findAll().stream()
                .map(PagamentoDTO::new)
                .collect(Collectors.toList());
    }
    //Post
    public ResponseEntity<?> add(PagamentoDTO dto){
        Optional<ConsultaModel> encontraridconsulta = DAOconsulta.findById(dto.getId_consulta());

        if (!encontraridconsulta.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        PagamentoModel pagamento = new PagamentoModel();
        pagamento.setFormaPagamento(dto.getFormaPagamento());
        pagamento.setConsulta(encontraridconsulta.get());
        pagamento.setStatus(dto.getStatus());
        pagamento.setValor(dto.getValor());

        PagamentoModel p = DAOpagamento.save(pagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(p);
    }
    //Put
    public ResponseEntity<?> atualizar(PagamentoDTO dto){
        Optional<PagamentoModel> encontraridpagamento = DAOpagamento.findById(dto.getId_pagamento());

        if (!encontraridpagamento.isPresent()){
            System.out.println("ID PAGAMENTO: "+dto.getId_pagamento());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        PagamentoModel pagamento = encontraridpagamento.get();
        pagamento.setFormaPagamento(dto.getFormaPagamento());
        pagamento.setStatus(dto.getStatus());
        pagamento.setValor(dto.getValor());

        PagamentoModel p = DAOpagamento.save(pagamento);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }
    //Delete
    public ResponseEntity<?> deletar(int id){
        Optional<PagamentoModel> pagamentoModel = DAOpagamento.findById(id);

        if(!pagamentoModel.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        DAOpagamento.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
