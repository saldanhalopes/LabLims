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
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import br.com.lablims.interfaces.EntidadeBase;
import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "tb_metodologia_versao")
@NamedQueries({
    @NamedQuery(name = "MetodologiaVersao.findByMetodo", 
            query = "Select mtd FROM MetodologiaVersao mtd "
            + "WHERE mtd.metodologia.codMetodo = :cod_metodo "
            + "ORDER BY mtd.versao DESC")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_metodologia_versao_auditoria")
@EntityListeners(AuditListener.class)
public class MetodologiaVersao implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodologia_id", referencedColumnName = "id")
    private Metodologia metodologia;

    @Column(name = "versao")
    private Integer versao;
    
    @Column(name = "status")
    private String status;

    @Column(name = "status_data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDataHora;
    
    @Column(name = "data_proxima_revisao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataProximaRevisao;

    @Column(name = "data_revisao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRevisao;

    @Column(name = "obs")
    private String obs;

    @ManyToOne()
    @JoinColumn(name = "anexo_id", referencedColumnName = "id")
    private Arquivos anexo;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Integer Acesso_MOD;
    
    public MetodologiaVersao() {
    }

    public MetodologiaVersao(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Metodologia getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(Metodologia metodologia) {
        this.metodologia = metodologia;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusDataHora() {
        return statusDataHora;
    }

    public void setStatusDataHora(Date statusDataHora) {
        this.statusDataHora = statusDataHora;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Arquivos getAnexo() {
        return anexo;
    }

    public void setAnexo(Arquivos anexo) {
        this.anexo = anexo;
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

    public Integer getAcesso_MOD() {
        return Acesso_MOD;
    }

    public void setAcesso_MOD(Integer Acesso_MOD) {
        this.Acesso_MOD = Acesso_MOD;
    }

    public Date getDataProximaRevisao() {
        return dataProximaRevisao;
    }

    public void setDataProximaRevisao(Date dataProximaRevisao) {
        this.dataProximaRevisao = dataProximaRevisao;
    }

    public Date getDataRevisao() {
        return dataRevisao;
    }

    public void setDataRevisao(Date dataRevisao) {
        this.dataRevisao = dataRevisao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final MetodologiaVersao other = (MetodologiaVersao) obj;
        return Objects.equals(this.id, other.id);
    }

}
