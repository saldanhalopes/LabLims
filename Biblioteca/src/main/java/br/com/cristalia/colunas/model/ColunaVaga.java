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
package br.com.cristalia.colunas.model;

import br.com.cristalia.biblioteca.audit.Audit;
import br.com.cristalia.biblioteca.audit.AuditListener;
import br.com.cristalia.biblioteca.interfaces.EntidadeBase;
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
@Table(name = "tb_coluna_vaga")
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_coluna_vaga_auditoria")
@EntityListeners(AuditListener.class)
public class ColunaVaga implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "vaga")
    private Integer vaga;
    
    @ManyToOne()
    @JoinColumn(name = "coluna_storage_id", referencedColumnName = "id")
    private ColunaStorage colunaStorage;
    
    @ManyToOne()
    @JoinColumn(name = "coluna_util_id", referencedColumnName = "id")
    private ColunaUtil colunaUtil;
    
    @Column(name = "obs")
    private String obs;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean Vaga_MOD;

    @Transient
    private Boolean ColunaStorage_MOD;
    
    @Transient
    private Boolean ColunaUtil_MOD;
    
    @Transient
    private Boolean Obs_MOD;
    
    public ColunaVaga() {
    }

    public ColunaVaga(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVaga() {
        return vaga;
    }

    public void setVaga(Integer vaga) {
        this.vaga = vaga;
    }

    public ColunaStorage getColunaStorage() {
        return colunaStorage;
    }

    public void setColunaStorage(ColunaStorage colunaStorage) {
        this.colunaStorage = colunaStorage;
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

    public Boolean getVaga_MOD() {
        return Vaga_MOD;
    }

    public void setVaga_MOD(Boolean Vaga_MOD) {
        this.Vaga_MOD = Vaga_MOD;
    }

    public Boolean getObs_MOD() {
        return Obs_MOD;
    }

    public void setObs_MOD(Boolean Obs_MOD) {
        this.Obs_MOD = Obs_MOD;
    }

    public Boolean getColunaStorage_MOD() {
        return ColunaStorage_MOD;
    }

    public void setColunaStorage_MOD(Boolean ColunaStorage_MOD) {
        this.ColunaStorage_MOD = ColunaStorage_MOD;
    }

    public ColunaUtil getColunaUtil() {
        return colunaUtil;
    }

    public void setColunaUtil(ColunaUtil colunaUtil) {
        this.colunaUtil = colunaUtil;
    }

    public Boolean getColunaUtil_MOD() {
        return ColunaUtil_MOD;
    }

    public void setColunaUtil_MOD(Boolean ColunaUtil_MOD) {
        this.ColunaUtil_MOD = ColunaUtil_MOD;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        final ColunaVaga other = (ColunaVaga) obj;
        return Objects.equals(this.id, other.id);
    }

}
