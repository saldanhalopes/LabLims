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
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

/**
 * O <code>AuditRevision</code> extende a classe <code>DefaultRevisionEntity</code>. Essa classe implementa
 * novos campos persolanizados para a auditoria.
 *
 * @author rafae.lopes
 * @version 1.00
 * @see br.com.lablims.audit.AuditEntityType
 * @see br.com.lablims.audit.AuditListener
 * @see org.hibernate.envers.DefaultRevisionEntity
 */
@Entity
@Table(name = "tba_audit_revinfo")
@RevisionEntity(AuditListener.class)
public class AuditRevision extends DefaultRevisionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Column(name = "ultima_modificacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaModificacao;

    @Column(name = "modificado_por")
    private String ultimaModificacaoPor;
    
    @Column(name = "computador")
    private String computador;

    @Column(name = "user_computador")
    private String userComputador;
    
    @Column(name = "motivo")
    private String motivo;

    @OneToMany(mappedBy = "revision", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<AuditEntityType> auditEntityType = new HashSet<>();

    
    public AuditRevision() {
    }

    public Date getUltimaModificacao() {
        return ultimaModificacao;
    }

    public void setUltimaModificacao(Date ultimaModificacao) {
        this.ultimaModificacao = ultimaModificacao;
    }

    public String getUltimaModificacaoPor() {
        return ultimaModificacaoPor;
    }

    public void setUltimaModificacaoPor(String ultimaModificacaoPor) {
        this.ultimaModificacaoPor = ultimaModificacaoPor;
    }

    public String getComputador() {
        return computador;
    }

    public void setComputador(String computador) {
        this.computador = computador;
    }

    public String getUserComputador() {
        return userComputador;
    }

    public void setUserComputador(String userComputador) {
        this.userComputador = userComputador;
    }

    public Set<AuditEntityType> getModifiedEntityTypes() {
        return auditEntityType;
    }

    public void setModifiedEntityTypes(Set<AuditEntityType> auditEntityType) {
        this.auditEntityType = auditEntityType;
    }
    
    public void addModifiedEntityType(String entityClassName, Integer modifiedType, Integer idEntity) {
        auditEntityType.add(new AuditEntityType(this, entityClassName, modifiedType, idEntity));
    }

    public Set<AuditEntityType> getAuditEntityType() {
        return auditEntityType;
    }

    public void setAuditEntityType(Set<AuditEntityType> auditEntityType) {
        this.auditEntityType = auditEntityType;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

}
