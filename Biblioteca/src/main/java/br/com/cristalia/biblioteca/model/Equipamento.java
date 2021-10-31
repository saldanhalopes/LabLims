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
import javax.persistence.Transient;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

 /**
 * O <code>Equipamento</code> classe Equipamento
 *
 * @author rafae.lopes
 * @version 1.00
 */
@Entity
@Table(name = "tb_equipamento")
@NamedQueries({
    @NamedQuery(name = "Equipamento.findAll", query = "SELECT equip FROM Equipamento equip"),
    @NamedQuery(name = "Equipamento.findTag", query = "SELECT equip FROM Equipamento equip WHERE equip.tag = :tag")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_equipamento_auditoria")
@EntityListeners(AuditListener.class)
public class Equipamento implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "descricao")
    private String descricao;
       
    @Column(name = "tag")
    private String tag;
    
    @ManyToOne()
    @JoinColumn(name = "tipo_equipamento_id", referencedColumnName = "id")
    private TipoEquipamento tipo;

    @ManyToOne()
    @JoinColumn(name = "setor_id", referencedColumnName = "id")
    private Setor setor;
    
    @Column(name = "fabricante")
    private String fabricante;

    @Column(name = "modelo")
    private String modelo;
    
    @Column(name = "ultima_calibracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaCalibracao;
    
    @Column(name = "proxima_calibracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date proximaCalibracao;

    @Column(name = "serial_number")
    private String serialNumber;
    
    @ManyToOne()
    @JoinColumn(name = "cetificado_id", referencedColumnName = "id")
    private Arquivos certificado;
    
    @ManyToOne()
    @JoinColumn(name = "manual_id", referencedColumnName = "id")
    private Arquivos manual;
    
    @ManyToOne()
    @JoinColumn(name = "escala_id", referencedColumnName = "id")
    private EscalaMedida escala;
    
    @Column(name = "procedimento")
    private String procedimento;
    
    @Column(name = "ativo") 
    private Boolean ativo;
    
    @Column(name = "version")
    private Integer version;
    
    @Column(name = "obs")
    private String obs;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean Tag_MOD;

    @Transient
    private Boolean Descricao_MOD;
    
    @Transient
    private Boolean Setor_MOD;
    
    @Transient
    private Boolean Fabricante_MOD;
    
    @Transient
    private Boolean Modelo_MOD;
    
    @Transient
    private Boolean Tipo_MOD;
    
    @Transient
    private Boolean UltimaCalibracao_MOD;
    
    @Transient
    private Boolean ProximaCalibracao_MOD;

    @Transient
    private Boolean Certificado_MOD;
    
    @Transient
    private Boolean Manual_MOD;
    
    @Transient
    private Boolean Escala_MOD;
    
    @Transient
    private Boolean Procedimento_MOD;

    @Transient
    private Boolean Ativo_MOD;
    
    @Transient
    private Boolean Serial_Number_MOD;
   
    @Transient
    private Boolean Obs_MOD;
        
    public Equipamento() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Audit getAudit() {
        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    public Boolean getTag_MOD() {
        return Tag_MOD;
    }

    public void setTag_MOD(Boolean Tag_MOD) {
        this.Tag_MOD = Tag_MOD;
    }

    public Boolean getSetor_MOD() {
        return Setor_MOD;
    }

    public void setSetor_MOD(Boolean Setor_MOD) {
        this.Setor_MOD = Setor_MOD;
    }

    public Boolean getFabricante_MOD() {
        return Fabricante_MOD;
    }

    public void setFabricante_MOD(Boolean Fabricante_MOD) {
        this.Fabricante_MOD = Fabricante_MOD;
    }

    public Boolean getModelo_MOD() {
        return Modelo_MOD;
    }

    public void setModelo_MOD(Boolean Modelo_MOD) {
        this.Modelo_MOD = Modelo_MOD;
    }

   public Boolean getObs_MOD() {
        return Obs_MOD;
    }

    public void setObs_MOD(Boolean Obs_MOD) {
        this.Obs_MOD = Obs_MOD;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Boolean getSerial_Number_MOD() {
        return Serial_Number_MOD;
    }

    public void setSerial_Number_MOD(Boolean Serial_Number_MOD) {
        this.Serial_Number_MOD = Serial_Number_MOD;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Date getUltimaCalibracao() {
        return ultimaCalibracao;
    }

    public void setUltimaCalibracao(Date ultimaCalibracao) {
        this.ultimaCalibracao = ultimaCalibracao;
    }

    public Date getProximaCalibracao() {
        return proximaCalibracao;
    }

    public void setProximaCalibracao(Date proximaCalibracao) {
        this.proximaCalibracao = proximaCalibracao;
    }

    public Arquivos getCertificado() {
        return certificado;
    }

    public void setCertificado(Arquivos certificado) {
        this.certificado = certificado;
    }

    public Arquivos getManual() {
        return manual;
    }

    public void setManual(Arquivos manual) {
        this.manual = manual;
    }

    public EscalaMedida getEscala() {
        return escala;
    }

    public void setEscala(EscalaMedida escala) {
        this.escala = escala;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getDescricao_MOD() {
        return Descricao_MOD;
    }

    public void setDescricao_MOD(Boolean Descricao_MOD) {
        this.Descricao_MOD = Descricao_MOD;
    }

    public Boolean getTipo_MOD() {
        return Tipo_MOD;
    }

    public void setTipo_MOD(Boolean Tipo_MOD) {
        this.Tipo_MOD = Tipo_MOD;
    }

    public Boolean getUltimaCalibracao_MOD() {
        return UltimaCalibracao_MOD;
    }

    public void setUltimaCalibracao_MOD(Boolean UltimaCalibracao_MOD) {
        this.UltimaCalibracao_MOD = UltimaCalibracao_MOD;
    }

    public Boolean getProximaCalibracao_MOD() {
        return ProximaCalibracao_MOD;
    }

    public void setProximaCalibracao_MOD(Boolean ProximaCalibracao_MOD) {
        this.ProximaCalibracao_MOD = ProximaCalibracao_MOD;
    }

    public Boolean getCertificado_MOD() {
        return Certificado_MOD;
    }

    public void setCertificado_MOD(Boolean Certificado_MOD) {
        this.Certificado_MOD = Certificado_MOD;
    }

    public Boolean getManual_MOD() {
        return Manual_MOD;
    }

    public void setManual_MOD(Boolean Manual_MOD) {
        this.Manual_MOD = Manual_MOD;
    }

    public Boolean getEscala_MOD() {
        return Escala_MOD;
    }

    public void setEscala_MOD(Boolean Escala_MOD) {
        this.Escala_MOD = Escala_MOD;
    }

    public Boolean getProcedimento_MOD() {
        return Procedimento_MOD;
    }

    public void setProcedimento_MOD(Boolean Procedimento_MOD) {
        this.Procedimento_MOD = Procedimento_MOD;
    }

    public Boolean getAtivo_MOD() {
        return Ativo_MOD;
    }

    public void setAtivo_MOD(Boolean Ativo_MOD) {
        this.Ativo_MOD = Ativo_MOD;
    }

    public TipoEquipamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoEquipamento tipo) {
        this.tipo = tipo;
    }
        
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
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
        final Equipamento other = (Equipamento) obj;
        return Objects.equals(this.id, other.id);
    }

}
