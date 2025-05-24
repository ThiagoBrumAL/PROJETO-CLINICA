package com.example.PROJETO_CLINICA.DTO.Request;

import com.example.PROJETO_CLINICA.Model.Enum.FormaPagamento;
import com.example.PROJETO_CLINICA.Model.Enum.StatusPag;
import com.example.PROJETO_CLINICA.Model.PagamentoModel;

import java.math.BigDecimal;

public class PagamentoDTO {
    public PagamentoDTO() {};

    private int id_pagamento;
    private int id_consulta;
    private BigDecimal valor;
    private FormaPagamento formaPagamento;
    private StatusPag status;
    private int id_paciente;


    public PagamentoDTO(PagamentoModel pagamentoModel){
        this.id_pagamento = pagamentoModel.getId();
        this.valor = pagamentoModel.getValor();
        this.formaPagamento = pagamentoModel.getFormaPagamento();
        this.status = pagamentoModel.getStatus();
        this.id_consulta = pagamentoModel.getConsulta().getId();
        this.id_medico = pagamentoModel.getConsulta().getMedico().getId();
        this.id_paciente = pagamentoModel.getConsulta().getPaciente().getId_paciente();
    }


    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    private int id_medico;

    public int getId_pagamento() {
        return id_pagamento;
    }

    public void setId_pagamento(int id_pagamento) {
        this.id_pagamento = id_pagamento;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public StatusPag getStatus() {
        return status;
    }

    public void setStatus(StatusPag status) {
        this.status = status;
    }
}
