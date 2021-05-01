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
package br.com.lablims.model;

import br.com.lablims.audit.Audit;
import br.com.lablims.audit.AuditListener;
import br.com.lablims.interfaces.EntidadeBase;
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
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author rafael.lopes
 */

@Entity
@Table(name = "tb_analise_tipo")
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_analise_tipo_auditoria")
@EntityListeners(AuditListener.class)
public class AnaliseTipo implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "analise_tipo")
    private String analiseTipo;
    
    @Column(name = "descricao_analise_tipo")
    private String descricaoAnaliseTipo;
    
    @Column(name = "sigla_analise_tipo")
    private String siglaAnaliseTipo;
    
    @Column(name = "version")
    private Integer version;
    
    @Transient
    private Audit audit = new Audit();
    
    @Transient
    private Boolean AnaliseTipo_MOD;
    
    @Transient
    private Boolean Descricao_AnaliseTipo_MOD;
    
    @Transient
    private Boolean Sigla_AnaliseTipo_MOD;

    public AnaliseTipo() {
    }

    public AnaliseTipo(Long id) {
        this.id = id;
    }

    public AnaliseTipo(String analiseTipo) {
        this.analiseTipo = analiseTipo;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnaliseTipo() {
        return analiseTipo;
    }

    public void setAnaliseTipo(String analiseTipo) {
        this.analiseTipo = analiseTipo;
    }

    public String getDescricaoAnaliseTipo() {
        return descricaoAnaliseTipo;
    }

    public void setDescricaoAnaliseTipo(String descricaoAnaliseTipo) {
        this.descricaoAnaliseTipo = descricaoAnaliseTipo;
    }

    public String getSiglaAnaliseTipo() {
        return siglaAnaliseTipo;
    }

    public void setSiglaAnaliseTipo(String siglaAnaliseTipo) {
        this.siglaAnaliseTipo = siglaAnaliseTipo;
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

    public Boolean getAnaliseTipo_MOD() {
        return AnaliseTipo_MOD;
    }

    public void setAnaliseTipo_MOD(Boolean AnaliseTipo_MOD) {
        this.AnaliseTipo_MOD = AnaliseTipo_MOD;
    }

    public Boolean getDescricao_AnaliseTipo_MOD() {
        return Descricao_AnaliseTipo_MOD;
    }

    public void setDescricao_AnaliseTipo_MOD(Boolean Descricao_AnaliseTipo_MOD) {
        this.Descricao_AnaliseTipo_MOD = Descricao_AnaliseTipo_MOD;
    }

    public Boolean getSigla_AnaliseTipo_MOD() {
        return Sigla_AnaliseTipo_MOD;
    }

    public void setSigla_AnaliseTipo_MOD(Boolean Sigla_AnaliseTipo_MOD) {
        this.Sigla_AnaliseTipo_MOD = Sigla_AnaliseTipo_MOD;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final AnaliseTipo other = (AnaliseTipo) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
