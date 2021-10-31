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
@Table(name = "tb_lote_materia_prima_status")
@NamedQueries({
    @NamedQuery(name = "LoteMateriaPrimaStatus.findStatus", query = "Select sts FROM LoteMateriaPrimaStatus sts "
            + "WHERE sts.lote.id = :lote_id ORDER BY sts.planoAnalise.descricao ASC"),
    @NamedQuery(name = "LoteMateriaPrimaStatus.findStatusByLoteAndPlanoAnalise", query = "Select sts "
            + "FROM LoteMateriaPrimaStatus sts WHERE sts.lote.id = :lote_id AND sts.planoAnalise.id = :pa_id"),
    @NamedQuery(name = "LoteMateriaPrimaStatus.findStatusByPlanoAnalise", query = "Select sts "
            + "FROM LoteMateriaPrimaStatus sts WHERE sts.planoAnalise.id = :pa_id"),
    @NamedQuery(name = "LoteMateriaPrimaStatus.findStatusByLoteAndAnalise", query = "Select sts "
            + "FROM LoteMateriaPrimaStatus sts WHERE sts.lote.id = :lote_id AND sts.planoAnalise.analise.id = :analise_id")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_lote_materia_prima_status_auditoria")
@EntityListeners(AuditListener.class)
public class LoteMateriaPrimaStatus implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "lote_id", referencedColumnName = "id")
    private LoteMateriaPrima lote;

    @ManyToOne()
    @JoinColumn(name = "plano_analise_id", referencedColumnName = "id")
    private PlanoAnalise planoAnalise;

    @ManyToOne()
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private AnaliseStatus analiseStatus;

    @Column(name = "status_data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date statusDataHora;

    @Column(name = "conferido")
    private Boolean conferido;

    @Column(name = "conferido_data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date conferidoDataHora;

    @Column(name = "obs")
    private String obs;

    @Column(name = "previsao_data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date previsaoDataHora;

    @Column(name = "programado")
    private Boolean programado;
    
    @Column(name = "celula")
    private String celula;
    
    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Integer Acesso_MOD;
    
    public LoteMateriaPrimaStatus() {
    }

    public LoteMateriaPrimaStatus(Long id) {
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

    public PlanoAnalise getPlanoAnalise() {
        return planoAnalise;
    }

    public void setPlanoAnalise(PlanoAnalise planoAnalise) {
        this.planoAnalise = planoAnalise;
    }

    public AnaliseStatus getAnaliseStatus() {
        return analiseStatus;
    }

    public void setAnaliseStatus(AnaliseStatus analiseStatus) {
        this.analiseStatus = analiseStatus;
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

    public Date getStatusDataHora() {
        return statusDataHora;
    }

    public void setStatusDataHora(Date statusDataHora) {
        this.statusDataHora = statusDataHora;
    }

    public Boolean getConferido() {
        return conferido;
    }

    public void setConferido(Boolean conferido) {
        this.conferido = conferido;
    }

    public Date getConferidoDataHora() {
        return conferidoDataHora;
    }

    public void setConferidoDataHora(Date conferidoDataHora) {
        this.conferidoDataHora = conferidoDataHora;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getCelula() {
        return celula;
    }

    public void setCelula(String celula) {
        this.celula = celula;
    }

    public Date getPrevisaoDataHora() {
        return previsaoDataHora;
    }

    public void setPrevisaoDataHora(Date previsaoDataHora) {
        this.previsaoDataHora = previsaoDataHora;
    }

    public Boolean getProgramado() {
        return programado;
    }

    public void setProgramado(Boolean programado) {
        this.programado = programado;
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
        final LoteMateriaPrimaStatus other = (LoteMateriaPrimaStatus) obj;
        return Objects.equals(this.id, other.id);
    }

}
