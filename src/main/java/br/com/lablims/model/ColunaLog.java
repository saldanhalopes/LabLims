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

import br.com.lablims.interfaces.EntidadeBase;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * O <code>ColunaLog</code> classe ColunaLog
 *
 * @author rafae.lopes
 * @version 1.00
 */
@Entity
@Table(name = "tb_coluna_log")
@NamedQueries({
    @NamedQuery(name = "ColunaLog.findAll", query = "SELECT colLog FROM ColunaLog colLog")})
@DynamicInsert(true)
@DynamicUpdate(true)
public class ColunaLog implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne()
    @JoinColumn(name = "coluna_util_id", referencedColumnName = "id")
    private ColunaUtil colunaUtil;

    @ManyToOne()
    @JoinColumn(name = "equipamento_id", referencedColumnName = "id")
    private Equipamento cromatografo;

    @ManyToOne()
    @JoinColumn(name = "usuario_inicio_id", referencedColumnName = "id")
    private Usuario usuarioInicio;
    
    @ManyToOne()
    @JoinColumn(name = "usuario_fim_id", referencedColumnName = "id")
    private Usuario usuarioFim;

    @Column(name = "data_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;

    @Column(name = "data_fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;

    @Column(name = "sentido")
    private Boolean sentido;

    @Column(name = "precoluna")
    private Boolean precoluna;

    @Column(name = "prefiltro")
    private Boolean prefiltro;

    @Column(name = "injecoes")
    private Integer injecoes;
    
    @ManyToOne()
    @JoinColumn(name = "campanha_id", referencedColumnName = "id")
    private Campanha campanha;

    @Column(name = "obs")
    private String obs;

    @ManyToOne()
    @JoinColumn(name = "anexo_id", referencedColumnName = "id")
    private Arquivos anexo;
    
    @Column(name = "version")
    private Integer version;

    
    public ColunaLog() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ColunaUtil getColunaUtil() {
        return colunaUtil;
    }

    public void setColunaUtil(ColunaUtil colunaUtil) {
        this.colunaUtil = colunaUtil;
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

    public Equipamento getCromatografo() {
        return cromatografo;
    }

    public void setCromatografo(Equipamento cromatografo) {
        this.cromatografo = cromatografo;
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

    public Boolean getSentido() {
        return sentido;
    }

    public void setSentido(Boolean sentido) {
        this.sentido = sentido;
    }

    public Boolean getPrecoluna() {
        return precoluna;
    }

    public void setPrecoluna(Boolean precoluna) {
        this.precoluna = precoluna;
    }

    public Boolean getPrefiltro() {
        return prefiltro;
    }

    public void setPrefiltro(Boolean prefiltro) {
        this.prefiltro = prefiltro;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public Usuario getUsuarioInicio() {
        return usuarioInicio;
    }

    public void setUsuarioInicio(Usuario usuarioInicio) {
        this.usuarioInicio = usuarioInicio;
    }

    public Usuario getUsuarioFim() {
        return usuarioFim;
    }

    public void setUsuarioFim(Usuario usuarioFim) {
        this.usuarioFim = usuarioFim;
    }

    public Integer getInjecoes() {
        return injecoes;
    }

    public void setInjecoes(Integer injecoes) {
        this.injecoes = injecoes;
    }

    public Arquivos getAnexo() {
        return anexo;
    }

    public void setAnexo(Arquivos anexo) {
        this.anexo = anexo;
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
        final ColunaLog other = (ColunaLog) obj;
        return Objects.equals(this.id, other.id);
    }

}
