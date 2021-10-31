/*
 * Copyright (C) 2018 rafael.lopes
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
package br.com.cristalia.biblioteca.model;

import br.com.cristalia.biblioteca.audit.Audit;
import br.com.cristalia.biblioteca.audit.AuditListener;
import br.com.cristalia.biblioteca.interfaces.EntidadeBase;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import javax.persistence.Transient;

/**
 *
 * @author rafael
 */
@Entity
@Table(name = "tb_departamento")
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_departamento_auditoria")
@EntityListeners(AuditListener.class)
public class Departamento implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departamento")
    private String departamento;

    @Column(name = "sigla_departamento")
    private String siglaDepartamento;

    @Column(name = "descricao_departamento")
    private String descricaoDepartamento;

    @Column(name = "version")
    private Integer version;
    
    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean Departamento_MOD;

    @Transient
    private Boolean Sigla_Departamento_MOD;

    @Transient
    private Boolean Descricao_Departamento_MOD;

    public Departamento() {
    }

    public Departamento(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getSiglaDepartamento() {
        return siglaDepartamento;
    }

    public void setSiglaDepartamento(String siglaDepartamento) {
        this.siglaDepartamento = siglaDepartamento;
    }

    public String getDescricaoDepartamento() {
        return descricaoDepartamento;
    }

    public void setDescricaoDepartamento(String descricaoDepartamento) {
        this.descricaoDepartamento = descricaoDepartamento;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Boolean getDepartamento_MOD() {
        return Departamento_MOD;
    }

    public void setDepartamento_MOD(Boolean Departamento_MOD) {
        this.Departamento_MOD = Departamento_MOD;
    }

    public Boolean getSigla_Departamento_MOD() {
        return Sigla_Departamento_MOD;
    }

    public void setSigla_Departamento_MOD(Boolean Sigla_Departamento_MOD) {
        this.Sigla_Departamento_MOD = Sigla_Departamento_MOD;
    }

    public Boolean getDescricao_Departamento_MOD() {
        return Descricao_Departamento_MOD;
    }

    public void setDescricao_Departamento_MOD(Boolean Descricao_Departamento_MOD) {
        this.Descricao_Departamento_MOD = Descricao_Departamento_MOD;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Departamento other = (Departamento) obj;
        return Objects.equals(this.id, other.id);
    }

}
