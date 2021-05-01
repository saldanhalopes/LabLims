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
import br.com.lablims.audit.AuditListener;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import br.com.lablims.interfaces.EntidadeBase;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tb_reagente")
@NamedQueries({
    @NamedQuery(name = "Reagente.findAll", query = "SELECT r FROM Reagente r"),
    @NamedQuery(name = "Reagente.findById", query = "SELECT r FROM Reagente r WHERE r.id = :id")})
@DynamicInsert(true)
@DynamicUpdate(true)
@Audited(withModifiedFlag = true)
@AuditTable(value = "tba_reagente_auditoria")
@EntityListeners(AuditListener.class)
public class Reagente implements EntidadeBase, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_reagente", nullable = false, unique = true)
    private Integer codReagente;

    @Column(name = "reagente")
    private String reagente;

    @ManyToOne()
    @JoinColumn(name = "unidade_id", referencedColumnName = "id")
    private UnidadeMedida unidade;

    @Column(name = "cas_number")
    private String casNumber;

    @Column(name = "qtd_estoque_min")
    private Integer qtdEstoqueMin;

    @Column(name = "qtd_estoque_max")
    private Integer qtdEstoqueMax;

    @Column(name = "compra_unica")
    private Boolean compraUnica;

    @Column(name = "enderecamento")
    private String enderecamento;

    @Column(name = "numero_identificacao")
    private String numeroIdentificacao;

    @Column(name = "armazenamento")
    private String armazenamento;

    @Column(name = "grau")
    private String grau;

    @Column(name = "pureza")
    private String pureza;

    @Column(name = "classe")
    private String classe;

    @Column(name = "controlado")
    private String controlado;

    @Column(name = "saude")
    private Integer saude;

    @Column(name = "inflamabilidade")
    private Integer inflamabilidade;

    @Column(name = "reatividade")
    private Integer reatividade;

    @Column(name = "especifico")
    private String especifico;

    @ManyToOne()
    @JoinColumn(name = "fispq_id", referencedColumnName = "id")
    private Arquivos fispq;

    @Column(name = "obs")
    private String obs;

    @Column(name = "version")
    private Integer version;

    @Transient
    private Audit audit = new Audit();

    @Transient
    private Boolean MOD;

    @Transient
    private Boolean CodReagente_MOD;

    @Transient
    private Boolean Reagente_MOD;

    @Transient
    private Boolean Unidade_MOD;
    
    @Transient
    private Boolean Tipo_MOD;
    
    @Transient
    private Boolean CasNumber_MOD;
    
    @Transient
    private Boolean QtdEstoqueMin_MOD;
    
    @Transient
    private Boolean QtdEstoqueMax_MOD;
    
    @Transient
    private Boolean CompraUnica_MOD;
    
    @Transient
    private Boolean Enderecamento_MOD;
    
    @Transient
    private Boolean NumeroIdentificacao_MOD;
    
    @Transient
    private Boolean Armazenamento_MOD;
    
    @Transient
    private Boolean Grau_MOD;
    
    @Transient
    private Boolean Pureza_MOD;
    
    @Transient
    private Boolean Classe_MOD;
    
    @Transient
    private Boolean Controlado_MOD;
    
    @Transient
    private Boolean Saude_MOD;
    
    @Transient
    private Boolean Inflamabilidade_MOD;
    
    @Transient
    private Boolean Reatividade_MOD;
    
    @Transient
    private Boolean Especifico_MOD;
    
    @Transient
    private Boolean Fispq_MOD;
    
    @Transient
    private Boolean Obs_MOD;

    public Reagente() {
    }

    public Reagente(Long id) {
        this.id = id;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodReagente() {
        return codReagente;
    }

    public void setCodReagente(Integer codReagente) {
        this.codReagente = codReagente;
    }

    public String getReagente() {
        return reagente;
    }

    public void setReagente(String reagente) {
        this.reagente = reagente;
    }

    public UnidadeMedida getUnidade() {
        return unidade;
    }

    public void setUnidade(UnidadeMedida unidade) {
        this.unidade = unidade;
    }

    public String getCasNumber() {
        return casNumber;
    }

    public void setCasNumber(String casNumber) {
        this.casNumber = casNumber;
    }

    public Integer getQtdEstoqueMin() {
        return qtdEstoqueMin;
    }

    public void setQtdEstoqueMin(Integer qtdEstoqueMin) {
        this.qtdEstoqueMin = qtdEstoqueMin;
    }

    public Integer getQtdEstoqueMax() {
        return qtdEstoqueMax;
    }

    public void setQtdEstoqueMax(Integer qtdEstoqueMax) {
        this.qtdEstoqueMax = qtdEstoqueMax;
    }

    public Boolean getCompraUnica() {
        return compraUnica;
    }

    public void setCompraUnica(Boolean compraUnica) {
        this.compraUnica = compraUnica;
    }

    public String getEnderecamento() {
        return enderecamento;
    }

    public void setEnderecamento(String enderecamento) {
        this.enderecamento = enderecamento;
    }

    public String getNumeroIdentificacao() {
        return numeroIdentificacao;
    }

    public void setNumeroIdentificacao(String numeroIdentificacao) {
        this.numeroIdentificacao = numeroIdentificacao;
    }

    public String getArmazenamento() {
        return armazenamento;
    }

    public void setArmazenamento(String armazenamento) {
        this.armazenamento = armazenamento;
    }

    public String getGrau() {
        return grau;
    }

    public void setGrau(String grau) {
        this.grau = grau;
    }

    public String getPureza() {
        return pureza;
    }

    public void setPureza(String pureza) {
        this.pureza = pureza;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getControlado() {
        return controlado;
    }

    public void setControlado(String controlado) {
        this.controlado = controlado;
    }

    public Integer getSaude() {
        return saude;
    }

    public void setSaude(Integer saude) {
        this.saude = saude;
    }

    public Integer getInflamabilidade() {
        return inflamabilidade;
    }

    public void setInflamabilidade(Integer inflamabilidade) {
        this.inflamabilidade = inflamabilidade;
    }

    public Integer getReatividade() {
        return reatividade;
    }

    public void setReatividade(Integer reatividade) {
        this.reatividade = reatividade;
    }

    public String getEspecifico() {
        return especifico;
    }

    public void setEspecifico(String especifico) {
        this.especifico = especifico;
    }

    public Arquivos getFispq() {
        return fispq;
    }

    public void setFispq(Arquivos arquivo) {
        this.fispq = arquivo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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

    public Boolean getCodReagente_MOD() {
        return CodReagente_MOD;
    }

    public void setCodReagente_MOD(Boolean CodReagente_MOD) {
        this.CodReagente_MOD = CodReagente_MOD;
    }

    public Boolean getReagente_MOD() {
        return Reagente_MOD;
    }

    public void setReagente_MOD(Boolean Reagente_MOD) {
        this.Reagente_MOD = Reagente_MOD;
    }

    public Boolean getUnidade_MOD() {
        return Unidade_MOD;
    }

    public void setUnidade_MOD(Boolean Unidade_MOD) {
        this.Unidade_MOD = Unidade_MOD;
    }

    public Boolean getTipo_MOD() {
        return Tipo_MOD;
    }

    public void setTipo_MOD(Boolean Tipo_MOD) {
        this.Tipo_MOD = Tipo_MOD;
    }

    public Boolean getCasNumber_MOD() {
        return CasNumber_MOD;
    }

    public void setCasNumber_MOD(Boolean CasNumber_MOD) {
        this.CasNumber_MOD = CasNumber_MOD;
    }

    public Boolean getQtdEstoqueMin_MOD() {
        return QtdEstoqueMin_MOD;
    }

    public void setQtdEstoqueMin_MOD(Boolean QtdEstoqueMin_MOD) {
        this.QtdEstoqueMin_MOD = QtdEstoqueMin_MOD;
    }

    public Boolean getQtdEstoqueMax_MOD() {
        return QtdEstoqueMax_MOD;
    }

    public void setQtdEstoqueMax_MOD(Boolean QtdEstoqueMax_MOD) {
        this.QtdEstoqueMax_MOD = QtdEstoqueMax_MOD;
    }

    public Boolean getCompraUnica_MOD() {
        return CompraUnica_MOD;
    }

    public void setCompraUnica_MOD(Boolean CompraUnica_MOD) {
        this.CompraUnica_MOD = CompraUnica_MOD;
    }

    public Boolean getEnderecamento_MOD() {
        return Enderecamento_MOD;
    }

    public void setEnderecamento_MOD(Boolean Enderecamento_MOD) {
        this.Enderecamento_MOD = Enderecamento_MOD;
    }

    public Boolean getNumeroIdentificacao_MOD() {
        return NumeroIdentificacao_MOD;
    }

    public void setNumeroIdentificacao_MOD(Boolean NumeroIdentificacao_MOD) {
        this.NumeroIdentificacao_MOD = NumeroIdentificacao_MOD;
    }

    public Boolean getArmazenamento_MOD() {
        return Armazenamento_MOD;
    }

    public void setArmazenamento_MOD(Boolean Armazenamento_MOD) {
        this.Armazenamento_MOD = Armazenamento_MOD;
    }

    public Boolean getGrau_MOD() {
        return Grau_MOD;
    }

    public void setGrau_MOD(Boolean Grau_MOD) {
        this.Grau_MOD = Grau_MOD;
    }

    public Boolean getPureza_MOD() {
        return Pureza_MOD;
    }

    public void setPureza_MOD(Boolean Pureza_MOD) {
        this.Pureza_MOD = Pureza_MOD;
    }

    public Boolean getClasse_MOD() {
        return Classe_MOD;
    }

    public void setClasse_MOD(Boolean Classe_MOD) {
        this.Classe_MOD = Classe_MOD;
    }

    public Boolean getControlado_MOD() {
        return Controlado_MOD;
    }

    public void setControlado_MOD(Boolean Controlado_MOD) {
        this.Controlado_MOD = Controlado_MOD;
    }

    public Boolean getSaude_MOD() {
        return Saude_MOD;
    }

    public void setSaude_MOD(Boolean Saude_MOD) {
        this.Saude_MOD = Saude_MOD;
    }

    public Boolean getInflamabilidade_MOD() {
        return Inflamabilidade_MOD;
    }

    public void setInflamabilidade_MOD(Boolean Inflamabilidade_MOD) {
        this.Inflamabilidade_MOD = Inflamabilidade_MOD;
    }

    public Boolean getReatividade_MOD() {
        return Reatividade_MOD;
    }

    public void setReatividade_MOD(Boolean Reatividade_MOD) {
        this.Reatividade_MOD = Reatividade_MOD;
    }

    public Boolean getEspecifico_MOD() {
        return Especifico_MOD;
    }

    public void setEspecifico_MOD(Boolean Especifico_MOD) {
        this.Especifico_MOD = Especifico_MOD;
    }

    public Boolean getFispq_MOD() {
        return Fispq_MOD;
    }

    public void setFispq_MOD(Boolean Arquivo_MOD) {
        this.Fispq_MOD = Arquivo_MOD;
    }

    public Boolean getObs_MOD() {
        return Obs_MOD;
    }

    public void setObs_MOD(Boolean Obs_MOD) {
        this.Obs_MOD = Obs_MOD;
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
        final Reagente other = (Reagente) obj;
        return Objects.equals(this.id, other.id);
    }




}
