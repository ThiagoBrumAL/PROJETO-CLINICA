package com.example.PROJETO_CLINICA.Service;

import com.example.PROJETO_CLINICA.DTO.Request.PacienteDTO;
import com.example.PROJETO_CLINICA.Model.*;
import com.example.PROJETO_CLINICA.Model.Enum.Sexo;
import com.example.PROJETO_CLINICA.Repository.DAOConsulta;
import com.example.PROJETO_CLINICA.Repository.DAOPaciente;
import com.example.PROJETO_CLINICA.Repository.DAOPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private DAOPaciente DAOpaciente;
    @Autowired
    DAOConsulta DAOconsulta;
    @Autowired
    DAOPagamento DAOpagamento;

    //Get
    public List<PacienteModel> lista(){
        return DAOpaciente.findAll();
    }
    //Post
    public ResponseEntity<?> add(PacienteDTO dto){

        if(dto.getSexo() != Sexo.Masculino && dto.getSexo() != Sexo.Feminino && dto.getSexo() != Sexo.Outro){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Sexo não encontrado--");
        }

        Optional<PacienteModel> pacienteComMesmoCpf = DAOpaciente.findByCpf(dto.getCpf());
        if(pacienteComMesmoCpf.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Já existe um usuario com este cpf--");
        }
        if(dto.getCpf().length() != 12){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--O cpf precisa ter 12 numeros--");
        }

        Optional<PacienteModel> telExistente = DAOpaciente.findByTelefone(dto.getTelefone());
        if (telExistente.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Já existe um paciente com este telefone--");
        }
        if(dto.getTelefone().length() != 13){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Telefone inválido--");
        }

        PacienteModel paciente = new PacienteModel();
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setEndereco(dto.getEndereco());
        paciente.setSexo(dto.getSexo());
        paciente.setTelefone(dto.getTelefone());
        paciente.setData_nascimento(dto.getData_nascimento());

        PacienteModel p = DAOpaciente.save(paciente);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    //Put
    public ResponseEntity<?> atualizar(PacienteDTO dto){
        Optional<PacienteModel> pacienteModel = DAOpaciente.findById(dto.getId());

        if(pacienteModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        //Cpf
        Optional<PacienteModel> cpfExistente = DAOpaciente.findByCpf(dto.getCpf());
        if(cpfExistente.isPresent() && !Objects.equals(cpfExistente.get().getId_paciente(), dto.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Este cpf não pertence a este paciente--");
        }
        if(dto.getCpf().length() != 12){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--O cpf precisa ter 12 digitos--");
        }

        //Telefone
        Optional<PacienteModel> telExistente = DAOpaciente.findByTelefone(dto.getTelefone());
        if (telExistente.isPresent() && !Objects.equals(telExistente.get().getId_paciente() , dto.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Este telefone não pertence a este paciente--");
        }
        if (dto.getTelefone().length() != 13){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Campo telefone inválido-");
        }
        PacienteModel paciente = pacienteModel.get();
        paciente.setNome(dto.getNome());
        paciente.setCpf(dto.getCpf());
        paciente.setSexo(dto.getSexo());
        paciente.setTelefone(dto.getTelefone());
        paciente.setData_nascimento(dto.getData_nascimento());
        paciente.setEndereco(dto.getEndereco());

        PacienteModel p = DAOpaciente.save(paciente);

        return ResponseEntity.status(HttpStatus.OK).body(p);
    }
    //Delete
    public ResponseEntity<?> deletar(int id){
        Optional<PacienteModel> pacienteModel = DAOpaciente.findById(id);

        if(!pacienteModel.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        DAOpaciente.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
