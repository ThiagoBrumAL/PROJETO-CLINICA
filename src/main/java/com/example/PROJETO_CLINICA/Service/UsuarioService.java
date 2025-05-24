package com.example.PROJETO_CLINICA.Service;


import com.example.PROJETO_CLINICA.DTO.Request.UsuarioDTO;
import com.example.PROJETO_CLINICA.Model.Enum.Perfil;
import com.example.PROJETO_CLINICA.Model.UsuarioModel;
import com.example.PROJETO_CLINICA.Repository.DAOUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class UsuarioService {

    @Autowired
    DAOUsuario DAOusuario;
    
    PasswordEncoder encoder;
    public UsuarioService(){
        this.encoder = new BCryptPasswordEncoder();
    }

    //Get
    public List<UsuarioModel> listar(){
        return DAOusuario.findAll();
    }
    //Post
    public ResponseEntity<?> add(UsuarioDTO dto){
        Optional<UsuarioModel> usuarioModelusername = DAOusuario.findByUsername(dto.getUsername());

        if(usuarioModelusername.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Já existe um usuario com este nome--");
        }
        if(dto.getPassword().length() < 10){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Senha com pelo menos 10 caracteres--");
        }

        if (usuarioModelusername.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Já existe um user com este nome--");
        }

        if(dto.getPerfil().equals(Perfil.ADMIN)){
            Optional<UsuarioModel> adminExistente = DAOusuario.findByPerfil(dto.getPerfil());
            if (adminExistente.isPresent()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[PROJETO CLINICA]--Já exite um admin cadastrado | acesso negado--");
            }
        }

        UsuarioModel user = new UsuarioModel();

        user.setSenha(encoder.encode(dto.getPassword()));
        user.setUsername(dto.getUsername());
        user.setPerfil(dto.getPerfil());

        UsuarioModel u = DAOusuario.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(u);
    }
    //Put
    public ResponseEntity<?> atualizar(UsuarioDTO dto){
        Optional<UsuarioModel> usuarioModel = DAOusuario.findByUsername(dto.getUsername());

        if(!usuarioModel.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Usuario não encontrado--");
        }
        if(dto.getPassword().length() < 10){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Senha com pelo menos 10 caracteres--");
        }
        if (dto.getPerfil() == Perfil.ADMIN){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Você não pode utilizar este cargo--");
        }

        UsuarioModel user = usuarioModel.get();
        String senha = encoder.encode(dto.getPassword());
        dto.setPassword(senha);

        user.setSenha(dto.getPassword());
        user.setUsername(dto.getUsername());
        user.setPerfil(dto.getPerfil());

        UsuarioModel u = DAOusuario.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(u);
    }
    //Login
    public ResponseEntity<?> login(UsuarioDTO dto){


        Optional<UsuarioModel> usuarioModelusername = DAOusuario.findByUsername(dto.getUsername());
        if(!usuarioModelusername.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Usuario não encontrado--");
        }

        if(dto.getPassword().length() != 10){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Senha com pelo menos 10 caracteres--");
        }

        UsuarioModel usuario = usuarioModelusername.get();

        if ("admin".equals(usuario.getUsername())) {
            Optional<UsuarioModel> usuarioModelPerfil = DAOusuario.findById(dto.getId());

            if (usuarioModelPerfil.isPresent() && usuarioModelPerfil.get().getPerfil().equals(Perfil.ADMIN)) {
                return ResponseEntity.status(HttpStatus.ACCEPTED)
                        .body("[PROJETO CLINICA]--Acesso Permitido-- |> Seja bem-vindo admin! <|");
            }
        }

        String senha_crip = usuarioModelusername.get().getSenha();

        if (!encoder.matches(dto.getPassword(),senha_crip)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("[PROJETO CLINICA]--Acesso negado--");
        }



        return ResponseEntity.status(HttpStatus.OK).build();
    }
    //Delete
    public ResponseEntity<?> deletar(int id){
        Optional<UsuarioModel> usuarioModel = DAOusuario.findById(id);

        if(!usuarioModel.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Usuario não encontrado--");
        }

        DAOusuario.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("[PROJETO CLINICA]--Usuario deletado--");
    }
}
