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
import javax.persistence.EntityListeners;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author rafael.lopes
 */
@Entity
@Table(name = "tb_form")
@NamedQueries({
    @NamedQuery(name = "Form.findAll", query = "SELECT f FROM Form f"),
    @NamedQuery(name = "Form.findById", query = "SELECT f FROM Form f WHERE f.id = :id"),
    @NamedQuery(name = "Form.findByForm", query = "SELECT f FROM Form f WHERE f.formName = :formName")})
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_form_auditoria")
@EntityListeners(AuditListener.class)
public class Form implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "form_name")
    private String formName;
    
    @Column(name = "propriedade")
    private String propriedade;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();
    
    @Transient
    private Boolean FormName_MOD;
    
    @Transient
    private Boolean Propriedade_MOD;
    
    public Form() {
    }

    public Form(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
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

    public String getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(String propriedade) {
        this.propriedade = propriedade;
    }

    public Boolean getFormName_MOD() {
        return FormName_MOD;
    }

    public void setFormName_MOD(Boolean FormName_MOD) {
        this.FormName_MOD = FormName_MOD;
    }

    public Boolean getPropriedade_MOD() {
        return Propriedade_MOD;
    }

    public void setPropriedade_MOD(Boolean Propriedade_MOD) {
        this.Propriedade_MOD = Propriedade_MOD;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Form other = (Form) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Form{" + "id=" + id + ", formName=" + formName + '}';
    }

}
