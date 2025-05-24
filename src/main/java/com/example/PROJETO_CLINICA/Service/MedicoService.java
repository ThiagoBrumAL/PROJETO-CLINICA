package com.example.PROJETO_CLINICA.Service;

import com.example.PROJETO_CLINICA.DTO.Request.MedicoRequestDTO;
import com.example.PROJETO_CLINICA.DTO.Response.MedicoResponseDTO;
import com.example.PROJETO_CLINICA.Model.EspecialidadeModel;
import com.example.PROJETO_CLINICA.Model.MedicoModel;
import com.example.PROJETO_CLINICA.Repository.DAOEspecialidade;
import com.example.PROJETO_CLINICA.Repository.DAOMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicoService {

    @Autowired
    private DAOMedico DAOMedico;

    @Autowired
    private DAOEspecialidade DAOEspecialidade;

    //Get
    public List<MedicoResponseDTO> listar() {
        return DAOMedico.findAll().stream()
                .map(MedicoResponseDTO::new)
                .collect(Collectors.toList());
    }
    //Post
    public ResponseEntity<?> add(MedicoRequestDTO dto){

        Optional<EspecialidadeModel> especialidadeModel = DAOEspecialidade.findById(dto.getId_especialidade());

        if(especialidadeModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Especialidade não encontrada--");
        }

        if (dto.getNome().length() < 3){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Nome inválido-");
        }


        Optional<MedicoModel> crmExistente = DAOMedico.findByCrm(dto.getCrm());
        if(crmExistente.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Já existe um medico com este crm--");
        }
        if (dto.getCrm().length() != 5){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--O crm deve ter 5 digitos-");
        }


        if (dto.getEmail() == null || !dto.getEmail().contains("@")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Campo email inválido--");
        }

        Optional<MedicoModel> emailexistente = DAOMedico.findByEmail(dto.getEmail());
        if (emailexistente.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Já existe um medico com este email--");
        }

        Optional<MedicoModel> telExistente = DAOMedico.findByTelefone(dto.getTelefone());
        if (telExistente.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Já existe um medico com este telefone--");
        }
        if (dto.getTelefone().length() != 13){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Campo telefone inválido-");
        }


        MedicoModel medico = new MedicoModel();
        medico.setNome(dto.getNome());
        medico.setCrm(dto.getCrm());
        medico.setEmail(dto.getEmail());
        medico.setTelefone(dto.getTelefone());
        medico.setEspecialidade(especialidadeModel.get());

        MedicoModel m = DAOMedico.save(medico);

        MedicoResponseDTO dtoresponse = new MedicoResponseDTO(m);

        return ResponseEntity.status(HttpStatus.CREATED).body(dtoresponse);
    }
    //Put
    public ResponseEntity<?> atualizar(MedicoRequestDTO dto){


            //ID
            Optional<MedicoModel> medicoModel = DAOMedico.findById(dto.getId());
            if(medicoModel.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--ID inválido--");

            }

            //Nome
            if (dto.getNome().length() < 3){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Nome inválido-");
            }

            //Telefone
            Optional<MedicoModel> telExistente = DAOMedico.findByTelefone(dto.getTelefone());
            if (telExistente.isPresent() && !Objects.equals(telExistente.get().getId() , dto.getTelefone())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Já existe um medico com este telefone--");
            }
            if (dto.getTelefone().length() != 13){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Campo telefone inválido-");
            }

            //Email
            if (dto.getEmail() == null || !dto.getEmail().contains("@")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Campo email inválido--");
            }
            Optional<MedicoModel> emailexistente = DAOMedico.findByEmail(dto.getEmail());
            if (emailexistente.isPresent() && !Objects.equals(emailexistente.get().getId() , dto.getId())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Já existe um medico com este email--");
            }


            //Especialidade
            Optional<EspecialidadeModel> especialidadeModel = DAOEspecialidade.findById(dto.getId_especialidade());
            if(especialidadeModel.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Especialidade não encontrada--");
            }

            //Crm
            Optional<MedicoModel> crmExistente = DAOMedico.findByCrm(dto.getCrm());
            if(crmExistente.isPresent() &&  !Objects.equals(crmExistente.get().getId(), dto.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
            if (dto.getCrm().length() != 5){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--O crm deve ter 5 digitos--");
            }



        MedicoModel medico = medicoModel.get();
            medico.setNome(dto.getNome());
            medico.setCrm(dto.getCrm());
            medico.setEmail(dto.getEmail());
            medico.setTelefone(dto.getTelefone());
            medico.setEspecialidade(especialidadeModel.get());

            MedicoModel m = DAOMedico.save(medico);

            MedicoResponseDTO dtoresponse = new MedicoResponseDTO(m);

            return ResponseEntity.status(HttpStatus.OK).body(dtoresponse);
    }
    //Delete
    public ResponseEntity<?> deletar(int id){
        Optional<MedicoModel> medicoModel = DAOMedico.findById(id);

        if(!medicoModel.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        DAOMedico.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
