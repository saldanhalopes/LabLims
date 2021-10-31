/*
 * Copyright (C) 2018 rafael.lopes
 *
 * Este programa Ã© um software livre: vocÃª pode redistribuÃ­-lo e / ou modificar
 * sob os termos da GNU General Public License, conforme publicado pela
 * a Free Software Foundation, seja a versÃ£o 3 da LicenÃ§a, quanto
 * qualquer versÃ£o posterior.
 *
 * Este programa Ã© distribuÃ­do na esperanÃ§a de que seja Ãºtil,
 * mas SEM QUALQUER GARANTIA; sem a garantia implÃ­cita de
 * COMERCIALIZAÃ‡ÃƒO OU APTIDÃƒO PARA UM PROPÃ“SITO PARTICULAR. Veja o
 * GNU General Public License para obter mais detalhes.
 *
 * VocÃª deve ter recebido uma cÃ³pia da GNU General Public License
 *  juntamente com este programa. Caso contrÃ¡rio, veja <http://www.gnu.org/licenses/>.
 */
package br.com.cristalia.biblioteca.model;

import br.com.cristalia.biblioteca.audit.Audit;
import br.com.cristalia.biblioteca.audit.AuditListener;
import br.com.cristalia.biblioteca.interfaces.EntidadeBase;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.EntityListeners;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * <code>Metodologia</code> classe Metodologia
 *
 * @author rafae.lopes
 * @version 1.00
 */
@Entity
@Table(name = "tb_metodologia")
@NamedQueries({
    @NamedQuery(name = "Metodologia.findCodMetodo", query = "Select m FROM Metodologia m WHERE m.codMetodo = :cod_metodo"),
    @NamedQuery(name = "Metodologia.findMetodoByMaterial", query = "Select mtd FROM Metodologia mtd JOIN FETCH mtd.material mat WHERE mat.id = :id"),
    @NamedQuery(name = "Metodologia.findMetodoWithMaterial", query = "Select mtd FROM Metodologia mtd LEFT JOIN FETCH mtd.material mat WHERE mtd.id = :id"),
    @NamedQuery(name = "Metodologia.findMetodologias", query = "Select mtd FROM Metodologia mtd WHERE mtd.id IN :ids")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_metodologia_auditoria")
@EntityListeners(AuditListener.class)
public class Metodologia implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_metodo", unique = true)
    private String codMetodo;

    @Column(name = "metodo")
    private String metodo;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "versao")
    private String versao;
    
    @Column(name = "obs")
    private String obs;

    @ManyToMany(cascade = {CascadeType.PERSIST,
        CascadeType.MERGE})
    @JoinTable(name = "tb_metodologia_material",
            joinColumns = @JoinColumn(name = "metodologia_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    private Set<Material> material = new HashSet<>();
    
    @NotAudited
    @ManyToMany(mappedBy = "metodologia")
    private Set<Campanha> campanha = new HashSet<>();

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean CodMetodo_MOD;

    @Transient
    private Boolean Metodo_MOD;

    @Transient
    private Boolean Versao_MOD;

    @Transient
    private Boolean Categoria_MOD;

    @Transient
    private Boolean Status_MOD;

    @Transient
    private Boolean Obs_MOD;

    @Transient
    private Boolean DataVencimento_MOD;

    @Transient
    private Boolean DataRevisao_MOD;

    @Transient
    private Boolean Material_MOD;
    
    @Transient
    private Boolean Campanha_MOD;

    public Metodologia() {
    }

    public Metodologia(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodMetodo() {
        return codMetodo;
    }

    public void setCodMetodo(String codMetodo) {
        this.codMetodo = codMetodo;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Set<Material> getMaterial() {
        return material;
    }

    public void setMaterial(Set<Material> material) {
        this.material = material;
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

    public Boolean getCodMetodo_MOD() {
        return CodMetodo_MOD;
    }

    public void setCodMetodo_MOD(Boolean CodMetodo_MOD) {
        this.CodMetodo_MOD = CodMetodo_MOD;
    }

    public Boolean getMetodo_MOD() {
        return Metodo_MOD;
    }

    public void setMetodo_MOD(Boolean Metodo_MOD) {
        this.Metodo_MOD = Metodo_MOD;
    }

    public Boolean getVersao_MOD() {
        return Versao_MOD;
    }

    public void setVersao_MOD(Boolean Versao_MOD) {
        this.Versao_MOD = Versao_MOD;
    }

    public Boolean getCategoria_MOD() {
        return Categoria_MOD;
    }

    public void setCategoria_MOD(Boolean Categoria_MOD) {
        this.Categoria_MOD = Categoria_MOD;
    }

    public Boolean getMaterial_MOD() {
        return Material_MOD;
    }

    public void setMaterial_MOD(Boolean Material_MOD) {
        this.Material_MOD = Material_MOD;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Boolean getStatus_MOD() {
        return Status_MOD;
    }

    public void setStatus_MOD(Boolean Status_MOD) {
        this.Status_MOD = Status_MOD;
    }

    public Boolean getObs_MOD() {
        return Obs_MOD;
    }

    public void setObs_MOD(Boolean Obs_MOD) {
        this.Obs_MOD = Obs_MOD;
    }

    public Boolean getDataVencimento_MOD() {
        return DataVencimento_MOD;
    }

    public void setDataVencimento_MOD(Boolean DataVencimento_MOD) {
        this.DataVencimento_MOD = DataVencimento_MOD;
    }

    public Boolean getDataRevisao_MOD() {
        return DataRevisao_MOD;
    }

    public void setDataRevisao_MOD(Boolean DataRevisao_MOD) {
        this.DataRevisao_MOD = DataRevisao_MOD;
    }

    public Set<Campanha> getCampanha() {
        return campanha;
    }

    public void setCampanha(Set<Campanha> campanha) {
        this.campanha = campanha;
    }

    public Boolean getCampanha_MOD() {
        return Campanha_MOD;
    }

    public void setCampanha_MOD(Boolean Campanha_MOD) {
        this.Campanha_MOD = Campanha_MOD;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
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
        final Metodologia other = (Metodologia) obj;
        return Objects.equals(this.id, other.id);
    }

    public void addMaterial(Material mat) {
        this.material.add(mat);
        mat.getMetodologia().add(this);
    }

    public void removeMaterial(Material mat) {
        this.material.remove(mat);
        mat.getMetodologia().remove(this);
    }

}
