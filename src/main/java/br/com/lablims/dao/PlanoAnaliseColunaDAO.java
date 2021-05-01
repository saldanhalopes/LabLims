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
import br.com.lablims.model.Coluna;
import br.com.lablims.model.PlanoAnaliseColuna;
import br.com.lablims.model.UnidadeMedida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael.lopes
 */
public class PlanoAnaliseColunaDAO extends GenenicoDAO<PlanoAnaliseColuna> {

    public List<PlanoAnaliseColuna> readPlanoAnaliseColuna(Long pa_id) {
        EntityManager em = ConnectionFactory.em();
        try {
            Query myQuery = em.createQuery("SELECT pac FROM PlanoAnaliseColuna pac "
                    + "INNER JOIN pac.coluna col "
                    + "INNER JOIN pac.planoAnalise pa "
                    + "WHERE pa.id = :pa_id "
                    + "ORDER BY pac.id ASC")
                    .setParameter("pa_id", pa_id);
            return myQuery.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<PlanoAnaliseColuna> readAuditoria(Long planoAnalise_id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<PlanoAnaliseColuna> paCols = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_plano_analise_coluna_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_plano_analise_coluna_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_plano_analise_coluna_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "WHERE tba_plano_analise_coluna_auditoria.plano_analise_id = ? "
                    + "ORDER BY tba_plano_analise_coluna_auditoria.REV DESC ");
            stmt.setLong(1, planoAnalise_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                PlanoAnaliseColuna paCol = new PlanoAnaliseColuna();
                Coluna col = new Coluna();
                UnidadeMedida und = new UnidadeMedida();
                Audit audit = new Audit();
                paCol.setId(rs.getLong("id"));
                col.setId(rs.getLong("coluna_id"));
                paCol.setColuna(col);
                paCol.setQtdUtilizada(rs.getInt("qtd_utilizada"));
                und.setId(rs.getLong("unidade_id"));
                paCol.setUnidade(und);
                paCol.setObs(rs.getString("obs"));
                paCol.setColuna_MOD(rs.getBoolean("coluna_MOD"));
                paCol.setQtdUtilizada_MOD(rs.getBoolean("qtdUtilizada_MOD"));
                paCol.setUnidade_MOD(rs.getBoolean("unidade_MOD"));
                paCol.setObs_MOD(rs.getBoolean("obs_MOD"));
                paCol.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                paCol.setAudit(audit);
                paCols.add(paCol);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return paCols;
    }

}
