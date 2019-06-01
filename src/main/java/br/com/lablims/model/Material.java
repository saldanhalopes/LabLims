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

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import br.com.lablims.interfaces.EntidadeBase;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.ManyToMany;
import org.hibernate.envers.NotAudited;

/**
 *
 * @author rafael.lopes
 */
@Entity
@Table(name = "tb_material")
public class Material implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cod_material", nullable = false, unique = true)
    private Integer codMaterial;

    @Column(name = "material", nullable = false, length = 250)
    private String material;

    @Column(name = "tipo", nullable = false, length = 10)
    private String tipo;

    @NotAudited
    @ManyToMany(mappedBy = "material")
    private Set<Metodologia> metodologia = new HashSet<>();

    public Material() {
    }

    public Material(Integer id) {
        this.id = id;
    }

    public Material(Integer codMaterial, String material, String tipo) {
        this.codMaterial = codMaterial;
        this.material = material;
        this.tipo = tipo;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Set<Metodologia> getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(Set<Metodologia> metodologia) {
        this.metodologia = metodologia;
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

    @Override
    public String toString() {
        return "Material{" + "id=" + id + ", codMaterial=" + codMaterial + ", material=" + material + ", tipo=" + tipo + '}';
    }

}
