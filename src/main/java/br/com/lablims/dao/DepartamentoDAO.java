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
package br.com.lablims.dao;

import br.com.lablims.audit.Audit;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Departamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class DepartamentoDAO extends GenenicoDAO<Departamento> {

    public List<Departamento> readDepartamentoAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Departamento> departs = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_departamento_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_departamento_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_departamento_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_departamento_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Departamento depart = new Departamento();
                Audit audit = new Audit();
                depart.setId(rs.getLong("id"));
                depart.setDepartamento(rs.getString("departamento"));
                depart.setSiglaDepartamento(rs.getString("sigla_departamento"));
                depart.setDescricaoDepartamento(rs.getString("descricao_departamento"));
                depart.setDepartamento_MOD(rs.getBoolean("departamento_MOD"));
                depart.setSigla_Departamento_MOD(rs.getBoolean("siglaDepartamento_MOD"));
                depart.setDescricao_Departamento_MOD(rs.getBoolean("descricaoDepartamento_MOD"));
                depart.setDepartamento_MOD(rs.getBoolean("departamento_MOD"));
                depart.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                depart.setAudit(audit);
                departs.add(depart);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return departs;
    }
}
