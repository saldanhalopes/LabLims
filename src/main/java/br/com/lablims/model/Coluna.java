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
@Table(name = "tb_coluna")
@NamedQueries({
    @NamedQuery(name = "Coluna.findAll", query = "SELECT col FROM Coluna col"),
    @NamedQuery(name = "Coluna.checkIsExits", query = "SELECT col FROM Coluna col WHERE col.codigo = :codigo")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_coluna_auditoria")
@EntityListeners(AuditListener.class)
public class Coluna implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "codigo")
    private String codigo;
    
    @Column(name = "part_number")
    private String partNumber;
    
    @ManyToOne()
    @JoinColumn(name = "tipo_coluna", referencedColumnName = "id")
    private ColunaConfig tipoColuna;
    
    @ManyToOne()
    @JoinColumn(name = "fabricante_coluna", referencedColumnName = "id")
    private ColunaConfig fabricanteColuna;
    
    @ManyToOne()
    @JoinColumn(name = "marca_coluna", referencedColumnName = "id")
    private ColunaConfig marcaColuna;
    
    @ManyToOne()
    @JoinColumn(name = "fase_coluna", referencedColumnName = "id")
    private ColunaConfig faseColuna;
    
    @ManyToOne()
    @JoinColumn(name = "tamanho_coluna", referencedColumnName = "id")
    private ColunaConfig tamanhoColuna;
    
    @ManyToOne()
    @JoinColumn(name = "diametro_coluna", referencedColumnName = "id")
    private ColunaConfig diametroColuna;
    
    @ManyToOne()
    @JoinColumn(name = "particula_coluna", referencedColumnName = "id")
    private ColunaConfig particulaColuna;
    
    @Column(name = "obs")
    private String obs;
    
    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean Codigo_MOD;

    @Transient
    private Boolean TipoColuna_MOD;
    
    @Transient
    private Boolean FabricanteColuna_MOD;
    
    @Transient
    private Boolean MarcaColuna_MOD;
    
    @Transient
    private Boolean FaseColuna_MOD;
    
    @Transient
    private Boolean TamanhoColuna_MOD;
    
    @Transient
    private Boolean DiametroColuna_MOD;
    
    @Transient
    private Boolean ParticulaColuna_MOD;
    
    @Transient
    private Boolean PartNumber_MOD;
    
    @Transient
    private Boolean Obs_MOD;
        
    public Coluna() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ColunaConfig getTipoColuna() {
        return tipoColuna;
    }

    public void setTipoColuna(ColunaConfig tipoColuna) {
        this.tipoColuna = tipoColuna;
    }

    public ColunaConfig getFabricanteColuna() {
        return fabricanteColuna;
    }

    public void setFabricanteColuna(ColunaConfig fabricanteColuna) {
        this.fabricanteColuna = fabricanteColuna;
    }

    public ColunaConfig getMarcaColuna() {
        return marcaColuna;
    }

    public void setMarcaColuna(ColunaConfig marcaColuna) {
        this.marcaColuna = marcaColuna;
    }

    public ColunaConfig getFaseColuna() {
        return faseColuna;
    }

    public void setFaseColuna(ColunaConfig faseColuna) {
        this.faseColuna = faseColuna;
    }

    public ColunaConfig getTamanhoColuna() {
        return tamanhoColuna;
    }

    public void setTamanhoColuna(ColunaConfig tamanhoColuna) {
        this.tamanhoColuna = tamanhoColuna;
    }

    public ColunaConfig getDiametroColuna() {
        return diametroColuna;
    }

    public void setDiametroColuna(ColunaConfig diametroColuna) {
        this.diametroColuna = diametroColuna;
    }

    public ColunaConfig getParticulaColuna() {
        return particulaColuna;
    }

    public void setParticulaColuna(ColunaConfig particulaColuna) {
        this.particulaColuna = particulaColuna;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Boolean getCodigo_MOD() {
        return Codigo_MOD;
    }

    public void setCodigo_MOD(Boolean Codigo_MOD) {
        this.Codigo_MOD = Codigo_MOD;
    }

    public Boolean getTipoColuna_MOD() {
        return TipoColuna_MOD;
    }

    public void setTipoColuna_MOD(Boolean TipoColuna_MOD) {
        this.TipoColuna_MOD = TipoColuna_MOD;
    }

    public Boolean getFabricanteColuna_MOD() {
        return FabricanteColuna_MOD;
    }

    public void setFabricanteColuna_MOD(Boolean FabricanteColuna_MOD) {
        this.FabricanteColuna_MOD = FabricanteColuna_MOD;
    }

    public Boolean getMarcaColuna_MOD() {
        return MarcaColuna_MOD;
    }

    public void setMarcaColuna_MOD(Boolean MarcaColuna_MOD) {
        this.MarcaColuna_MOD = MarcaColuna_MOD;
    }

    public Boolean getFaseColuna_MOD() {
        return FaseColuna_MOD;
    }

    public void setFaseColuna_MOD(Boolean FaseColuna_MOD) {
        this.FaseColuna_MOD = FaseColuna_MOD;
    }

    public Boolean getTamanhoColuna_MOD() {
        return TamanhoColuna_MOD;
    }

    public void setTamanhoColuna_MOD(Boolean TamanhoColuna_MOD) {
        this.TamanhoColuna_MOD = TamanhoColuna_MOD;
    }

    public Boolean getDiametroColuna_MOD() {
        return DiametroColuna_MOD;
    }

    public void setDiametroColuna_MOD(Boolean DiametroColuna_MOD) {
        this.DiametroColuna_MOD = DiametroColuna_MOD;
    }

    public Boolean getParticulaColuna_MOD() {
        return ParticulaColuna_MOD;
    }

    public void setParticulaColuna_MOD(Boolean ParticulaColuna_MOD) {
        this.ParticulaColuna_MOD = ParticulaColuna_MOD;
    }

    public Boolean getPartNumber_MOD() {
        return PartNumber_MOD;
    }

    public void setPartNumber_MOD(Boolean PartNumber_MOD) {
        this.PartNumber_MOD = PartNumber_MOD;
    }

    public Boolean getObs_MOD() {
        return Obs_MOD;
    }

    public void setObs_MOD(Boolean Obs_MOD) {
        this.Obs_MOD = Obs_MOD;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
        final Coluna other = (Coluna) obj;
        return Objects.equals(this.id, other.id);
    }

}
