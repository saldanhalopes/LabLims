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
import br.com.lablims.model.Metodologia;
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
 * @author rafael.lopes
 */
public class MetodologiaDAO extends GenenicoDAO<Metodologia> {

    public Boolean getCodMetodo(EntityManager em, String mtd) {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            return em.createNamedQuery("Metodologia.findCodMetodo", Metodologia.class)
                    .setParameter("cod_metodo", mtd)
                    .getSingleResult().getCodMetodo() != null;
        } catch (Exception ex) {
            return false;
        }
    }

    public List<Metodologia> readMetodologiaAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Metodologia> mtds = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_metodologia_auditoria.*, "
                    + "tba_audit_revinfo.computador, "
                    + "tba_audit_revinfo.ultima_modificacao, "
                    + "tba_audit_revinfo.user_computador, "
                    + "tba_audit_revinfo.modificado_por, "
                    + "tba_audit_revinfo.motivo "
                    + "FROM tba_metodologia_auditoria  "
                    + "INNER JOIN tba_audit_revinfo "
                    + "ON tba_metodologia_auditoria.REV = tba_audit_revinfo.id "
                    + "ORDER BY tba_metodologia_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                Audit audit = new Audit();
                mtd.setId(rs.getInt("id"));
                mtd.setCodMetodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                mtd.setCategoria(rs.getString("categoria"));
                mtd.setLink(rs.getString("link"));
                mtd.setVersao(rs.getInt("versao"));
                mtd.setCodMetodo_MOD(rs.getInt("codMetodo_MOD"));
                mtd.setMetodo_MOD(rs.getInt("metodo_MOD"));
                mtd.setCategoria_MOD(rs.getInt("categoria_MOD"));
                mtd.setLink_MOD(rs.getInt("link_MOD"));
                mtd.setVersao_MOD(rs.getInt("versao_MOD"));
                mtd.setVersion(rs.getInt("version"));
                mtd.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getDate("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                mtd.setAudit(audit);
                mtds.add(mtd);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return mtds;
    }

}
