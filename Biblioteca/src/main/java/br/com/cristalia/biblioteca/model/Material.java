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
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
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
@Table(name = "tb_material")
@NamedQueries({
    @NamedQuery(name = "Material.findAll", query = "SELECT m FROM Material m"),
    @NamedQuery(name = "Material.findById", query = "SELECT m FROM Material m WHERE m.id = :id"),
    @NamedQuery(name = "Material.findCodMaterial", query = "Select m FROM Material m WHERE m.codMaterial = :codMaterial"),
    @NamedQuery(name = "Material.findMaterialByControlado", query = "Select m FROM Material m WHERE m.controleEspecial = :controleEspecial"),
    @NamedQuery(name = "Material.findMaterialMetodologiaById", query = "Select m FROM Material m LEFT JOIN FETCH m.metodologia mtd WHERE m.id = :id"),
    @NamedQuery(name = "Material.findMaterialByMetodologia", query = "Select m FROM Material m JOIN FETCH m.metodologia mtd WHERE mtd.id = :id")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_material_auditoria")
@EntityListeners(AuditListener.class)
public class Material implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_material", nullable = false, unique = true)
    private Integer codMaterial;

    @Column(name = "material", nullable = false, length = 250)
    private String material;

    @ManyToOne()
    @JoinColumn(name = "tipo_material", referencedColumnName = "id")
    private TipoMaterial tipoMaterial;
       
    @Column(name = "controle_especial")
    private Boolean controleEspecial;
    
    @Column(name = "fiscalizado")
    private String fiscalizado;

    @NotAudited
    @ManyToMany(mappedBy = "material")
    private Set<Metodologia> metodologia = new HashSet<>();

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean MOD;

    @Transient
    private Boolean CodMaterial_MOD;

    @Transient
    private Boolean Material_MOD;

    @Transient
    private Boolean TipoMaterial_MOD;
    
    @Transient
    private Boolean ControleEspecial_MOD;
    
    @Transient
    private Boolean Fiscalizado_MOD;

    public Material() {
    }

    public Material(Long id) {
        this.id = id;
    }

    public Material(Integer codMaterial, String material, String tipo) {
        this.codMaterial = codMaterial;
        this.material = material;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodMaterial() {
        return codMaterial;
    }

    public void setCodMaterial(Integer codMaterial) {
        this.codMaterial = codMaterial;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public TipoMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(TipoMaterial tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    public Set<Metodologia> getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(Set<Metodologia> metodologia) {
        this.metodologia = metodologia;
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

    public Boolean getCodMaterial_MOD() {
        return CodMaterial_MOD;
    }

    public void setCodMaterial_MOD(Boolean CodMaterial_MOD) {
        this.CodMaterial_MOD = CodMaterial_MOD;
    }

    public Boolean getMaterial_MOD() {
        return Material_MOD;
    }

    public void setMaterial_MOD(Boolean Material_MOD) {
        this.Material_MOD = Material_MOD;
    }

    public Boolean getTipoMaterial_MOD() {
        return TipoMaterial_MOD;
    }

    public void setTipoMaterial_MOD(Boolean TipoMaterial_MOD) {
        this.TipoMaterial_MOD = TipoMaterial_MOD;
    }

    public Boolean getControleEspecial() {
        return controleEspecial;
    }

    public void setControleEspecial(Boolean controleEspecial) {
        this.controleEspecial = controleEspecial;
    }

    public String getFiscalizado() {
        return fiscalizado;
    }

    public void setFiscalizado(String fiscalizado) {
        this.fiscalizado = fiscalizado;
    }

    public Boolean getControleEspecial_MOD() {
        return ControleEspecial_MOD;
    }

    public void setControleEspecial_MOD(Boolean ControleEspecial_MOD) {
        this.ControleEspecial_MOD = ControleEspecial_MOD;
    }

    public Boolean getFiscalizado_MOD() {
        return Fiscalizado_MOD;
    }

    public void setFiscalizado_MOD(Boolean Fiscalizado_MOD) {
        this.Fiscalizado_MOD = Fiscalizado_MOD;
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
        final Material other = (Material) obj;
        return Objects.equals(this.id, other.id);
    }

    public void addMetodologia(Metodologia mtd) {
        this.metodologia.add(mtd);
        mtd.getMaterial().add(this);
    }
 
    public void removeMetodologia(Metodologia mtd) {
        this.metodologia.remove(mtd);
        mtd.getMaterial().remove(this);
    }
    
}
