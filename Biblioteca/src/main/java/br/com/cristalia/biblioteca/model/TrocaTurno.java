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
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import java.util.Date;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author rafael
 */
@Entity
@Table(name = "tb_troca_turno")
@NamedQueries({
    @NamedQuery(name = "TrocaTurno.findByAgrupador", query = "SELECT tt FROM TrocaTurno tt WHERE tt.agrupador = :agrupador ORDER BY tt.id DESC")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_troca_turno_auditoria")
@EntityListeners(AuditListener.class)
public class TrocaTurno implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "passagem")
    private String passagem;
    
    @Column(name = "agrupador")
    private String agrupador;
    
    @ManyToOne()
    @JoinColumn(name = "turno_id", referencedColumnName = "id")
    private Turno turno;
    
    @ManyToOne()
    @JoinColumn(name = "setor_id", referencedColumnName = "id")
    private Setor setor;
    
    @ManyToOne()
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    
    @ManyToOne()
    @JoinColumn(name = "grupo_id", referencedColumnName = "id")
    private Grupo grupo;

    @Column(name = "data_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro;


    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();
    
    @Transient
    private Boolean Passagem_MOD;

    @Transient
    private Boolean Turno_MOD;
    
    @Transient
    private Boolean Setor_MOD;

    @Transient
    private Boolean Usuario_MOD;
    
    @Transient
    private Boolean Agrupador_MOD;
    
      @Transient
    private Boolean DataRegistro_MOD;


    public TrocaTurno() {
    }

    public TrocaTurno(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassagem() {
        return passagem;
    }

    public void setPassagem(String passagem) {
        this.passagem = passagem;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
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

    public Boolean getPassagem_MOD() {
        return Passagem_MOD;
    }

    public void setPassagem_MOD(Boolean Passagem_MOD) {
        this.Passagem_MOD = Passagem_MOD;
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

    public Boolean getUsuario_MOD() {
        return Usuario_MOD;
    }

    public void setUsuario_MOD(Boolean Usuario_MOD) {
        this.Usuario_MOD = Usuario_MOD;
    }

    public Boolean getDataRegistro_MOD() {
        return DataRegistro_MOD;
    }

    public void setDataRegistro_MOD(Boolean DataRegistro_MOD) {
        this.DataRegistro_MOD = DataRegistro_MOD;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public String getAgrupador() {
        return agrupador;
    }

    public void setAgrupador(String agrupador) {
        this.agrupador = agrupador;
    }

    public Boolean getAgrupador_MOD() {
        return Agrupador_MOD;
    }

    public void setAgrupador_MOD(Boolean Agrupador_MOD) {
        this.Agrupador_MOD = Agrupador_MOD;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final TrocaTurno other = (TrocaTurno) obj;
        return Objects.equals(this.id, other.id);
    }

   
}
