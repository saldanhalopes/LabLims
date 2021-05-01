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
import br.com.lablims.model.Coluna;
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
public class ColunaDAO extends GenenicoDAO<Coluna> {

    public Boolean checkIsExits(String codigo) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Coluna.checkIsExits", Coluna.class)
                    .setParameter("codigo", codigo)
                    .getSingleResult().getId()!= null;
        } catch (NoResultException ex) {
            return false;
        } finally {
            em.close();
        }
    }
    
    
    public List<Coluna> readAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Coluna> cols = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_coluna_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_coluna_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_coluna_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_coluna_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Coluna col = new Coluna();
                ColunaConfig tipoColuna = new ColunaConfig();
                ColunaConfig fabricanteColuna = new ColunaConfig();
                ColunaConfig marcaColuna = new ColunaConfig();
                ColunaConfig faseColuna = new ColunaConfig();
                ColunaConfig tamanhoColuna = new ColunaConfig();
                ColunaConfig diametroColuna = new ColunaConfig();
                ColunaConfig particulaColuna = new ColunaConfig();
                Audit audit = new Audit();
                col.setId(rs.getLong("id"));
                col.setCodigo(rs.getString("codigo"));
                col.setPartNumber(rs.getString("part_number"));
                col.setObs(rs.getString("obs"));
                tipoColuna.setId(rs.getLong("tipo_coluna"));
                col.setTipoColuna(tipoColuna);
                fabricanteColuna.setId(rs.getLong("fabricante_coluna"));
                col.setFabricanteColuna(fabricanteColuna);
                marcaColuna.setId(rs.getLong("marca_coluna"));
                col.setMarcaColuna(marcaColuna);
                faseColuna.setId(rs.getLong("fase_coluna"));
                col.setFaseColuna(faseColuna);
                tamanhoColuna.setId(rs.getLong("tamanho_coluna"));
                col.setTamanhoColuna(tamanhoColuna);
                diametroColuna.setId(rs.getLong("diametro_coluna"));
                col.setDiametroColuna(diametroColuna);
                particulaColuna.setId(rs.getLong("particula_coluna"));
                col.setParticulaColuna(particulaColuna);
                col.setCodigo_MOD(rs.getBoolean("codigo_MOD"));
                col.setTipoColuna_MOD(rs.getBoolean("tipoColuna_MOD"));
                col.setFabricanteColuna_MOD(rs.getBoolean("fabricanteColuna_MOD"));
                col.setMarcaColuna_MOD(rs.getBoolean("marcaColuna_MOD"));
                col.setFaseColuna_MOD(rs.getBoolean("faseColuna_MOD"));
                col.setTamanhoColuna_MOD(rs.getBoolean("tamanhoColuna_MOD"));
                col.setDiametroColuna_MOD(rs.getBoolean("diametroColuna_MOD"));
                col.setParticulaColuna_MOD(rs.getBoolean("particulaColuna_MOD"));
                col.setPartNumber_MOD(rs.getBoolean("partNumber_MOD"));
                col.setObs_MOD(rs.getBoolean("obs_MOD"));
                col.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                col.setAudit(audit);
                cols.add(col);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return cols;
    }

}
