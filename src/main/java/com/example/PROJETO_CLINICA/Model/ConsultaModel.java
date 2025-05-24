package com.example.PROJETO_CLINICA.Model;


import com.example.PROJETO_CLINICA.Model.Enum.StatusCon;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Consulta")
public class ConsultaModel {
    public ConsultaModel(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_consulta")
    private int id;

    @ManyToOne
    @JoinColumn(name="id_paciente")
    private PacienteModel paciente;

    @ManyToOne
    @JoinColumn(name="id_medico", referencedColumnName="id_medico")
    private MedicoModel medico;

    @Column(name="data_hora")
    private LocalDateTime data_hora;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private StatusCon status = StatusCon.AGENDADA;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PacienteModel getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteModel id_paciente) {
        this.paciente = id_paciente;
    }

    public MedicoModel getMedico() {
        return medico;
    }

    public void setMedico(MedicoModel id_medico) {
        this.medico = id_medico;
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
