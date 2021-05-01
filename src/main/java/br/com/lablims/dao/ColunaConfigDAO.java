/*
 * Copyright (C) 2018 rafael
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.lablims.dao;

import br.com.lablims.audit.Audit;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.ColunaConfig;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class ColunaConfigDAO extends GenenicoDAO<ColunaConfig> {
    
    
    public Boolean checkIsExits(String tipo, String valor) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("ColunaConfig.checkIsExits", ColunaConfig.class)
                    .setParameter("tipo", tipo)
                    .setParameter("valor", valor)
                    .getSingleResult().getId()!= null;
        } catch (NoResultException ex) {
            return false;
        } finally {
            em.close();
        }
    }
    
    public List<ColunaConfig> readColunaConfig(String tipo) {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("ColunaConfig.findColunaConfigByTipo", ColunaConfig.class)
                    .setParameter("tipo", tipo)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<ColunaConfig> readAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ColunaConfig> configs = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_coluna_config_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_coluna_config_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_coluna_config_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_coluna_config_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ColunaConfig config = new ColunaConfig();
                Audit audit = new Audit();
                config.setId(rs.getLong("id"));
                config.setTipo(rs.getString("tipo"));
                config.setConfiguracao(rs.getString("configuracao"));
                config.setDescricao(rs.getString("descricao"));
                config.setConfiguracao_MOD(rs.getBoolean("configuracao_MOD"));
                config.setTipo_MOD(rs.getBoolean("tipo_MOD"));
                config.setDescricao_MOD(rs.getBoolean("descricao_MOD"));
                config.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                config.setAudit(audit);
                configs.add(config);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return configs;
    }
    
}
