/*
 * Copyright (C) 2019 rafael.lopes
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
package br.com.lablims.audit;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * O <code>Splash</code> implementa classe que recebe os dados de alteracao de
 * cada classe.
 *
 * @author rafae.lopes
 * @version 1.00
 * @see br.com.lablims.audit.AuditRevision
 */
@Entity
@Table(name = "tba_audit_entity_type")
public class AuditEntityType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private AuditRevision revision;

    @Column(name = "entity_class_name")
    private String entityClassName;

    @Column(name = "modified_type")
    private Integer modifiedType;

    @Column(name = "id_entity")
    private Integer idEntity;

    public AuditEntityType() {
    }

    public AuditEntityType(AuditRevision revision, String entityClassName,
            Integer modifiedType, Integer idEntity) {
        this.revision = revision;
        this.entityClassName = entityClassName;
        this.modifiedType = modifiedType;
        this.idEntity = idEntity;
    }

    public Integer getModifiedType() {
        return modifiedType;
    }

    public void setModifiedType(Integer modifiedType) {
        this.modifiedType = modifiedType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuditRevision getRevision() {
        return revision;
    }

    public void setRevision(AuditRevision revision) {
        this.revision = revision;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    public Integer getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(Integer idEntity) {
        this.idEntity = idEntity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuditEntityType other = (AuditEntityType) obj;
        return Objects.equals(this.id, other.id);
    }

}
