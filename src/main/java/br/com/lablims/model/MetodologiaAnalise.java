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
 * <code>MetodologiaAnalise</code> classe MetodologiaAnalise
 *
 * @author rafae.lopes
 * @version 1.00
 */
@Entity
@Table(name = "tb_metodologia_analise")
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_metodologia_analise_auditoria")
@EntityListeners(AuditListener.class)
public class MetodologiaAnalise implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lead_time_min")
    private Integer leadTimeMin;
    
    @Column(name = "lead_time_medio")
    private Integer leadTimeMedio;
    
    @Column(name = "lead_time_max")
    private Integer leadTimeMax;
    
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
    private Integer MOD;

    @Transient
    private Integer LeadTimeMin_MOD;
    
    @Transient
    private Integer LeadTimeMedio_MOD;
    
    @Transient
    private Integer LeadTimeMax_MOD;
    
    @Transient
    private Integer Versao_MOD;

    public MetodologiaAnalise() {
    }

    public MetodologiaAnalise(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getMOD() {
        return MOD;
    }

    public void setMOD(Integer MOD) {
        this.MOD = MOD;
    }

    public Integer getLeadTimeMin_MOD() {
        return LeadTimeMin_MOD;
    }

    public void setLeadTimeMin_MOD(Integer LeadTimeMin_MOD) {
        this.LeadTimeMin_MOD = LeadTimeMin_MOD;
    }

    public Integer getLeadTimeMedio_MOD() {
        return LeadTimeMedio_MOD;
    }

    public void setLeadTimeMedio_MOD(Integer LeadTimeMedio_MOD) {
        this.LeadTimeMedio_MOD = LeadTimeMedio_MOD;
    }

    public Integer getLeadTimeMax_MOD() {
        return LeadTimeMax_MOD;
    }

    public void setLeadTimeMax_MOD(Integer LeadTimeMax_MOD) {
        this.LeadTimeMax_MOD = LeadTimeMax_MOD;
    }

    public Integer getVersao_MOD() {
        return Versao_MOD;
    }

    public void setVersao_MOD(Integer Versao_MOD) {
        this.Versao_MOD = Versao_MOD;
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
        final MetodologiaAnalise other = (MetodologiaAnalise) obj;
        return Objects.equals(this.id, other.id);
    }
    
}
