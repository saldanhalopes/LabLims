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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 *
 * @author rafael.lopes
 */
@Entity
@Table(name = "tb_analise_produtividade")
@NamedQueries({
    @NamedQuery(name = "AnaliseProdutividade.findAll", query = "Select a FROM AnaliseProdutividade a")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_analise_produtividade_auditoria")
@EntityListeners(AuditListener.class)
public class AnaliseProdutividade implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "analise_produtividade")
    private String analiseProdutividade;
    
    @Column(name = "sigla_analise_produtividade")
    private String siglaAnaliseProdutividade;
    
    @Column(name = "descricao_analise_produtividade")
    private String descricaoAnaliseProdutividade;

    @Column(name = "cor")
    private String cor;
    
    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean MOD;

    @Transient
    private Boolean AnaliseProdutividade_MOD;

    @Transient
    private Boolean SiglaAnaliseProdutividade_MOD;

    @Transient
    private Boolean DescricaoAnaliseProdutividade_MOD;
    
    @Transient
    private Boolean Cor_MOD;

    public AnaliseProdutividade() {
    }

    public AnaliseProdutividade(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnaliseProdutividade() {
        return analiseProdutividade;
    }

    public void setAnaliseProdutividade(String analiseProdutividade) {
        this.analiseProdutividade = analiseProdutividade;
    }

    public String getSiglaAnaliseProdutividade() {
        return siglaAnaliseProdutividade;
    }

    public void setSiglaAnaliseProdutividade(String siglaAnaliseProdutividade) {
        this.siglaAnaliseProdutividade = siglaAnaliseProdutividade;
    }

    public String getDescricaoAnaliseProdutividade() {
        return descricaoAnaliseProdutividade;
    }

    public void setDescricaoAnaliseProdutividade(String descricaoAnaliseProdutividade) {
        this.descricaoAnaliseProdutividade = descricaoAnaliseProdutividade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
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

    public Boolean getMOD() {
        return MOD;
    }

    public void setMOD(Boolean MOD) {
        this.MOD = MOD;
    }

    public Boolean getAnaliseProdutividade_MOD() {
        return AnaliseProdutividade_MOD;
    }

    public void setAnaliseProdutividade_MOD(Boolean AnaliseProdutividade_MOD) {
        this.AnaliseProdutividade_MOD = AnaliseProdutividade_MOD;
    }

    public Boolean getSiglaAnaliseProdutividade_MOD() {
        return SiglaAnaliseProdutividade_MOD;
    }

    public void setSiglaAnaliseProdutividade_MOD(Boolean SiglaAnaliseProdutividade_MOD) {
        this.SiglaAnaliseProdutividade_MOD = SiglaAnaliseProdutividade_MOD;
    }

    public Boolean getDescricaoAnaliseProdutividade_MOD() {
        return DescricaoAnaliseProdutividade_MOD;
    }

    public void setDescricaoAnaliseProdutividade_MOD(Boolean DescricaoAnaliseProdutividade_MOD) {
        this.DescricaoAnaliseProdutividade_MOD = DescricaoAnaliseProdutividade_MOD;
    }

    public Boolean getCor_MOD() {
        return Cor_MOD;
    }

    public void setCor_MOD(Boolean Cor_MOD) {
        this.Cor_MOD = Cor_MOD;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final AnaliseProdutividade other = (AnaliseProdutividade) obj;
        return Objects.equals(this.id, other.id);
    }


    
}
