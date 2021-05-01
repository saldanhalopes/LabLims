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
import br.com.lablims.audit.AuditListener;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import br.com.lablims.interfaces.EntidadeBase;
import java.util.Objects;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 * <code>PlanoAnalise</code> classe PlanoAnalise
 *
 * @author rafae.lopes
 * @version 1.00
 */
@Entity
@Table(name = "tb_plano_analise")
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_plano_analise_auditoria")
@EntityListeners(AuditListener.class)
public class PlanoAnalise implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lead_time_min")
    private Integer leadTimeMin;
    
    @Column(name = "lead_time_medio")
    private Integer leadTimeMedio;
    
    @Column(name = "lead_time_max")
    private Integer leadTimeMax;
    
    @Column(name = "descricao")
    private String descricao;
    
    @ManyToOne()
    @JoinColumn(name = "metodologia_id", referencedColumnName = "id")
    private Metodologia metodologia;

    @ManyToOne()
    @JoinColumn(name = "analise_id", referencedColumnName = "id")
    private Analise analise;

    @ManyToOne()
    @JoinColumn(name = "analise_tipo_id", referencedColumnName = "id")
    private AnaliseTipo analiseTipo;

    @ManyToOne()
    @JoinColumn(name = "setor_id", referencedColumnName = "id")
    private Setor setor;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean LeadTimeMin_MOD;
    
    @Transient
    private Boolean LeadTimeMedio_MOD;
    
    @Transient
    private Boolean LeadTimeMax_MOD;
    
    @Transient
    private Boolean Versao_MOD;
    
    @Transient
    private Boolean Analise_MOD;
    
    @Transient
    private Boolean AnaliseTipo_MOD;
    
    @Transient
    private Boolean Setor_MOD;

    @Transient
    private Boolean Descricao_MOD;
    
    public PlanoAnalise() {
    }

    public PlanoAnalise(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLeadTimeMin() {
        return leadTimeMin;
    }

    public void setLeadTimeMin(Integer leadTimeMin) {
        this.leadTimeMin = leadTimeMin;
    }

    public Integer getLeadTimeMedio() {
        return leadTimeMedio;
    }

    public void setLeadTimeMedio(Integer leadTimeMedio) {
        this.leadTimeMedio = leadTimeMedio;
    }

    public Integer getLeadTimeMax() {
        return leadTimeMax;
    }

    public void setLeadTimeMax(Integer leadTimeMax) {
        this.leadTimeMax = leadTimeMax;
    }

    public Metodologia getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(Metodologia metodologia) {
        this.metodologia = metodologia;
    }

    public Analise getAnalise() {
        return analise;
    }

    public void setAnalise(Analise analise) {
        this.analise = analise;
    }

    public AnaliseTipo getAnaliseTipo() {
        return analiseTipo;
    }

    public void setAnaliseTipo(AnaliseTipo analiseTipo) {
        this.analiseTipo = analiseTipo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getLeadTimeMin_MOD() {
        return LeadTimeMin_MOD;
    }

    public void setLeadTimeMin_MOD(Boolean LeadTimeMin_MOD) {
        this.LeadTimeMin_MOD = LeadTimeMin_MOD;
    }

    public Boolean getLeadTimeMedio_MOD() {
        return LeadTimeMedio_MOD;
    }

    public void setLeadTimeMedio_MOD(Boolean LeadTimeMedio_MOD) {
        this.LeadTimeMedio_MOD = LeadTimeMedio_MOD;
    }

    public Boolean getLeadTimeMax_MOD() {
        return LeadTimeMax_MOD;
    }

    public void setLeadTimeMax_MOD(Boolean LeadTimeMax_MOD) {
        this.LeadTimeMax_MOD = LeadTimeMax_MOD;
    }

    public Boolean getVersao_MOD() {
        return Versao_MOD;
    }

    public void setVersao_MOD(Boolean Versao_MOD) {
        this.Versao_MOD = Versao_MOD;
    }

    public Boolean getAnalise_MOD() {
        return Analise_MOD;
    }

    public void setAnalise_MOD(Boolean Analise_MOD) {
        this.Analise_MOD = Analise_MOD;
    }

    public Boolean getAnaliseTipo_MOD() {
        return AnaliseTipo_MOD;
    }

    public void setAnaliseTipo_MOD(Boolean AnaliseTipo_MOD) {
        this.AnaliseTipo_MOD = AnaliseTipo_MOD;
    }

    public Boolean getSetor_MOD() {
        return Setor_MOD;
    }

    public void setSetor_MOD(Boolean Setor_MOD) {
        this.Setor_MOD = Setor_MOD;
    }

    public Boolean getDescricao_MOD() {
        return Descricao_MOD;
    }

    public void setDescricao_MOD(Boolean Descricao_MOD) {
        this.Descricao_MOD = Descricao_MOD;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
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
        final PlanoAnalise other = (PlanoAnalise) obj;
        return Objects.equals(this.id, other.id);
    }
    
}
