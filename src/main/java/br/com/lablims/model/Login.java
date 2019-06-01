/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lablims.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import br.com.lablims.interfaces.EntidadeBase;

/**
 *
 * @author rafael.lopes
 */
@Entity
@Table(name = "tb_login")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Login implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "lastlogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastlogin;

    @Column(name = "lastlogout")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastlogout;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_login_usuario"))
    private Usuario usuario;

    public Login() {
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Date getLastlogout() {
        return lastlogout;
    }

    public void setLastlogout(Date lastlogout) {
        this.lastlogout = lastlogout;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Login other = (Login) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Login{" + "id=" + id + ", lastlogin=" + lastlogin + ", lastlogout=" + lastlogout + ", usuario=" + usuario + '}';
    }

}
