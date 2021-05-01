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
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

 /**
 * @author rafae.lopes
 * @version 1.00
 */
@Entity
@Table(name = "tb_log_cromatografo")
@NamedQueries({
    @NamedQuery(name = "LogCromatografo.findAll", query = "SELECT log FROM LogCromatografo log")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_log_cromatografo_auditoria")
@EntityListeners(AuditListener.class)
public class LogCromatografo implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name = "analise_status_id", referencedColumnName = "id")
    private AnaliseStatus analiseStatus;
    
    @Column(name = "data_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;
    
    @Column(name = "data_fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;
    
    @Column(name = "data_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro;

    @ManyToOne()
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    
    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "obs")
    private String obs;

    @ManyToOne()
    @JoinColumn(name = "campanha_id", referencedColumnName = "id")
    private Campanha campanha;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean MOD;

    @Transient
    private Boolean AnaliseStatus_MOD;
    
    @Transient
    private Boolean DataInicio_MOD;
    
    @Transient
    private Boolean DataFim_MOD;
    
    @Transient
    private Boolean DataRegistro_MOD;
    
    @Transient
    private Boolean Usuario_MOD;
    @Transient
    private Boolean Descricao_MOD;
    @Transient
    private Boolean Obs_MOD;
    @Transient
    private Boolean Campanha_MOD;
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnaliseStatus getAnaliseStatus() {
        return analiseStatus;
    }

    public void setAnaliseStatus(AnaliseStatus analiseStatus) {
        this.analiseStatus = analiseStatus;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
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

    public Boolean getDataInicio_MOD() {
        return DataInicio_MOD;
    }

    public void setDataInicio_MOD(Boolean DataInicio_MOD) {
        this.DataInicio_MOD = DataInicio_MOD;
    }

    public Boolean getDataFim_MOD() {
        return DataFim_MOD;
    }

    public void setDataFim_MOD(Boolean DataFim_MOD) {
        this.DataFim_MOD = DataFim_MOD;
    }

    public Boolean getDataRegistro_MOD() {
        return DataRegistro_MOD;
    }

    public void setDataRegistro_MOD(Boolean DataRegistro_MOD) {
        this.DataRegistro_MOD = DataRegistro_MOD;
    }

    public Boolean getUsuario_MOD() {
        return Usuario_MOD;
    }

    public void setUsuario_MOD(Boolean Usuario_MOD) {
        this.Usuario_MOD = Usuario_MOD;
    }

    public Boolean getDescricao_MOD() {
        return Descricao_MOD;
    }

    public void setDescricao_MOD(Boolean Descricao_MOD) {
        this.Descricao_MOD = Descricao_MOD;
    }

    public Boolean getObs_MOD() {
        return Obs_MOD;
    }

    public void setObs_MOD(Boolean Obs_MOD) {
        this.Obs_MOD = Obs_MOD;
    }

    public Boolean getCampanha_MOD() {
        return Campanha_MOD;
    }

    public void setCampanha_MOD(Boolean Campanha_MOD) {
        this.Campanha_MOD = Campanha_MOD;
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
        final LogCromatografo other = (LogCromatografo) obj;
        return Objects.equals(this.id, other.id);
    }

}
