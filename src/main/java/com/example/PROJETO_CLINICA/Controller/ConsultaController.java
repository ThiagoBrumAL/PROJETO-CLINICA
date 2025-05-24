package com.example.PROJETO_CLINICA.Controller;

import com.example.PROJETO_CLINICA.DTO.Request.ConsultaDTO;
import com.example.PROJETO_CLINICA.Service.ConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("paciente/consulta")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @GetMapping("/listar")
    public ResponseEntity<List<ConsultaDTO>> listar(){
        List<ConsultaDTO> lista = consultaService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@Valid @RequestBody ConsultaDTO dto){return consultaService.atualizar(dto);}

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@Valid @RequestBody ConsultaDTO dto){return consultaService.add(dto);}

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@Valid @PathVariable Integer id){return consultaService.deletar(id);}
}
