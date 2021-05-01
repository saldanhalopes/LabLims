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
import br.com.lablims.interfaces.EntidadeBase;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import br.com.lablims.audit.AuditListener;
import javax.persistence.Transient;

/**
 * O <code>Usuario</code> classe Usuario
 *
 * @author rafae.lopes
 * @version 1.00
 */
@Entity
@Table(name = "tb_usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findById", query = "SELECT u FROM Usuario u WHERE u.id = :id"),
    @NamedQuery(name = "Usuario.findByUsuario", query = "SELECT u FROM Usuario u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "Usuario.findByNome", query = "SELECT u FROM Usuario u WHERE u.nome = :nome"),
    @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email"),
    @NamedQuery(name = "Usuario.findLogin", query = "Select u FROM Usuario u WHERE u.usuario = :usuario AND u.pass = :pass")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_usuario_auditoria")
@EntityListeners(AuditListener.class)
public class Usuario implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario", unique = true)
    private String usuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "pass")
    private String pass;

    @Column(name = "change_pass")
    private Boolean changePass;

    @Column(name = "lock")
    private Boolean lock;

    @Column(name = "email")
    private String email;

    @Column(name = "cracha")
    private String cracha;

    @Column(name = "failed_access_count")
    private Integer failedAccessCount;

    @Column(name = "created", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date created;

    @Column(name = "lastlogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Column(name = "lastlogout")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogout;
    
    @Column(name = "last_change_pass")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChangePass;

    @ManyToOne()
    @JoinColumn(name = "turno_id", referencedColumnName = "id")
    private Turno turno;

    @ManyToOne()
    @JoinColumn(name = "setor_id", referencedColumnName = "id")
    private Setor setor;

    @ManyToOne()
    @JoinColumn(name = "grupo_id", referencedColumnName = "id")
    private Grupo grupo;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean Usuario_MOD;
    
    @Transient
    private Boolean Nome_MOD;
    
    @Transient
    private Boolean Pass_MOD;
    
    @Transient
    private Boolean Change_Pass_MOD;
    
    @Transient
    private Boolean Lock_MOD;
    
    @Transient
    private Boolean Email_MOD;
    
    @Transient
    private Boolean Cracha_MOD;
    
    @Transient
    private Boolean Turno_MOD;
    
    @Transient
    private Boolean Setor_MOD;
    
    @Transient
    private Boolean Grupo_MOD;

    public Usuario() {
    }

    public Usuario(Long id) {
        this.id = id;
    }

    public Usuario(String usuario) {
        this.usuario = usuario;
    }

    public Usuario(String usuario, String pass) {
        this.usuario = usuario;
        this.pass = pass;
    }

    public Usuario(String usuario, String nome, String pass, String email, String cracha,
            Turno turno, Date created, Setor setor, Grupo grupo) {
        this.usuario = usuario;
        this.nome = nome;
        this.pass = pass;
        this.email = email;
        this.cracha = cracha;
        this.turno = turno;
        this.created = created;
        this.setor = setor;
        this.grupo = grupo;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getLock() {
        return lock;
    }

    public void setLock(Boolean lock) {
        this.lock = lock;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCracha() {
        return cracha;
    }

    public void setCracha(String cracha) {
        this.cracha = cracha;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getChangePass() {
        return changePass;
    }

    public void setChangePass(Boolean changePass) {
        this.changePass = changePass;
    }

    public Integer getFailedAccessCount() {
        return failedAccessCount;
    }

    public void setFailedAccessCount(Integer failedAccessCount) {
        this.failedAccessCount = failedAccessCount;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLastLogout() {
        return lastLogout;
    }

    public void setLastLogout(Date lastLogout) {
        this.lastLogout = lastLogout;
    }

    public Date getLastChangePass() {
        return lastChangePass;
    }

    public void setLastChangePass(Date lastChangePass) {
        this.lastChangePass = lastChangePass;
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

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Boolean getUsuario_MOD() {
        return Usuario_MOD;
    }

    public void setUsuario_MOD(Boolean Usuario_MOD) {
        this.Usuario_MOD = Usuario_MOD;
    }

    public Boolean getNome_MOD() {
        return Nome_MOD;
    }

    public void setNome_MOD(Boolean Nome_MOD) {
        this.Nome_MOD = Nome_MOD;
    }

    public Boolean getPass_MOD() {
        return Pass_MOD;
    }

    public void setPass_MOD(Boolean Pass_MOD) {
        this.Pass_MOD = Pass_MOD;
    }

    public Boolean getChange_Pass_MOD() {
        return Change_Pass_MOD;
    }

    public void setChange_Pass_MOD(Boolean Change_Pass_MOD) {
        this.Change_Pass_MOD = Change_Pass_MOD;
    }

    public Boolean getLock_MOD() {
        return Lock_MOD;
    }

    public void setLock_MOD(Boolean Lock_MOD) {
        this.Lock_MOD = Lock_MOD;
    }

    public Boolean getEmail_MOD() {
        return Email_MOD;
    }

    public void setEmail_MOD(Boolean Email_MOD) {
        this.Email_MOD = Email_MOD;
    }

    public Boolean getCracha_MOD() {
        return Cracha_MOD;
    }

    public void setCracha_MOD(Boolean Cracha_MOD) {
        this.Cracha_MOD = Cracha_MOD;
    }

    public Boolean getTurno_MOD() {
        return Turno_MOD;
    }

    public void setTurno_MOD(Boolean Turno_MOD) {
        this.Turno_MOD = Turno_MOD;
    }

    public Boolean getSetor_MOD() {
        return Setor_MOD;
    }

    public void setSetor_MOD(Boolean Setor_MOD) {
        this.Setor_MOD = Setor_MOD;
    }

    public Boolean getGrupo_MOD() {
        return Grupo_MOD;
    }

    public void setGrupo_MOD(Boolean Grupo_MOD) {
        this.Grupo_MOD = Grupo_MOD;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        return Objects.equals(this.id, other.id);
    }

}
