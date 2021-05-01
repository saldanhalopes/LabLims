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
@Table(name = "tb_registro_solucao_reagente")
@NamedQueries({
    @NamedQuery(name = "RegistroSolucaoReagente.findUltimoRegistroByTipo", query = "SELECT sr FROM RegistroSolucaoReagente sr WHERE sr.tipo = :tipo ORDER BY sr.id DESC")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_registro_solucao_reagente_auditoria")
@EntityListeners(AuditListener.class)
public class RegistroSolucaoReagente implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "numero")
    private Integer numero;

    @Column(name = "lote")
    private String lote;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "referencia")
    private String referencia;

    @Column(name = "armazenamento")
    private String armazenamento;

    @Column(name = "data_preparo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPreparo;

    @Column(name = "data_validade")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataValidade;

    @Column(name = "data_conferencia")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataConferencia;

    @Column(name = "obs")
    private String obs;

    @ManyToOne()
    @JoinColumn(name = "criador_id", referencedColumnName = "id")
    private Usuario criador;

    @ManyToOne()
    @JoinColumn(name = "conferente_id", referencedColumnName = "id")
    private Usuario conferente;
    
    @Column(name = "ativo")
    private Boolean ativo;
    
    
    
    
    
    

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean MOD;

    @Transient
    private Boolean Descricao_MOD;

    @Transient
    private Boolean Lote_MOD;

    @Transient
    private Boolean Tipo_MOD;

    @Transient
    private Boolean Referencia_MOD;

    @Transient
    private Boolean Armazenamento_MOD;

    @Transient
    private Boolean DataPreparo_MOD;

    @Transient
    private Boolean DataValidade_MOD;

    @Transient
    private Boolean DataConferencia_MOD;

    @Transient
    private Boolean Criador_MOD;

    @Transient
    private Boolean Conferente_MOD;

    @Transient
    private Boolean Ativo_MOD;
    
    @Transient
    private Boolean Obs_MOD;

    public RegistroSolucaoReagente() {
    }

    public RegistroSolucaoReagente(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Boolean getLote_MOD() {
        return Lote_MOD;
    }

    public void setLote_MOD(Boolean Lote_MOD) {
        this.Lote_MOD = Lote_MOD;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(String armazenamento) {
        this.armazenamento = armazenamento;
    }

    public Date getDataPreparo() {
        return dataPreparo;
    }

    public void setDataPreparo(Date dataPreparo) {
        this.dataPreparo = dataPreparo;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public Usuario getConferente() {
        return conferente;
    }

    public void setConferente(Usuario conferente) {
        this.conferente = conferente;
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

    public Boolean getDescricao_MOD() {
        return Descricao_MOD;
    }

    public void setDescricao_MOD(Boolean Descricao_MOD) {
        this.Descricao_MOD = Descricao_MOD;
    }

    public Boolean getTipo_MOD() {
        return Tipo_MOD;
    }

    public void setTipo_MOD(Boolean Tipo_MOD) {
        this.Tipo_MOD = Tipo_MOD;
    }

    public Boolean getReferencia_MOD() {
        return Referencia_MOD;
    }

    public void setReferencia_MOD(Boolean Referencia_MOD) {
        this.Referencia_MOD = Referencia_MOD;
    }

    public Boolean getArmazenamento_MOD() {
        return Armazenamento_MOD;
    }

    public void setArmazenamento_MOD(Boolean Armazenamento_MOD) {
        this.Armazenamento_MOD = Armazenamento_MOD;
    }

    public Boolean getDataPreparo_MOD() {
        return DataPreparo_MOD;
    }

    public void setDataPreparo_MOD(Boolean DataPreparo_MOD) {
        this.DataPreparo_MOD = DataPreparo_MOD;
    }

    public Boolean getDataValidade_MOD() {
        return DataValidade_MOD;
    }

    public void setDataValidade_MOD(Boolean DataValidade_MOD) {
        this.DataValidade_MOD = DataValidade_MOD;
    }

    public Boolean getCriador_MOD() {
        return Criador_MOD;
    }

    public void setCriador_MOD(Boolean Criador_MOD) {
        this.Criador_MOD = Criador_MOD;
    }

    public Boolean getConferente_MOD() {
        return Conferente_MOD;
    }

    public void setConferente_MOD(Boolean Conferente_MOD) {
        this.Conferente_MOD = Conferente_MOD;
    }

    public Boolean getObs_MOD() {
        return Obs_MOD;
    }

    public void setObs_MOD(Boolean Obs_MOD) {
        this.Obs_MOD = Obs_MOD;
    }

    public Date getDataConferencia() {
        return dataConferencia;
    }

    public void setDataConferencia(Date dataConferencia) {
        this.dataConferencia = dataConferencia;
    }

    public Boolean getDataConferencia_MOD() {
        return DataConferencia_MOD;
    }

    public void setDataConferencia_MOD(Boolean DataConferencia_MOD) {
        this.DataConferencia_MOD = DataConferencia_MOD;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getAtivo_MOD() {
        return Ativo_MOD;
    }

    public void setAtivo_MOD(Boolean Ativo_MOD) {
        this.Ativo_MOD = Ativo_MOD;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final RegistroSolucaoReagente other = (RegistroSolucaoReagente) obj;
        return Objects.equals(this.id, other.id);
    }

}
