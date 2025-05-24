package com.example.PROJETO_CLINICA.Controller;

import com.example.PROJETO_CLINICA.DTO.Request.PacienteDTO;
import com.example.PROJETO_CLINICA.Model.PacienteModel;
import com.example.PROJETO_CLINICA.Service.PacienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("paciente")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping("/listar")
    public ResponseEntity<List<PacienteModel>> listar(){return ResponseEntity.status(HttpStatus.OK).body(pacienteService.lista());}

    @PostMapping("/cadastrar")
    public ResponseEntity<?> inserir(@Valid @RequestBody PacienteDTO dto){
        return pacienteService.add(dto);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@Valid @RequestBody PacienteDTO dto){return pacienteService.atualizar(dto);}

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@Valid @PathVariable int id){
        return pacienteService.deletar(id);
    }


}
