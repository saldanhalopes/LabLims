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
 * @author rafael.lopes
 */
@Entity
@Table(name = "tb_seguranca")
@NamedQueries({
    @NamedQuery(name = "Seguranca.findByTipo", query = "SELECT s FROM Seguranca s WHERE s.tipo = :tipo")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_seguranca_auditoria")
@EntityListeners(AuditListener.class)
public class Seguranca implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;
    public static final String STATUS = "Status";
    public static final String LETRAS = "Letras na Senha";
    public static final String CRIPTO = "Criptografia";
    public static final String FECHAR = "Fechar Programa";
    public static final String MOTIVO = "Motivo";
    public static final String VALIDAR = "Validar";
    public static final String TAM_SENHA = "Tamanho da Senha";
    public static final String EXPIRAR = "Expirar Senha";
    public static final String FALHA = "Falha Acesso";
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean Numero_MOD;

    @Transient
    private Boolean Tipo_MOD;

    public Seguranca() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Boolean getNumero_MOD() {
        return Numero_MOD;
    }

    public void setNumero_MOD(Boolean Numero_MOD) {
        this.Numero_MOD = Numero_MOD;
    }

    public Boolean getTipo_MOD() {
        return Tipo_MOD;
    }

    public void setTipo_MOD(Boolean Tipo_MOD) {
        this.Tipo_MOD = Tipo_MOD;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Seguranca other = (Seguranca) obj;
        return Objects.equals(this.id, other.id);
    }

}
