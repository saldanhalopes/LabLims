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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

/**
 *
 * @author rafael
 */
@Entity
@Table(name = "tb_setor")
@NamedQueries({
    @NamedQuery(name = "Setor.findAll", query = "SELECT s FROM Setor s"),
    @NamedQuery(name = "Setor.findBySetorId", query = "SELECT s FROM Setor s WHERE s.id = :id"),
    @NamedQuery(name = "Setor.findBySetor", query = "SELECT s FROM Setor s WHERE s.setor = :setor"),
    @NamedQuery(name = "Setor.findBySiglaSetor", query = "SELECT s FROM Setor s WHERE s.siglaSetor = :siglaSetor"),
    @NamedQuery(name = "Setor.findByDescricaoSetor", query = "SELECT s FROM Setor s WHERE s.descricaoSetor = :descricaoSetor")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_setor_auditoria")
@EntityListeners(AuditListener.class)
public class Setor implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "setor", nullable = false)
    private String setor;

    @Column(name = "sigla_setor", nullable = false)
    private String siglaSetor;

    @Column(name = "descricao_setor")
    private String descricaoSetor;
    
    @Column(name = "tipo")
    private String tipo;

    @Column(name = "version")
    private Integer version;

    @ManyToOne()
    @JoinColumn(name = "departamento_id", referencedColumnName = "id")
    private Departamento departamento;
    
    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean Setor_MOD;

    @Transient
    private Boolean Sigla_Setor_MOD;

    @Transient
    private Boolean Descricao_Setor_MOD;
    
    @Transient
    private Boolean Departamento_MOD;
    
    @Transient
    private Boolean Tipo_MOD;

    public Setor() {
    }

    public Setor(Long id) {
        this.id = id;
    }

    public Setor(String setor) {
        this.setor = setor;
    }

    public Setor(String setor, String siglaSetor, String descricaoSetor) {
        this.setor = setor;
        this.siglaSetor = siglaSetor;
        this.descricaoSetor = descricaoSetor;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getSiglaSetor() {
        return siglaSetor;
    }

    public void setSiglaSetor(String siglaSetor) {
        this.siglaSetor = siglaSetor;
    }

    public String getDescricaoSetor() {
        return descricaoSetor;
    }

    public void setDescricaoSetor(String descricaoSetor) {
        this.descricaoSetor = descricaoSetor;
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

    public Boolean getSetor_MOD() {
        return Setor_MOD;
    }

    public void setSetor_MOD(Boolean Setor_MOD) {
        this.Setor_MOD = Setor_MOD;
    }

    public Boolean getSigla_Setor_MOD() {
        return Sigla_Setor_MOD;
    }

    public void setSigla_Setor_MOD(Boolean Sigla_Setor_MOD) {
        this.Sigla_Setor_MOD = Sigla_Setor_MOD;
    }

    public Boolean getDescricao_Setor_MOD() {
        return Descricao_Setor_MOD;
    }

    public void setDescricao_Setor_MOD(Boolean Descricao_Setor_MOD) {
        this.Descricao_Setor_MOD = Descricao_Setor_MOD;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Boolean getDepartamento_MOD() {
        return Departamento_MOD;
    }

    public void setDepartamento_MOD(Boolean Departamento_MOD) {
        this.Departamento_MOD = Departamento_MOD;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getTipo_MOD() {
        return Tipo_MOD;
    }

    public void setTipo_MOD(Boolean Tipo_MOD) {
        this.Tipo_MOD = Tipo_MOD;
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
        final Setor other = (Setor) obj;
        return Objects.equals(this.id, other.id);
    }

}
