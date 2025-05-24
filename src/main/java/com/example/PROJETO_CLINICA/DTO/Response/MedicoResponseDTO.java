package com.example.PROJETO_CLINICA.DTO.Response;

import com.example.PROJETO_CLINICA.Model.MedicoModel;

public class MedicoResponseDTO {

    private String nome;
    private String crm;
    private String telefone;
    private String email;
    private String especialidade;

    public MedicoResponseDTO(MedicoModel medico){
        this.nome = medico.getNome();
        this.crm = medico.getCrm();
        this.email = medico.getEmail();
        this.id = medico.getId();
        this.telefone = medico.getTelefone();
        this.especialidade = (medico.getEspecialidade()!= null)
                ? medico.getEspecialidade().getNome() : "Não informada";
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
