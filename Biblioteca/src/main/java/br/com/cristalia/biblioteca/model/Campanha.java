/*
 * Copyright (C) 2017 rafael.lopes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.cristalia.biblioteca.model;

import br.com.cristalia.biblioteca.audit.Audit;
import br.com.cristalia.biblioteca.audit.AuditListener;
import br.com.cristalia.biblioteca.interfaces.EntidadeBase;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 *
 * @author rafael.lopes
 */
@Entity
@Table(name = "tb_campanha")
@NamedQueries({
    @NamedQuery(name = "Campanha.findAll", query = "SELECT camp FROM Campanha camp")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_campanha_auditoria")
@EntityListeners(AuditListener.class)
public class Campanha implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "campanha")
    private String campanha;

    @ManyToOne()
    @JoinColumn(name = "setor_id", referencedColumnName = "id")
    private Setor setor;

    @ManyToMany(cascade = {CascadeType.PERSIST,
        CascadeType.MERGE})
    @JoinTable(name = "tb_campanha_metodologia",
            joinColumns = @JoinColumn(name = "campanha_id"),
            inverseJoinColumns = @JoinColumn(name = "metodologia_id"))
    private Set<Metodologia> metodologia = new HashSet<>();
    
    @ManyToMany(cascade = {CascadeType.PERSIST,
        CascadeType.MERGE})
    @JoinTable(name = "tb_campanha_analise",
            joinColumns = @JoinColumn(name = "campanha_id"),
            inverseJoinColumns = @JoinColumn(name = "analise_id"))
    private Set<Analise> analise = new HashSet<>();
    
    @ManyToOne()
    @JoinColumn(name = "equipamento_id", referencedColumnName = "id")
    private Equipamento equipamento;

    @ManyToOne()
    @JoinColumn(name = "usuario_inicio_id", referencedColumnName = "id")
    private Usuario usuarioIncio;

    @ManyToOne()
    @JoinColumn(name = "usuario_fim_id", referencedColumnName = "id")
    private Usuario usuarioFim;

    @Column(name = "data_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro;

    @Column(name = "data_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInicio;

    @Column(name = "data_fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFim;

    @Column(name = "obs")
    private String obs;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    private int ordem;

    private String cor;
    private Timestamp previsao;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCampanha() {
        return campanha;
    }

    public void setCampanha(String campanha) {
        this.campanha = campanha;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Set<Metodologia> getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(Set<Metodologia> metodologia) {
        this.metodologia = metodologia;
    }

    public Set<Analise> getAnalise() {
        return analise;
    }

    public void setAnalise(Set<Analise> analise) {
        this.analise = analise;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Usuario getUsuarioIncio() {
        return usuarioIncio;
    }

    public void setUsuarioIncio(Usuario usuarioIncio) {
        this.usuarioIncio = usuarioIncio;
    }

    public Usuario getUsuarioFim() {
        return usuarioFim;
    }

    public void setUsuarioFim(Usuario usuarioFim) {
        this.usuarioFim = usuarioFim;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
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

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Timestamp getPrevisao() {
        return previsao;
    }

    public void setPrevisao(Timestamp previsao) {
        this.previsao = previsao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Campanha other = (Campanha) obj;
        return Objects.equals(this.id, other.id);
    }

}
