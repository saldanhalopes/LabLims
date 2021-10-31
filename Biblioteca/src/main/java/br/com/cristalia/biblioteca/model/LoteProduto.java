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
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

/**
 * <code>LoteProduto</code> classe LoteProduto
 *
 * @author rafae.lopes
 * @version 1.00
 */
@Entity
@Table(name = "tb_lote_produto")
@NamedQueries({
    @NamedQuery(name = "LoteProduto.findLote", query = "Select lot FROM LoteProduto lot WHERE lot.lote = :lote")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_lote_produto_auditoria")
@EntityListeners(AuditListener.class)
public class LoteProduto implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lote", unique = true, length = 50)
    private String lote;

    @ManyToOne()
    @JoinColumn(name = "material_id", referencedColumnName = "id")
    private Material material;
    
    @ManyToOne()
    @JoinColumn(name = "metodologia_id", referencedColumnName = "id")
    private Metodologia metodologia;
    
    @ManyToOne()
    @JoinColumn(name = "metodologia2_id", referencedColumnName = "id")
    private Metodologia metodologia2;
    
    @ManyToOne()
    @JoinColumn(name = "metodologia3_id", referencedColumnName = "id")
    private Metodologia metodologia3;

    @NotAudited
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "lote")
    private LoteProdutoInfo loteInfo;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean Lote_MOD;

    @Transient
    private Boolean Material_MOD;

    @Transient
    private Boolean Metodologia_MOD;

    @Transient
    private Boolean Metodologia2_MOD;
    
    @Transient
    private Boolean Metodologia3_MOD;

    public LoteProduto() {
    }

    public LoteProduto(Long id) {
        this.id = id;
    }

    public LoteProduto(String lote, LoteProdutoInfo loteInfo) {
        this.lote = lote;
        this.loteInfo = loteInfo;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public LoteProdutoInfo getLoteInfo() {
        return loteInfo;
    }

    public void setLoteInfo(LoteProdutoInfo loteInfo) {
        this.loteInfo = loteInfo;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Boolean getLote_MOD() {
        return Lote_MOD;
    }

    public void setLote_MOD(Boolean Lote_MOD) {
        this.Lote_MOD = Lote_MOD;
    }

    public Boolean getMaterial_MOD() {
        return Material_MOD;
    }

    public void setMaterial_MOD(Boolean Material_MOD) {
        this.Material_MOD = Material_MOD;
    }

    public Boolean getMetodologia_MOD() {
        return Metodologia_MOD;
    }

    public void setMetodologia_MOD(Boolean Metodologia_MOD) {
        this.Metodologia_MOD = Metodologia_MOD;
    }

    public Boolean getMetodologia2_MOD() {
        return Metodologia2_MOD;
    }

    public void setMetodologia2_MOD(Boolean Metodologia2_MOD) {
        this.Metodologia2_MOD = Metodologia2_MOD;
    }

    public Boolean getMetodologia3_MOD() {
        return Metodologia3_MOD;
    }

    public void setMetodologia3_MOD(Boolean Metodologia3_MOD) {
        this.Metodologia3_MOD = Metodologia3_MOD;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Metodologia getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(Metodologia metodologia) {
        this.metodologia = metodologia;
    }

    public Metodologia getMetodologia2() {
        return metodologia2;
    }

    public void setMetodologia2(Metodologia metodologia2) {
        this.metodologia2 = metodologia2;
    }

    public Metodologia getMetodologia3() {
        return metodologia3;
    }

    public void setMetodologia3(Metodologia metodologia3) {
        this.metodologia3 = metodologia3;
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
        final LoteProduto other = (LoteProduto) obj;
        return Objects.equals(this.id, other.id);
    }

}
