/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lablims.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.UpdateTimestamp;
import br.com.lablims.interfaces.EntidadeBase;

/**
 *
 * @author rafael.lopes
 */
@Entity
@Table(name = "tb_sistema")
public class Sistema implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "sistema_nome", nullable = true)
    private String sistemaNome;

    @Column(name = "sistema_criador", nullable = true)
    private String sistemaCriador;

    @Column(name = "sistema_data_update", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date sistemaDataUpdate;

    @Column(name = "sistema_versao", nullable = true)
    private Float sistemaVersao;

    public Sistema() {
    }

    public Sistema(Integer id) {
        this.id = id;
    }

    public Sistema(String sistemaNome, String sistemaCriador, Date sistemaDataUpdate, Float sistemaVersao) {
        this.sistemaNome = sistemaNome;
        this.sistemaCriador = sistemaCriador;
        this.sistemaDataUpdate = sistemaDataUpdate;
        this.sistemaVersao = sistemaVersao;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSistemaNome() {
        return sistemaNome;
    }

    public void setSistemaNome(String sistemaNome) {
        this.sistemaNome = sistemaNome;
    }

    public String getSistemaCriador() {
        return sistemaCriador;
    }

    public void setSistemaCriador(String sistemaCriador) {
        this.sistemaCriador = sistemaCriador;
    }

    public Date getSistemaDataUpdate() {
        return sistemaDataUpdate;
    }

    public void setSistemaDataUpdate(Date sistemaDataUpdate) {
        this.sistemaDataUpdate = sistemaDataUpdate;
    }

    public Float getSistemaVersao() {
        return sistemaVersao;
    }

    public void setSistemaVersao(Float sistemaVersao) {
        this.sistemaVersao = sistemaVersao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sistema other = (Sistema) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Sistema{" + "id=" + id + ", sistemaNome=" + sistemaNome + ", sistemaCriador=" + sistemaCriador + ", sistemaDataUpdate=" + sistemaDataUpdate + ", sistemaVersao=" + sistemaVersao + '}';
    }

    
}
