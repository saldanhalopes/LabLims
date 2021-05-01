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

import br.com.lablims.audit.AuditListener;
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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

 /**
 * O <code>Usuario</code> classe Usuario
 *
 * @author rafae.lopes
 * @version 1.00
 */
@Entity
@Table(name = "tb_fase_movel_log")
@NamedQueries({
    @NamedQuery(name = "FaseMovelLog.findAll", query = "SELECT fmLog FROM FaseMovel fmLog")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_fase_movel_log_auditoria")
@EntityListeners(AuditListener.class)
public class FaseMovelLog implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fase_movel_a;
    private Integer fase_movel_a_qt;
    private String fase_movel_a_lote;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fase_movel_a_validade;
    
    private String fase_movel_b;
    private Integer fase_movel_b_qt;
    private String fase_movel_b_lote;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fase_movel_b_validade;
    
    private String fase_movel_c;
    private Integer fase_movel_c_qt;
    private String fase_movel_c_lote;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fase_movel_c_validade;
    
    private String fase_movel_d;
    private Integer fase_movel_d_qt;
    private String fase_movel_d_lote;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fase_movel_d_validade;
    
    private String fase_movel_e;
    private String fase_movel_e_lote;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fase_movel_e_validade;
    
    private String fase_movel_f;
    private String fase_movel_f_lote;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fase_movel_f_validade;
    
    
    @ManyToOne()
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario usuario;
    
    @Column(name = "data_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataRegistro;

    @ManyToOne()
    @JoinColumn(name = "campanha_id", referencedColumnName = "id")
    private Campanha campanha;
    
    @ManyToOne()
    @JoinColumn(name = "equipamento_id", referencedColumnName = "id")
    private Equipamento equipamento;
  
    @Column(name = "version")
    private Integer version;

    public FaseMovelLog() {
    }
      
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFase_movel_a() {
        return fase_movel_a;
    }

    public void setFase_movel_a(String fase_movel_a) {
        this.fase_movel_a = fase_movel_a;
    }

    public Integer getFase_movel_a_qt() {
        return fase_movel_a_qt;
    }

    public void setFase_movel_a_qt(Integer fase_movel_a_qt) {
        this.fase_movel_a_qt = fase_movel_a_qt;
    }

    public String getFase_movel_a_lote() {
        return fase_movel_a_lote;
    }

    public void setFase_movel_a_lote(String fase_movel_a_lote) {
        this.fase_movel_a_lote = fase_movel_a_lote;
    }

    public Date getFase_movel_a_validade() {
        return fase_movel_a_validade;
    }

    public void setFase_movel_a_validade(Date fase_movel_a_validade) {
        this.fase_movel_a_validade = fase_movel_a_validade;
    }

    public String getFase_movel_b() {
        return fase_movel_b;
    }

    public void setFase_movel_b(String fase_movel_b) {
        this.fase_movel_b = fase_movel_b;
    }

    public Integer getFase_movel_b_qt() {
        return fase_movel_b_qt;
    }

    public void setFase_movel_b_qt(Integer fase_movel_b_qt) {
        this.fase_movel_b_qt = fase_movel_b_qt;
    }

    public String getFase_movel_b_lote() {
        return fase_movel_b_lote;
    }

    public void setFase_movel_b_lote(String fase_movel_b_lote) {
        this.fase_movel_b_lote = fase_movel_b_lote;
    }

    public Date getFase_movel_b_validade() {
        return fase_movel_b_validade;
    }

    public void setFase_movel_b_validade(Date fase_movel_b_validade) {
        this.fase_movel_b_validade = fase_movel_b_validade;
    }

    public String getFase_movel_c() {
        return fase_movel_c;
    }

    public void setFase_movel_c(String fase_movel_c) {
        this.fase_movel_c = fase_movel_c;
    }

    public Integer getFase_movel_c_qt() {
        return fase_movel_c_qt;
    }

    public void setFase_movel_c_qt(Integer fase_movel_c_qt) {
        this.fase_movel_c_qt = fase_movel_c_qt;
    }

    public String getFase_movel_c_lote() {
        return fase_movel_c_lote;
    }

    public void setFase_movel_c_lote(String fase_movel_c_lote) {
        this.fase_movel_c_lote = fase_movel_c_lote;
    }

    public Date getFase_movel_c_validade() {
        return fase_movel_c_validade;
    }

    public void setFase_movel_c_validade(Date fase_movel_c_validade) {
        this.fase_movel_c_validade = fase_movel_c_validade;
    }

    public String getFase_movel_d() {
        return fase_movel_d;
    }

    public void setFase_movel_d(String fase_movel_d) {
        this.fase_movel_d = fase_movel_d;
    }

    public Integer getFase_movel_d_qt() {
        return fase_movel_d_qt;
    }

    public void setFase_movel_d_qt(Integer fase_movel_d_qt) {
        this.fase_movel_d_qt = fase_movel_d_qt;
    }

    public String getFase_movel_d_lote() {
        return fase_movel_d_lote;
    }

    public void setFase_movel_d_lote(String fase_movel_d_lote) {
        this.fase_movel_d_lote = fase_movel_d_lote;
    }

    public Date getFase_movel_d_validade() {
        return fase_movel_d_validade;
    }

    public void setFase_movel_d_validade(Date fase_movel_d_validade) {
        this.fase_movel_d_validade = fase_movel_d_validade;
    }

    public String getFase_movel_e() {
        return fase_movel_e;
    }

    public void setFase_movel_e(String fase_movel_e) {
        this.fase_movel_e = fase_movel_e;
    }

    public String getFase_movel_e_lote() {
        return fase_movel_e_lote;
    }

    public void setFase_movel_e_lote(String fase_movel_e_lote) {
        this.fase_movel_e_lote = fase_movel_e_lote;
    }

    public Date getFase_movel_e_validade() {
        return fase_movel_e_validade;
    }

    public void setFase_movel_e_validade(Date fase_movel_e_validade) {
        this.fase_movel_e_validade = fase_movel_e_validade;
    }

    public String getFase_movel_f() {
        return fase_movel_f;
    }

    public void setFase_movel_f(String fase_movel_f) {
        this.fase_movel_f = fase_movel_f;
    }

    public String getFase_movel_f_lote() {
        return fase_movel_f_lote;
    }

    public void setFase_movel_f_lote(String fase_movel_f_lote) {
        this.fase_movel_f_lote = fase_movel_f_lote;
    }

    public Date getFase_movel_f_validade() {
        return fase_movel_f_validade;
    }

    public void setFase_movel_f_validade(Date fase_movel_f_validade) {
        this.fase_movel_f_validade = fase_movel_f_validade;
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

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final FaseMovelLog other = (FaseMovelLog) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
    

}
