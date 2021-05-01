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
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tb_lote_materia_prima_info")
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_lote_materia_prima_info_auditoria")
@EntityListeners(AuditListener.class)
public class LoteMateriaPrimaInfo implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotAudited
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private LoteMateriaPrima lote;

    @Column(name = "data_necessidade")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataNecessidade;

    @Column(name = "data_vencimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVenciamento;

    @Column(name = "qtd_estoque", precision = 16, scale = 6)
    private BigDecimal qtdEstoque;

    @ManyToOne()
    @JoinColumn(name = "unidade_id", referencedColumnName = "id")
    private UnidadeMedida unidade;

    @Column(name = "qtd_volumes")
    private Integer qtdVolumes;

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

    @Column(name = "data_amostragem")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAmostragem;

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

    @Column(name = "fabricante_fornecedor")
    private String fabricanteFornecedor;

    @Column(name = "lote_original")
    private String loteOriginal;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean DataNecessidade_MOD;

    @Transient
    private Boolean QtdEstoque_MOD;

    @Transient
    private Boolean QtdNecessaria_MOD;

    @Transient
    private Boolean PrevLiberacao_MOD;

    @Transient
    private Boolean DataStatus_MOD;

    @Transient
    private Boolean Status_MOD;

    @Transient
    private Boolean DataVenciamento_MOD;

    @Transient
    private Boolean QtdVolumes_MOD;

    @Transient
    private Boolean Unidade_MOD;

    @Transient
    private Boolean ObsCq_MOD;

    @Transient
    private Boolean DataEntrada_MOD;

    @Transient
    private Boolean DataAmostragem_MOD;

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

    @Transient
    private Boolean FabricanteFornecedor_MOD;

    @Transient
    private Boolean LoteOriginal_MOD;

    public LoteMateriaPrimaInfo() {
    }

    public LoteMateriaPrimaInfo(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoteMateriaPrima getLote() {
        return lote;
    }

    public void setLote(LoteMateriaPrima lote) {
        this.lote = lote;
    }

    public Date getDataNecessidade() {
        return dataNecessidade;
    }

    public void setDataNecessidade(Date dataNecessidade) {
        this.dataNecessidade = dataNecessidade;
    }

    public BigDecimal getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(BigDecimal qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
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

    public Boolean getDataNecessidade_MOD() {
        return DataNecessidade_MOD;
    }

    public void setDataNecessidade_MOD(Boolean DataNecessidade_MOD) {
        this.DataNecessidade_MOD = DataNecessidade_MOD;
    }

    public Boolean getQtdEstoque_MOD() {
        return QtdEstoque_MOD;
    }

    public void setQtdEstoque_MOD(Boolean QtdEstoque_MOD) {
        this.QtdEstoque_MOD = QtdEstoque_MOD;
    }

    public Boolean getQtdNecessaria_MOD() {
        return QtdNecessaria_MOD;
    }

    public void setQtdNecessaria_MOD(Boolean QtdNecessaria_MOD) {
        this.QtdNecessaria_MOD = QtdNecessaria_MOD;
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

    public UnidadeMedida getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeMedida unidade) {
        this.unidade = unidade;
    }

    public Boolean getObsCq_MOD() {
        return ObsCq_MOD;
    }

    public void setObsCq_MOD(Boolean ObsCq_MOD) {
        this.ObsCq_MOD = ObsCq_MOD;
    }

    public Date getDataVenciamento() {
        return dataVenciamento;
    }

    public void setDataVenciamento(Date dataVenciamento) {
        this.dataVenciamento = dataVenciamento;
    }

    public Integer getQtdVolumes() {
        return qtdVolumes;
    }

    public void setQtdVolumes(Integer qtdVolumes) {
        this.qtdVolumes = qtdVolumes;
    }

    public Boolean getDataVenciamento_MOD() {
        return DataVenciamento_MOD;
    }

    public void setDataVenciamento_MOD(Boolean DataVenciamento_MOD) {
        this.DataVenciamento_MOD = DataVenciamento_MOD;
    }

    public Boolean getQtdVolumes_MOD() {
        return QtdVolumes_MOD;
    }

    public void setQtdVolumes_MOD(Boolean QtdVolumes_MOD) {
        this.QtdVolumes_MOD = QtdVolumes_MOD;
    }

    public Boolean getUnidade_MOD() {
        return Unidade_MOD;
    }

    public void setUnidade_MOD(Boolean Unidade_MOD) {
        this.Unidade_MOD = Unidade_MOD;
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

    public Date getDataAmostragem() {
        return dataAmostragem;
    }

    public void setDataAmostragem(Date dataAmostragem) {
        this.dataAmostragem = dataAmostragem;
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

    public Boolean getDataAmostragem_MOD() {
        return DataAmostragem_MOD;
    }

    public void setDataAmostragem_MOD(Boolean DataAmostragem_MOD) {
        this.DataAmostragem_MOD = DataAmostragem_MOD;
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

    public String getFabricanteFornecedor() {
        return fabricanteFornecedor;
    }

    public void setFabricanteFornecedor(String fabricanteFornecedor) {
        this.fabricanteFornecedor = fabricanteFornecedor;
    }

    public String getLoteOriginal() {
        return loteOriginal;
    }

    public void setLoteOriginal(String loteOriginal) {
        this.loteOriginal = loteOriginal;
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

    public Boolean getFabricanteFornecedor_MOD() {
        return FabricanteFornecedor_MOD;
    }

    public void setFabricanteFornecedor_MOD(Boolean FabricanteFornecedor_MOD) {
        this.FabricanteFornecedor_MOD = FabricanteFornecedor_MOD;
    }

    public Boolean getLoteOriginal_MOD() {
        return LoteOriginal_MOD;
    }

    public void setLoteOriginal_MOD(Boolean LoteOriginal_MOD) {
        this.LoteOriginal_MOD = LoteOriginal_MOD;
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
        final LoteMateriaPrimaInfo other = (LoteMateriaPrimaInfo) obj;
        return Objects.equals(this.id, other.id);
    }

}
