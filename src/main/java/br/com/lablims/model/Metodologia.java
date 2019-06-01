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
import br.com.lablims.interfaces.EntidadeBase;
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
    @NamedQuery(name = "Metodologia.findCodMetodo", query = "Select m FROM Metodologia m WHERE m.codMetodo = :cod_metodo")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_metodologia_auditoria")
@EntityListeners(AuditListener.class)
public class Metodologia implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cod_metodo", unique = true, length = 25)
    private String codMetodo;

    @Column(name = "metodo")
    private String metodo;

    @Column(name = "versao")
    private Integer versao;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "link", length = 250)
    private String link;

    @NotAudited
    @ManyToMany(cascade = {CascadeType.PERSIST,
        CascadeType.MERGE})
    @JoinTable(name = "tb_metodologia_material",
            joinColumns = @JoinColumn(name = "metodologia_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id"))
    private Set<Material> material = new HashSet<>();
    
    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Integer MOD;

    @Transient
    private Integer CodMetodo_MOD;

    @Transient
    private Integer Metodo_MOD;

    @Transient
    private Integer Versao_MOD;

    @Transient
    private Integer Categoria_MOD;

    @Transient
    private Integer Link_MOD;

    public Metodologia() {
    }

    public Metodologia(Integer id) {
        this.id = id;
    }

    public Metodologia(String codMetodo, String metodo, Integer versao, String categoria, String link) {
        this.codMetodo = codMetodo;
        this.metodo = metodo;
        this.versao = versao;
        this.categoria = categoria;
        this.link = link;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public Integer getMOD() {
        return MOD;
    }

    public void setMOD(Integer MOD) {
        this.MOD = MOD;
    }

    public Integer getCodMetodo_MOD() {
        return CodMetodo_MOD;
    }

    public void setCodMetodo_MOD(Integer CodMetodo_MOD) {
        this.CodMetodo_MOD = CodMetodo_MOD;
    }

    public Integer getMetodo_MOD() {
        return Metodo_MOD;
    }

    public void setMetodo_MOD(Integer Metodo_MOD) {
        this.Metodo_MOD = Metodo_MOD;
    }

    public Integer getVersao_MOD() {
        return Versao_MOD;
    }

    public void setVersao_MOD(Integer Versao_MOD) {
        this.Versao_MOD = Versao_MOD;
    }

    public Integer getCategoria_MOD() {
        return Categoria_MOD;
    }

    public void setCategoria_MOD(Integer Categoria_MOD) {
        this.Categoria_MOD = Categoria_MOD;
    }

    public Integer getLink_MOD() {
        return Link_MOD;
    }

    public void setLink_MOD(Integer Link_MOD) {
        this.Link_MOD = Link_MOD;
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

}
