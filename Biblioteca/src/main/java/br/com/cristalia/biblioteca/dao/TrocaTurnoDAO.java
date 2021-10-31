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
import br.com.cristalia.biblioteca.model.TrocaTurno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class TrocaTurnoDAO extends GenenicoDAO<TrocaTurno> {
    
    public List<TrocaTurno> findByAgrupador(String agrupador, Integer limit) throws NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("TrocaTurno.findByAgrupador", TrocaTurno.class)
                    .setParameter("agrupador", agrupador)
                    .setMaxResults(limit)
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<TrocaTurno> readAuditoria(Integer max_results) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TrocaTurno> turnos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_troca_turno_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_troca_turno_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_troca_turno_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_troca_turno_auditoria.REV DESC "
                    + "limit ?");
            stmt.setInt(1, max_results);
            rs = stmt.executeQuery();
            while (rs.next()) {
                TrocaTurno turno = new TrocaTurno();
                Audit audit = new Audit();
                turno.setId(rs.getLong("id"));
                turno.setPassagem(rs.getString("passagem"));
                turno.setDataRegistro(rs.getDate("data_registro"));
                turno.setPassagem_MOD(rs.getBoolean("passagem_MOD"));
                turno.setDataRegistro_MOD(rs.getBoolean("dataRegistro_MOD"));
                turno.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                turno.setAudit(audit);
                turnos.add(turno);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return turnos;
    }
}
