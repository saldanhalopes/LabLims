package br.com.lablims.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import br.com.lablims.audit.Audit;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Grupo;

/**
 *
 * @author rafael
 */
public class GrupoDAO extends GenenicoDAO<Grupo> {

    public List<Grupo> getListGrupo(String value) {
        EntityManager em = ConnectionFactory.em();
        try {
            Query myQuery;
            if (value == null) {
                myQuery = em.createQuery("SELECT g FROM Grupo g ORDER BY g.id DESC");
            } else {
                myQuery = em.createQuery("SELECT g FROM Grupo g WHERE  "
                        + "g.id LIKE CONCAT('%',:id, '%') OR "
                        + "g.grupoNome LIKE CONCAT('%',:grupo, '%') OR "
                        + "g.grupoTipo LIKE CONCAT('%',:tipo, '%') "
                        + "ORDER BY g.id ASC")
                        .setParameter("id", value)
                        .setParameter("grupo", value)
                        .setParameter("tipo", value);
            }
            return (ArrayList<Grupo>) myQuery.getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Grupo> readGruposAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Grupo> grupos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_grupo_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_grupo_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_grupo_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_grupo_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Grupo grupo = new Grupo();
                Audit audit = new Audit();
                grupo.setId(rs.getLong("id"));
                grupo.setGrupoNome(rs.getString("grupo_nome"));
                grupo.setGrupoTipo(rs.getString("grupo_tipo"));
                grupo.setNome_MOD(rs.getBoolean("grupoNome_MOD"));
                grupo.setTipo_MOD(rs.getBoolean("grupoTipo_MOD"));
                grupo.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                grupo.setAudit(audit);
                grupos.add(grupo);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return grupos;
    }

}
