package br.com.lablims.dao;

import br.com.lablims.audit.Audit;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Seguranca;
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
public class SegurancaDAO extends GenenicoDAO<Seguranca> {
    
    public Seguranca findByTipo(String tipo) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Seguranca.findByTipo", Seguranca.class)
                    .setParameter("tipo", tipo)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<Seguranca> readSegurancaAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Seguranca> segs = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_seguranca_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_seguranca_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_seguranca_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_seguranca_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Seguranca seg = new Seguranca();
                Audit audit = new Audit();
                seg.setId(rs.getLong("id"));
                seg.setTipo(rs.getString("tipo"));
                seg.setNumero(rs.getInt("numero"));
                seg.setTipo_MOD(rs.getBoolean("tipo_MOD"));
                seg.setNumero_MOD(rs.getBoolean("numero_MOD"));
                seg.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                seg.setAudit(audit);
                segs.add(seg);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return segs;
    }
    
}
