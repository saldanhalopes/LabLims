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
package br.com.lablims.model;

import br.com.lablims.audit.Audit;
import br.com.lablims.interfaces.EntidadeBase;
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
import br.com.lablims.audit.AuditListener;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

/**
 *
 * @author rafael
 */
@Entity
@Table(name = "tb_unidade_medida")
@NamedQueries({
    @NamedQuery(name = "UnidadeMedida.findAll", query = "SELECT un FROM UnidadeMedida un"),
    @NamedQuery(name = "UnidadeMedida.findByUnidade", query = "SELECT un FROM UnidadeMedida un WHERE un.unidade = :unidade")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_unidade_medida_auditoria")
@EntityListeners(AuditListener.class)
public class UnidadeMedida implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "unidade", nullable = false)
    private String unidade;

    @ManyToOne()
    @JoinColumn(name = "escala_id", referencedColumnName = "id")
    private EscalaMedida escala;
    
    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean Unidade_MOD;
    
    @Transient
    private Boolean Escala_MOD;


    public UnidadeMedida() {
    }

    public UnidadeMedida(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public EscalaMedida getEscala() {
        return escala;
    }

    public void setEscala(EscalaMedida escala) {
        this.escala = escala;
    }

    public Boolean getEscala_MOD() {
        return Escala_MOD;
    }

    public void setEscala_MOD(Boolean Escala_MOD) {
        this.Escala_MOD = Escala_MOD;
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

    public Boolean getUnidade_MOD() {
        return Unidade_MOD;
    }

    public void setUnidade_MOD(Boolean Unidade_MOD) {
        this.Unidade_MOD = Unidade_MOD;
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
        final UnidadeMedida other = (UnidadeMedida) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "UnidadeMedida{" + "id=" + id + ", unidade=" + unidade + ", version=" + version + ", audit=" + audit + ", Unidade_MOD=" + Unidade_MOD + '}';
    }

}
