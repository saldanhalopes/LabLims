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
package br.com.cristalia.biblioteca.dao;

import br.com.cristalia.biblioteca.audit.Audit;
import br.com.cristalia.biblioteca.connection.ConnectionFactory;
import br.com.cristalia.biblioteca.model.AnaliseStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class AnaliseStatusDAO extends GenenicoDAO<AnaliseStatus> {

    public AnaliseStatus findByStatus(String status) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("AnaliseStatus.findByStatus", AnaliseStatus.class)
                    .setParameter("status", status)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    
    public List<AnaliseStatus> readAnaliseStatusAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AnaliseStatus> analisesStatus = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_analise_status_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_analise_status_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_analise_status_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_analise_status_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                AnaliseStatus analiseStatus = new AnaliseStatus();
                Audit audit = new Audit();
                analiseStatus.setId(rs.getLong("id"));
                analiseStatus.setAnaliseStatus(rs.getString("analise_status"));
                analiseStatus.setSiglaAnaliseStatus(rs.getString("sigla_analise_status"));
                analiseStatus.setDescricaoAnaliseStatus(rs.getString("descricao_analise_status"));
                analiseStatus.setAnaliseStatus_MOD(rs.getBoolean("analiseStatus_MOD"));
                analiseStatus.setSiglaAnaliseStatus_MOD(rs.getBoolean("siglaAnaliseStatus_MOD"));
                analiseStatus.setDescricaoAnaliseStatus_MOD(rs.getBoolean("descricaoAnaliseStatus_MOD"));
                analiseStatus.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                analiseStatus.setAudit(audit);
                analisesStatus.add(analiseStatus);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return analisesStatus;
    }
    
}
