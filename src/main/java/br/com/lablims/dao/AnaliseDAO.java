package br.com.lablims.dao;

import br.com.lablims.audit.Audit;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Analise;
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
public class AnaliseDAO extends GenenicoDAO<Analise> {
    
    public List<Analise> readAnaliseAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Analise> mtds = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_analise_auditoria.*, "
                    + "tba_audit_revinfo.computador, "
                    + "tba_audit_revinfo.ultima_modificacao, "
                    + "tba_audit_revinfo.user_computador, "
                    + "tba_audit_revinfo.modificado_por, "
                    + "tba_audit_revinfo.motivo "
                    + "FROM tba_analise_auditoria  "
                    + "INNER JOIN tba_audit_revinfo "
                    + "ON tba_analise_auditoria.REV = tba_audit_revinfo.id "
                    + "ORDER BY tba_analise_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Analise mtd = new Analise();
                Audit audit = new Audit();
                mtd.setId(rs.getInt("id"));
                mtd.setAnalise(rs.getString("analise"));
                mtd.setSiglaAnalise(rs.getString("sigla_analise"));
                mtd.setDescricaoAnalise(rs.getString("descricao_analise"));
                mtd.setAnalise_MOD(rs.getInt("analise_MOD"));
                mtd.setSigla_Analise_MOD(rs.getInt("siglaAnalise_MOD"));
                mtd.setDescricao_Analise_MOD(rs.getInt("descricaoAnalise_MOD"));
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
