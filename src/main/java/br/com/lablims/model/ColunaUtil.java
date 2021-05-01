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
import br.com.lablims.interfaces.EntidadeBase;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 * O <code>Usuario</code> classe Usuario
 *
 * @author rafae.lopes
 * @version 1.00
 */
@Entity
@Table(name = "tb_coluna_util")
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_coluna_util_auditoria")
@EntityListeners(AuditListener.class)
public class ColunaUtil implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "coluna_id", referencedColumnName = "id")
    private Coluna coluna;

    @Column(name = "codigo_coluna")
    private Integer codigoColuna;

    @ManyToOne()
    @JoinColumn(name = "setor_id", referencedColumnName = "id")
    private Setor setor;

    @ManyToOne()
    @JoinColumn(name = "metodologia_id", referencedColumnName = "id")
    private Metodologia metodologia;

    @ManyToOne()
    @JoinColumn(name = "analise_id", referencedColumnName = "id")
    private Analise analise;

    @ManyToOne()
    @JoinColumn(name = "vaga_id", referencedColumnName = "id")
    private ColunaVaga vaga;

    @ManyToOne()
    @JoinColumn(name = "certificado_id", referencedColumnName = "id")
    private Arquivos certificado;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "data_descarte")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDescarte;

    @Column(name = "data_verificacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataVerificacao;

    @Column(name = "data_ativacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtivacao;

    @Column(name = "obs")
    private String obs;

    @Column(name = "version")
    private Integer version;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "tb_arquivos_coluna_util",
            joinColumns = @JoinColumn(name = "arquivio_id"),
            inverseJoinColumns = @JoinColumn(name = "anexo_id"))
    private Set<Arquivos> anexos = new HashSet<>();

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean Coluna_MOD;

    @Transient
    private Boolean CodigoColuna_MOD;

    @Transient
    private Boolean Setor_MOD;

    @Transient
    private Boolean Metodologia_MOD;

    @Transient
    private Boolean Analise_MOD;

    @Transient
    private Boolean Vaga_MOD;

    @Transient
    private Boolean DataDescarte_MOD;

    @Transient
    private Boolean DataVerificacao_MOD;

    @Transient
    private Boolean DataAtivacao_MOD;

    @Transient
    private Boolean SerialNumber_MOD;

    @Transient
    private Boolean Obs_MOD;

    @Transient
    private Boolean Certificado_MOD;

    public ColunaUtil() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coluna getColuna() {
        return coluna;
    }

    public void setColuna(Coluna coluna) {
        this.coluna = coluna;
    }

    public Integer getCodigoColuna() {
        return codigoColuna;
    }

    public void setCodigoColuna(Integer codigoColuna) {
        this.codigoColuna = codigoColuna;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
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

    public ColunaVaga getVaga() {
        return vaga;
    }

    public void setVaga(ColunaVaga vaga) {
        this.vaga = vaga;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getDataDescarte() {
        return dataDescarte;
    }

    public void setDataDescarte(Date dataDescarte) {
        this.dataDescarte = dataDescarte;
    }

    public Date getDataVerificacao() {
        return dataVerificacao;
    }

    public void setDataVerificacao(Date dataVerificacao) {
        this.dataVerificacao = dataVerificacao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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

    public Boolean getColuna_MOD() {
        return Coluna_MOD;
    }

    public void setColuna_MOD(Boolean Coluna_MOD) {
        this.Coluna_MOD = Coluna_MOD;
    }

    public Boolean getSetor_MOD() {
        return Setor_MOD;
    }

    public void setSetor_MOD(Boolean Setor_MOD) {
        this.Setor_MOD = Setor_MOD;
    }

    public Boolean getMetodologia_MOD() {
        return Metodologia_MOD;
    }

    public void setMetodologia_MOD(Boolean Metodologia_MOD) {
        this.Metodologia_MOD = Metodologia_MOD;
    }

    public Boolean getAnalise_MOD() {
        return Analise_MOD;
    }

    public void setAnalise_MOD(Boolean Analise_MOD) {
        this.Analise_MOD = Analise_MOD;
    }

    public Boolean getVaga_MOD() {
        return Vaga_MOD;
    }

    public void setVaga_MOD(Boolean Vaga_MOD) {
        this.Vaga_MOD = Vaga_MOD;
    }

    public Boolean getDataDescarte_MOD() {
        return DataDescarte_MOD;
    }

    public void setDataDescarte_MOD(Boolean DataDescarte_MOD) {
        this.DataDescarte_MOD = DataDescarte_MOD;
    }

    public Boolean getDataVerificacao_MOD() {
        return DataVerificacao_MOD;
    }

    public void setDataVerificacao_MOD(Boolean DataVerificacao_MOD) {
        this.DataVerificacao_MOD = DataVerificacao_MOD;
    }

    public Boolean getSerialNumber_MOD() {
        return SerialNumber_MOD;
    }

    public void setSerialNumber_MOD(Boolean SerialNumber_MOD) {
        this.SerialNumber_MOD = SerialNumber_MOD;
    }

    public Boolean getObs_MOD() {
        return Obs_MOD;
    }

    public void setObs_MOD(Boolean Obs_MOD) {
        this.Obs_MOD = Obs_MOD;
    }

    public Date getDataAtivacao() {
        return dataAtivacao;
    }

    public void setDataAtivacao(Date dataAtivacao) {
        this.dataAtivacao = dataAtivacao;
    }

    public Boolean getDataAtivacao_MOD() {
        return DataAtivacao_MOD;
    }

    public void setDataAtivacao_MOD(Boolean DataAtivacao_MOD) {
        this.DataAtivacao_MOD = DataAtivacao_MOD;
    }

    public Boolean getCodigoColuna_MOD() {
        return CodigoColuna_MOD;
    }

    public void setCodigoColuna_MOD(Boolean CodigoColuna_MOD) {
        this.CodigoColuna_MOD = CodigoColuna_MOD;
    }

    public Arquivos getCertificado() {
        return certificado;
    }

    public void setCertificado(Arquivos certificado) {
        this.certificado = certificado;
    }

    public Boolean getCertificado_MOD() {
        return Certificado_MOD;
    }

    public void setCertificado_MOD(Boolean Certificado_MOD) {
        this.Certificado_MOD = Certificado_MOD;
    }

    public Set<Arquivos> getAnexos() {
        return anexos;
    }

    public void setAnexos(Set<Arquivos> anexos) {
        this.anexos = anexos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final ColunaUtil other = (ColunaUtil) obj;
        return Objects.equals(this.id, other.id);
    }

}
