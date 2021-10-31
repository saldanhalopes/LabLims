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

//import br.com.cristalia.colunas.model.Coluna;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.EntityListeners;
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
@Table(name = "tb_plano_analise_coluna")
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_plano_analise_coluna_auditoria")
@EntityListeners(AuditListener.class)
public class PlanoAnaliseColuna implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "plano_analise_id", referencedColumnName = "id")
    private PlanoAnalise planoAnalise;

//    @ManyToOne()
//    @JoinColumn(name = "coluna_id", referencedColumnName = "id")
//    private Coluna coluna;

    @Column(name = "qtd_utilizada")
    private Integer qtdUtilizada;
    
    @ManyToOne()
    @JoinColumn(name = "unidade_id", referencedColumnName = "id")
    private UnidadeMedida unidade;

    @Column(name = "version")
    private Integer version;
    
    @Column(name = "obs")
    private String obs;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean PlanoAnalise_MOD;
    
    @Transient
    private Boolean Coluna_MOD;
    
    @Transient
    private Boolean QtdUtilizada_MOD;
    
    @Transient
    private Boolean Unidade_MOD;
    
    @Transient
    private Boolean Obs_MOD;
    
    public PlanoAnaliseColuna() {
    }

    public PlanoAnaliseColuna(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlanoAnalise getPlanoAnalise() {
        return planoAnalise;
    }

    public void setPlanoAnalise(PlanoAnalise planoAnalise) {
        this.planoAnalise = planoAnalise;
    }

    public Integer getQtdUtilizada() {
        return qtdUtilizada;
    }

    public void setQtdUtilizada(Integer qtdUtilizada) {
        this.qtdUtilizada = qtdUtilizada;
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

    public Boolean getPlanoAnalise_MOD() {
        return PlanoAnalise_MOD;
    }

    public void setPlanoAnalise_MOD(Boolean PlanoAnalise_MOD) {
        this.PlanoAnalise_MOD = PlanoAnalise_MOD;
    }

    public Boolean getQtdUtilizada_MOD() {
        return QtdUtilizada_MOD;
    }

    public void setQtdUtilizada_MOD(Boolean QtdUtilizada_MOD) {
        this.QtdUtilizada_MOD = QtdUtilizada_MOD;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public UnidadeMedida getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeMedida unidade) {
        this.unidade = unidade;
    }

//    public Coluna getColuna() {
//        return coluna;
//    }
//
//    public void setColuna(Coluna coluna) {
//        this.coluna = coluna;
//    }

    public Boolean getColuna_MOD() {
        return Coluna_MOD;
    }

    public void setColuna_MOD(Boolean Coluna_MOD) {
        this.Coluna_MOD = Coluna_MOD;
    }

    public Boolean getObs_MOD() {
        return Obs_MOD;
    }

    public void setObs_MOD(Boolean Obs_MOD) {
        this.Obs_MOD = Obs_MOD;
    }

    public Boolean getUnidade_MOD() {
        return Unidade_MOD;
    }

    public void setUnidade_MOD(Boolean Unidade_MOD) {
        this.Unidade_MOD = Unidade_MOD;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final PlanoAnaliseColuna other = (PlanoAnaliseColuna) obj;
        return Objects.equals(this.id, other.id);
    }


}
