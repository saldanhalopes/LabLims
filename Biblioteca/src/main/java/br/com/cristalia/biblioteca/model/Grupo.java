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
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;


/**
 *
 * @author rafael
 */
@Entity
@Table(name = "tb_grupo")
@NamedQueries({
    @NamedQuery(name = "Groups.findAll", query = "SELECT g FROM Grupo g"),
    @NamedQuery(name = "Groups.findId", query = "SELECT g FROM Grupo g WHERE g.id = :id"),
    @NamedQuery(name = "Groups.findByGrupoNome", query = "SELECT g FROM Grupo g WHERE g.grupoNome = :grupoNome"),
    @NamedQuery(name = "Groups.findByGrupoTipo", query = "SELECT g FROM Grupo g WHERE g.grupoTipo = :grupoTipo")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_grupo_auditoria")
@EntityListeners(AuditListener.class)
public class Grupo implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "grupo_nome", nullable = false)
    private String grupoNome;

    @Column(name = "grupo_tipo", nullable = false)
    private String grupoTipo;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean Nome_MOD;

    @Transient
    private Boolean Tipo_MOD;

    public Grupo() {
    }

    public Grupo(Long id) {
        this.id = id;
    }

    public Grupo(String grupoNome, String grupoTipo) {
        this.grupoNome = grupoNome;
        this.grupoTipo = grupoTipo;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrupoNome() {
        return grupoNome;
    }

    public void setGrupoNome(String grupoNome) {
        this.grupoNome = grupoNome;
    }

    public String getGrupoTipo() {
        return grupoTipo;
    }

    public void setGrupoTipo(String grupoTipo) {
        this.grupoTipo = grupoTipo;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Boolean getNome_MOD() {
        return Nome_MOD;
    }

    public void setNome_MOD(Boolean Nome_MOD) {
        this.Nome_MOD = Nome_MOD;
    }

    public Boolean getTipo_MOD() {
        return Tipo_MOD;
    }

    public void setTipo_MOD(Boolean Tipo_MOD) {
        this.Tipo_MOD = Tipo_MOD;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Grupo other = (Grupo) obj;
        return Objects.equals(this.id, other.id);
    }

}
