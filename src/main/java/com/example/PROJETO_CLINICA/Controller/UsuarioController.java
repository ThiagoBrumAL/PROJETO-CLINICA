package com.example.PROJETO_CLINICA.Controller;

import com.example.PROJETO_CLINICA.DTO.Request.UsuarioDTO;
import com.example.PROJETO_CLINICA.Model.UsuarioModel;
import com.example.PROJETO_CLINICA.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("usuario")
public class UsuarioController {


    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioModel>> listar(){return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listar());}

    @PostMapping("/cadastrar")
    public ResponseEntity<?> add(@RequestBody UsuarioDTO dto){return usuarioService.add(dto);}

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody UsuarioDTO dto){return usuarioService.atualizar(dto);}

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody UsuarioDTO dto ){return usuarioService.login(dto);}

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletar(@Valid @PathVariable Integer id){return usuarioService.deletar(id);}
}
