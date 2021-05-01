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
import javax.persistence.Id;
import javax.persistence.Table;
import br.com.lablims.interfaces.EntidadeBase;
import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 *
 * @author rafael.lopes
 */
@Entity
@Table(name = "tb_lote_produto_info")
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_lote_produto_info_auditoria")
@EntityListeners(AuditListener.class)
public class LoteProdutoInfo implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotAudited
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private LoteProduto lote;

    @Column(name = "prev_liberacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date prevLiberacao;

    @Column(name = "data_status")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataStatus;

    @Column(name = "status")
    private String status;

    @Column(name = "obs_cq")
    private String obsCq;

    @Column(name = "created", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created;

    @Column(name = "data_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEntrada;

    @Column(name = "data_analise")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAnalise;

    @Column(name = "data_liberado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataLiberado;

    @Column(name = "data_doc")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDoc;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "numero_roteiro")
    private Integer numeroRoteiro;

    @Column(name = "data_impressao_roteiro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataImpressaoRoteiro;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean PrevLiberacao_MOD;

    @Transient
    private Boolean DataStatus_MOD;

    @Transient
    private Boolean Status_MOD;

    @Transient
    private Boolean ObsCq_MOD;

    @Transient
    private Boolean DataEntrada_MOD;

    @Transient
    private Boolean DataCq_MOD;

    @Transient
    private Boolean DataLiberado_MOD;

    @Transient
    private Boolean DataDoc_MOD;

    @Transient
    private Boolean Complemento_MOD;

    @Transient
    private Boolean NumeroRoteiro_MOD;

    @Transient
    private Boolean DataImpressaoRoteiro_MOD;

    public LoteProdutoInfo() {
    }

    public LoteProdutoInfo(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoteProduto getLote() {
        return lote;
    }

    public void setLote(LoteProduto lote) {
        this.lote = lote;
    }

    public Date getPrevLiberacao() {
        return prevLiberacao;
    }

    public void setPrevLiberacao(Date prevLiberacao) {
        this.prevLiberacao = prevLiberacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObsCq() {
        return obsCq;
    }

    public void setObsCq(String obsCq) {
        this.obsCq = obsCq;
    }

    public Date getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Date dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Boolean getPrevLiberacao_MOD() {
        return PrevLiberacao_MOD;
    }

    public void setPrevLiberacao_MOD(Boolean PrevLiberacao_MOD) {
        this.PrevLiberacao_MOD = PrevLiberacao_MOD;
    }

    public Boolean getDataStatus_MOD() {
        return DataStatus_MOD;
    }

    public void setDataStatus_MOD(Boolean DataStatus_MOD) {
        this.DataStatus_MOD = DataStatus_MOD;
    }

    public Boolean getStatus_MOD() {
        return Status_MOD;
    }

    public void setStatus_MOD(Boolean Status_MOD) {
        this.Status_MOD = Status_MOD;
    }

    public Boolean getObsCq_MOD() {
        return ObsCq_MOD;
    }

    public void setObsCq_MOD(Boolean ObsCq_MOD) {
        this.ObsCq_MOD = ObsCq_MOD;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataAnalise() {
        return dataAnalise;
    }

    public void setDataAnalise(Date dataAnalise) {
        this.dataAnalise = dataAnalise;
    }

    public Date getDataLiberado() {
        return dataLiberado;
    }

    public void setDataLiberado(Date dataLiberado) {
        this.dataLiberado = dataLiberado;
    }

    public Boolean getDataEntrada_MOD() {
        return DataEntrada_MOD;
    }

    public void setDataEntrada_MOD(Boolean DataEntrada_MOD) {
        this.DataEntrada_MOD = DataEntrada_MOD;
    }

    public Boolean getDataCq_MOD() {
        return DataCq_MOD;
    }

    public void setDataCq_MOD(Boolean DataCq_MOD) {
        this.DataCq_MOD = DataCq_MOD;
    }

    public Boolean getDataLiberado_MOD() {
        return DataLiberado_MOD;
    }

    public void setDataLiberado_MOD(Boolean DataLiberado_MOD) {
        this.DataLiberado_MOD = DataLiberado_MOD;
    }

    public Date getDataDoc() {
        return dataDoc;
    }

    public void setDataDoc(Date dataDoc) {
        this.dataDoc = dataDoc;
    }

    public Boolean getDataDoc_MOD() {
        return DataDoc_MOD;
    }

    public void setDataDoc_MOD(Boolean DataDoc_MOD) {
        this.DataDoc_MOD = DataDoc_MOD;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getNumeroRoteiro() {
        return numeroRoteiro;
    }

    public void setNumeroRoteiro(Integer numeroRoteiro) {
        this.numeroRoteiro = numeroRoteiro;
    }

    public Date getDataImpressaoRoteiro() {
        return dataImpressaoRoteiro;
    }

    public void setDataImpressaoRoteiro(Date dataImpressaoRoteiro) {
        this.dataImpressaoRoteiro = dataImpressaoRoteiro;
    }

    public Boolean getComplemento_MOD() {
        return Complemento_MOD;
    }

    public void setComplemento_MOD(Boolean Complemento_MOD) {
        this.Complemento_MOD = Complemento_MOD;
    }

    public Boolean getNumeroRoteiro_MOD() {
        return NumeroRoteiro_MOD;
    }

    public void setNumeroRoteiro_MOD(Boolean NumeroRoteiro_MOD) {
        this.NumeroRoteiro_MOD = NumeroRoteiro_MOD;
    }

    public Boolean getDataImpressaoRoteiro_MOD() {
        return DataImpressaoRoteiro_MOD;
    }

    public void setDataImpressaoRoteiro_MOD(Boolean DataImpressaoRoteiro_MOD) {
        this.DataImpressaoRoteiro_MOD = DataImpressaoRoteiro_MOD;
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
        final LoteProdutoInfo other = (LoteProdutoInfo) obj;
        return Objects.equals(this.id, other.id);
    }

}
