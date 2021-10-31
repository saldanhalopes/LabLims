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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author rafael.lopes
 */
@Entity
@Table(name = "tb_analise_status")
@NamedQueries({
    @NamedQuery(name = "AnaliseStatus.findByStatus", query = "Select a FROM AnaliseStatus a WHERE a.analiseStatus = :status")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_analise_status_auditoria")
@EntityListeners(AuditListener.class)
public class AnaliseStatus implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "analise_status")
    private String analiseStatus;
    
    @Column(name = "sigla_analise_status")
    private String siglaAnaliseStatus;
    
    @Column(name = "descricao_analise_status")
    private String descricaoAnaliseStatus;
    
    @ManyToOne()
    @JoinColumn(name = "analise_produtividade_id", referencedColumnName = "id")
    private AnaliseProdutividade analiseProdutividade;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean MOD;

    @Transient
    private Boolean AnaliseStatus_MOD;

    @Transient
    private Boolean SiglaAnaliseStatus_MOD;

    @Transient
    private Boolean DescricaoAnaliseStatus_MOD;

    @Transient
    private Boolean AnaliseProdutividade_MOD;
    
    public AnaliseStatus() {
    }

    public AnaliseStatus(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnaliseStatus() {
        return analiseStatus;
    }

    public void setAnaliseStatus(String analiseStatus) {
        this.analiseStatus = analiseStatus;
    }

    public String getSiglaAnaliseStatus() {
        return siglaAnaliseStatus;
    }

    public void setSiglaAnaliseStatus(String siglaAnaliseStatus) {
        this.siglaAnaliseStatus = siglaAnaliseStatus;
    }

    public String getDescricaoAnaliseStatus() {
        return descricaoAnaliseStatus;
    }

    public void setDescricaoAnaliseStatus(String descricaoAnaliseStatus) {
        this.descricaoAnaliseStatus = descricaoAnaliseStatus;
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

    public Boolean getMOD() {
        return MOD;
    }

    public void setMOD(Boolean MOD) {
        this.MOD = MOD;
    }

    public Boolean getAnaliseStatus_MOD() {
        return AnaliseStatus_MOD;
    }

    public void setAnaliseStatus_MOD(Boolean AnaliseStatus_MOD) {
        this.AnaliseStatus_MOD = AnaliseStatus_MOD;
    }

    public Boolean getSiglaAnaliseStatus_MOD() {
        return SiglaAnaliseStatus_MOD;
    }

    public void setSiglaAnaliseStatus_MOD(Boolean SiglaAnaliseStatus_MOD) {
        this.SiglaAnaliseStatus_MOD = SiglaAnaliseStatus_MOD;
    }

    public Boolean getDescricaoAnaliseStatus_MOD() {
        return DescricaoAnaliseStatus_MOD;
    }

    public void setDescricaoAnaliseStatus_MOD(Boolean DescricaoAnaliseStatus_MOD) {
        this.DescricaoAnaliseStatus_MOD = DescricaoAnaliseStatus_MOD;
    }

    public AnaliseProdutividade getAnaliseProdutividade() {
        return analiseProdutividade;
    }

    public void setAnaliseProdutividade(AnaliseProdutividade analiseProdutividade) {
        this.analiseProdutividade = analiseProdutividade;
    }

    public Boolean getAnaliseProdutividade_MOD() {
        return AnaliseProdutividade_MOD;
    }

    public void setAnaliseProdutividade_MOD(Boolean AnaliseProdutividade_MOD) {
        this.AnaliseProdutividade_MOD = AnaliseProdutividade_MOD;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final AnaliseStatus other = (AnaliseStatus) obj;
        return Objects.equals(this.id, other.id);
    }


    
}
