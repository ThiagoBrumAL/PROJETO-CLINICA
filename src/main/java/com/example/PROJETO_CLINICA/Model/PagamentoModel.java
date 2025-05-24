package com.example.PROJETO_CLINICA.Model;

import com.example.PROJETO_CLINICA.Model.Enum.FormaPagamento;
import com.example.PROJETO_CLINICA.Model.Enum.StatusPag;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="Pagamento")
public class PagamentoModel {

    public PagamentoModel(){}

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_pagamento")
    private int id;

    @ManyToOne
    @JoinColumn(name="id_consulta", referencedColumnName = "id_consulta", unique = true)
    private ConsultaModel consulta;

    @jakarta.validation.constraints.NotNull(message = "Campo obrigatorio")
    @Column(name="valor", nullable = false)
    private BigDecimal valor;


    @jakarta.validation.constraints.NotNull(message = "Campo obrigatorio")
    @Enumerated(EnumType.STRING)
    @Column(name="forma_pagamento", nullable = false)
    private FormaPagamento formaPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private StatusPag status = StatusPag.PENDENTE;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ConsultaModel getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaModel consulta) {
        this.consulta = consulta;
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
        this.status = (status == null) ? this.status = StatusPag.PENDENTE: status;
    }
}
