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
package br.com.cristalia.colunas.model;

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
 * O <code>Usuario</code> classe Coluna Config
 *
 * @author rafae.lopes
 * @version 1.00
 */
@Entity
@Table(name = "tb_coluna_config")
@NamedQueries({
    @NamedQuery(name = "ColunaConfig.findAll", query = "SELECT conf FROM ColunaConfig conf"),
    @NamedQuery(name = "ColunaConfig.checkIsExits", query = "SELECT conf FROM ColunaConfig conf WHERE conf.tipo = :tipo AND conf.configuracao = :valor"),
    @NamedQuery(name = "ColunaConfig.findColunaConfigByTipo", query = "SELECT conf FROM ColunaConfig conf WHERE conf.tipo = :tipo ORDER BY conf.configuracao ASC")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_coluna_config_auditoria")
@EntityListeners(AuditListener.class)
public class ColunaConfig implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo")
    private String tipo;
    
    @Column(name = "configuracao")
    private String configuracao;
    
    @Column(name = "descricao")
    private String descricao;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean Tipo_MOD;
    
    @Transient
    private Boolean Configuracao_MOD;
    
    @Transient
    private Boolean Descricao_MOD;
    
    public ColunaConfig() {
    }

    public ColunaConfig(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(String configuracao) {
        this.configuracao = configuracao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public Boolean getTipo_MOD() {
        return Tipo_MOD;
    }

    public void setTipo_MOD(Boolean Tipo_MOD) {
        this.Tipo_MOD = Tipo_MOD;
    }

    public Boolean getConfiguracao_MOD() {
        return Configuracao_MOD;
    }

    public void setConfiguracao_MOD(Boolean Configuracao_MOD) {
        this.Configuracao_MOD = Configuracao_MOD;
    }

    public Boolean getDescricao_MOD() {
        return Descricao_MOD;
    }

    public void setDescricao_MOD(Boolean Descricao_MOD) {
        this.Descricao_MOD = Descricao_MOD;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final ColunaConfig other = (ColunaConfig) obj;
        return Objects.equals(this.id, other.id);
    }

    
}
