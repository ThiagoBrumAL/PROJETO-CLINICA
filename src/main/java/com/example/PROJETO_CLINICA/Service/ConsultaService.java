package com.example.PROJETO_CLINICA.Service;

import com.example.PROJETO_CLINICA.DTO.Request.ConsultaDTO;
import com.example.PROJETO_CLINICA.Model.ConsultaModel;
import com.example.PROJETO_CLINICA.Model.Enum.StatusCon;
import com.example.PROJETO_CLINICA.Model.MedicoModel;
import com.example.PROJETO_CLINICA.Model.PacienteModel;
import com.example.PROJETO_CLINICA.Repository.DAOConsulta;
import com.example.PROJETO_CLINICA.Repository.DAOMedico;
import com.example.PROJETO_CLINICA.Repository.DAOPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultaService {

    @Autowired
    DAOConsulta DAOconsulta;

    @Autowired
    DAOMedico DAOmedico;

    @Autowired
    DAOPaciente DAOpaciente;


    //Get
    public List<ConsultaDTO> listar(){
        return DAOconsulta.findAll().stream().map(ConsultaDTO::new).collect(Collectors.toList());
    }
    //POST
    public ResponseEntity<?> add(ConsultaDTO dto){

        Optional<MedicoModel> encontraridmedico = DAOmedico.findById(dto.getId_medico());
        Optional<PacienteModel> encontraridpaciente = DAOpaciente.findById(dto.getId_paciente());

        if (!encontraridmedico.isPresent() || !encontraridpaciente.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--id inválido--");
        }

        if(encontraridmedico.isEmpty() && encontraridpaciente.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Os campos não podem estar vazios--");
        }

        ConsultaModel consulta = new ConsultaModel();
        consulta.setMedico(encontraridmedico.get());
        consulta.setPaciente(encontraridpaciente.get());
        if(dto.getStatus() != null){
            consulta.setStatus(dto.getStatus());
        }else{
            consulta.setStatus(StatusCon.AGENDADA);
        }

        consulta.setData_hora(dto.getData_hora());
        ConsultaModel c = DAOconsulta.save(consulta);

        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }
    //Put
    public ResponseEntity<?> atualizar(ConsultaDTO dto){

        Optional<ConsultaModel> alterarId = DAOconsulta.findById(dto.getId());
        if(alterarId.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<MedicoModel> encontraridmedico = DAOmedico.findById(dto.getId_medico());
        if (encontraridmedico.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Medico não encontrado!");
        }

        Optional<PacienteModel> encontraridpaciente = DAOpaciente.findById(dto.getId_paciente());
        if (encontraridpaciente.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Paciente não encontrado!");
        }

        if(dto.getStatus() != StatusCon.AGENDADA && dto.getStatus() != StatusCon.CANCELADA && dto.getStatus() != StatusCon.REALIZADA){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("[PROJETO CLINICA]--Valor de status invalido--");
        }

        ConsultaModel consulta = alterarId.get();
        consulta.setPaciente(encontraridpaciente.get());
        consulta.setMedico(encontraridmedico.get());
        consulta.setData_hora(dto.getData_hora());
        consulta.setStatus(dto.getStatus());

        ConsultaModel c = DAOconsulta.save(consulta);

        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }
    //Delete
    public ResponseEntity<?> deletar(int id) {
        Optional<ConsultaModel> consultamodel = DAOconsulta.findById(id);

        if (!consultamodel.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        DAOconsulta.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
