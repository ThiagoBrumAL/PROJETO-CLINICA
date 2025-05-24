package com.example.PROJETO_CLINICA.Controller;

import com.example.PROJETO_CLINICA.DTO.Request.MedicoRequestDTO;
import com.example.PROJETO_CLINICA.DTO.Response.MedicoResponseDTO;
import com.example.PROJETO_CLINICA.Model.MedicoModel;
import com.example.PROJETO_CLINICA.Service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medico")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @GetMapping("/listar")
    public ResponseEntity<List<MedicoResponseDTO>> listar(){
        List<MedicoResponseDTO> lista = medicoService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> inserir(@Valid @RequestBody MedicoRequestDTO dto){
        return medicoService.add(dto);
    }


    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@Valid @RequestBody MedicoRequestDTO dto){
        return medicoService.atualizar(dto);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@Valid @PathVariable int id){
        return medicoService.deletar(id);
    }




}
