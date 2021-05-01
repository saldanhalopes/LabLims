/*
 * Copyright (C) 2019 rafael.lopes
 *
 * Este programa é um software livre: você pode redistribuí-lo e / ou modificar
 * sob os termos da GNU General Public License, conforme publicado pela
 * a Free Software Foundation, seja a versão 3 da Licença, quanto
 * qualquer versão posterior.
 *
 * Este programa é distribuído na esperança de que seja útil,
 * mas SEM QUALQUER GARANTIA; sem a garantia implícita de
 * COMERCIALIZAÇÃO OU APTIDÃO PARA UM PROPÓSITO PARTICULAR. Veja o
 * GNU General Public License para obter mais detalhes.
 *
 * Você deve ter recebido uma cópia da GNU General Public License
 *  juntamente com este programa. Caso contrário, veja <http://www.gnu.org/licenses/>.
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
    private Long id;

    @Column(name = "sistema_nome")
    private String sistemaNome;

    @Column(name = "sistema_criador")
    private String sistemaCriador;

    @Column(name = "sistema_data_update")
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date sistemaDataUpdate;

    @Column(name = "major_versao")
    private Integer majorVersion;

    @Column(name = "minor_versao")
    private Integer minorVersion;

    @Column(name = "path_version")
    private Integer pathVersion;

    @Column(name = "qualifier_version")
    private Integer qualifierVersion;

    @Column(name = "builder_version")
    private Integer builderVersao;

    @Column(name = "detalhes")
    private String detalhes;

    public Sistema() {
    }

    public Sistema(Long id) {
        this.id = id;
    }

    public Sistema(String sistemaNome, String sistemaCriador, Date sistemaDataUpdate, Float sistemaVersao) {
        this.sistemaNome = sistemaNome;
        this.sistemaCriador = sistemaCriador;
        this.sistemaDataUpdate = sistemaDataUpdate;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(Integer majorVersion) {
        this.majorVersion = majorVersion;
    }

    public Integer getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(Integer minorVersion) {
        this.minorVersion = minorVersion;
    }

    public Integer getPathVersion() {
        return pathVersion;
    }

    public void setPathVersion(Integer pathVersion) {
        this.pathVersion = pathVersion;
    }

    public Integer getQualifierVersion() {
        return qualifierVersion;
    }

    public void setQualifierVersion(Integer qualifierVersion) {
        this.qualifierVersion = qualifierVersion;
    }

    public Integer getBuilderVersao() {
        return builderVersao;
    }

    public void setBuilderVersao(Integer builderVersao) {
        this.builderVersao = builderVersao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
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


}
