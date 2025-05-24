package com.example.PROJETO_CLINICA.Controller;

import com.example.PROJETO_CLINICA.DTO.Request.PagamentoDTO;
import com.example.PROJETO_CLINICA.Service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("paciente/consulta/pagamentos")
public class PagamentoController {

    @Autowired
    PagamentoService pagamentoService;


    @GetMapping("/listar")
    public ResponseEntity<List<PagamentoDTO>> listar(){
        List<PagamentoDTO> lista = pagamentoService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<?> add(@Valid @RequestBody PagamentoDTO dto){return pagamentoService.add(dto);}

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@Valid @RequestBody PagamentoDTO dto){return pagamentoService.atualizar(dto);}

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@Valid @PathVariable Integer id){return pagamentoService.deletar(id);}

}
