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

import br.com.lablims.audit.Audit;
import br.com.lablims.audit.AuditListener;
import br.com.lablims.interfaces.EntidadeBase;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 *
 * @author rafael.lopes
 */

@Entity
@Table(name = "tb_analise")
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_analise_auditoria")
@EntityListeners(AuditListener.class)
public class Analise implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "analise")
    private String analise;
    
    @Column(name = "descricao_analise")
    private String descricaoAnalise;
    
    @Column(name = "sigla_analise")
    private String siglaAnalise;
    
    @NotAudited
    @ManyToMany(mappedBy = "analise")
    private Set<Campanha> campanha = new HashSet<>();
    
    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean Analise_MOD;
    
    @Transient
    private Boolean Descricao_Analise_MOD;
    
    @Transient
    private Boolean Sigla_Analise_MOD;

    public Analise() {
    }

    public Analise(Long id) {
        this.id = id;
    }

    public Analise(String analise) {
        this.analise = analise;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnalise() {
        return analise;
    }

    public void setAnalise(String analise) {
        this.analise = analise;
    }

    public String getDescricaoAnalise() {
        return descricaoAnalise;
    }

    public void setDescricaoAnalise(String descricaoAnalise) {
        this.descricaoAnalise = descricaoAnalise;
    }

    public String getSiglaAnalise() {
        return siglaAnalise;
    }

    public void setSiglaAnalise(String siglaAnalise) {
        this.siglaAnalise = siglaAnalise;
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

    public Boolean getAnalise_MOD() {
        return Analise_MOD;
    }

    public void setAnalise_MOD(Boolean Analise_MOD) {
        this.Analise_MOD = Analise_MOD;
    }

    public Boolean getDescricao_Analise_MOD() {
        return Descricao_Analise_MOD;
    }

    public void setDescricao_Analise_MOD(Boolean Descricao_Analise_MOD) {
        this.Descricao_Analise_MOD = Descricao_Analise_MOD;
    }

    public Boolean getSigla_Analise_MOD() {
        return Sigla_Analise_MOD;
    }

    public void setSigla_Analise_MOD(Boolean Sigla_Analise_MOD) {
        this.Sigla_Analise_MOD = Sigla_Analise_MOD;
    }

    public Set<Campanha> getCampanha() {
        return campanha;
    }

    public void setCampanha(Set<Campanha> campanha) {
        this.campanha = campanha;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final Analise other = (Analise) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
