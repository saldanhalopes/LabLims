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
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import br.com.lablims.interfaces.EntidadeBase;
import javax.persistence.EntityListeners;
import javax.persistence.Transient;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author rafael.lopes
 */
@Entity
@Table(name = "tb_regra_acesso")
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_regra_acesso_auditoria")
@EntityListeners(AuditListener.class)
public class RegraAcesso implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "acesso_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_acesso_regra_acesso"))
    private Acesso acesso;

    @ManyToOne()
    @JoinColumn(name = "form_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_form_regra_acesso"))
    private Form form;

    @ManyToOne()
    @JoinColumn(name = "grupo_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_grupo_regra_acesso"))
    private Grupo grupo;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Integer MOD;
    
    @Transient
    private Integer Acesso_MOD;
    
    @Transient
    private Integer Form_MOD;
    
    @Transient
    private Integer Grupo_MOD;
    
    public RegraAcesso() {
    }

    public RegraAcesso(Integer id) {
        this.id = id;
    }

    public RegraAcesso(Acesso acesso, Form form, Grupo grupo) {
        this.acesso = acesso;
        this.form = form;
        this.grupo = grupo;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Acesso getAcesso() {
        return acesso;
    }

    public void setAcesso(Acesso acesso) {
        this.acesso = acesso;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
    
    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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

    public Integer getAcesso_MOD() {
        return Acesso_MOD;
    }

    public void setAcesso_MOD(Integer Acesso_MOD) {
        this.Acesso_MOD = Acesso_MOD;
    }

    public Integer getForm_MOD() {
        return Form_MOD;
    }

    public void setForm_MOD(Integer Form_MOD) {
        this.Form_MOD = Form_MOD;
    }

    public Integer getGrupo_MOD() {
        return Grupo_MOD;
    }

    public void setGrupo_MOD(Integer Grupo_MOD) {
        this.Grupo_MOD = Grupo_MOD;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final RegraAcesso other = (RegraAcesso) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "RegraAcesso{" + "id=" + id + ", acesso=" + acesso + ", form=" + form + ", grupo=" + grupo + '}';
    }

}
