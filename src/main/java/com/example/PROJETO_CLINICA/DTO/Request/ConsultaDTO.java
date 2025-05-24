package com.example.PROJETO_CLINICA.DTO.Request;

import com.example.PROJETO_CLINICA.Model.ConsultaModel;
import com.example.PROJETO_CLINICA.Model.Enum.StatusCon;

import java.time.LocalDateTime;

public class ConsultaDTO {

    public ConsultaDTO(){};

    private int id;
    private int id_paciente;
    private int id_medico;
    private LocalDateTime data_hora;
    private StatusCon status;

    public ConsultaDTO(ConsultaModel consultaModel){
        this.id = consultaModel.getId();
        this.id_medico = consultaModel.getMedico().getId();
        this.id_paciente = consultaModel.getPaciente().getId_paciente();
        this.data_hora = consultaModel.getData_hora();
        this.status = consultaModel.getStatus();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public LocalDateTime getData_hora() {
        return data_hora;
    }

    public void setData_hora(LocalDateTime data_hora) {
        this.data_hora = data_hora;
    }

    public StatusCon getStatus() {
        return status;
    }

    public void setStatus(StatusCon status) {
        this.status = status;
    }
}
