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
package br.com.lablims.dao;

import br.com.lablims.audit.Audit;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Arquivos;
import br.com.lablims.model.Reagente;
import br.com.lablims.model.UnidadeMedida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael.lopes
 */
public class ReagenteDAO extends GenenicoDAO<Reagente> {

    public List<Reagente> readAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Reagente> reagentes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_reagente_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_reagente_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_reagente_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_reagente_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Reagente reagente = new Reagente();
                UnidadeMedida und = new UnidadeMedida();
                Arquivos fispq = new Arquivos();
                Audit audit = new Audit();
                reagente.setId(rs.getLong("id"));
                reagente.setCompraUnica(rs.getBoolean("compra_unica"));
                reagente.setCodReagente(rs.getInt("cod_reagente"));
                reagente.setReagente(rs.getString("reagente"));
                reagente.setControlado(rs.getString("controlado"));
                reagente.setCasNumber(rs.getString("cas_number"));
                reagente.setGrau(rs.getString("grau"));
                reagente.setClasse(rs.getString("classe"));
                reagente.setPureza(rs.getString("pureza"));
                fispq.setId(rs.getLong("fispq_id"));
                reagente.setFispq(fispq);
                reagente.setObs(rs.getString("obs"));
                reagente.setNumeroIdentificacao(rs.getString("numero_identificacao"));
                reagente.setEnderecamento(rs.getString("enderecamento"));
                reagente.setArmazenamento(rs.getString("armazenamento"));
                reagente.setQtdEstoqueMin(rs.getInt("qtd_estoque_min"));
                reagente.setQtdEstoqueMax(rs.getInt("qtd_estoque_max"));
                und.setId(rs.getLong("unidade_id"));
                reagente.setUnidade(und);
                reagente.setSaude(rs.getInt("saude"));
                reagente.setInflamabilidade(rs.getInt("inflamabilidade"));
                reagente.setReatividade(rs.getInt("reatividade"));
                reagente.setEspecifico(rs.getString("especifico"));
                reagente.setCompraUnica_MOD(rs.getBoolean("compraunica_MOD"));
                reagente.setCodReagente_MOD(rs.getBoolean("codreagente_MOD"));
                reagente.setReagente_MOD(rs.getBoolean("reagente_MOD"));
                reagente.setControlado_MOD(rs.getBoolean("controlado_MOD"));
                reagente.setCasNumber_MOD(rs.getBoolean("casnumber_MOD"));
                reagente.setGrau_MOD(rs.getBoolean("grau_MOD"));
                reagente.setClasse_MOD(rs.getBoolean("classe_MOD"));
                reagente.setPureza_MOD(rs.getBoolean("pureza_MOD"));
                reagente.setFispq_MOD(rs.getBoolean("fispq_MOD"));
                reagente.setObs_MOD(rs.getBoolean("obs_MOD"));
                reagente.setNumeroIdentificacao_MOD(rs.getBoolean("numeroidentificacao_MOD"));
                reagente.setEnderecamento_MOD(rs.getBoolean("enderecamento_MOD"));
                reagente.setArmazenamento_MOD(rs.getBoolean("armazenamento_MOD"));
                reagente.setQtdEstoqueMin_MOD(rs.getBoolean("qtdestoquemin_MOD"));
                reagente.setQtdEstoqueMax_MOD(rs.getBoolean("qtdestoquemax_MOD"));
                reagente.setUnidade_MOD(rs.getBoolean("unidade_MOD"));
                reagente.setSaude_MOD(rs.getBoolean("saude_MOD"));
                reagente.setInflamabilidade_MOD(rs.getBoolean("inflamabilidade_MOD"));
                reagente.setReatividade_MOD(rs.getBoolean("reatividade_MOD"));
                reagente.setEspecifico_MOD(rs.getBoolean("especifico_MOD"));
                reagente.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                reagente.setAudit(audit);
                reagentes.add(reagente);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return reagentes;
    }

}
