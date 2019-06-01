package br.com.lablims.dao;

import br.com.lablims.audit.Audit;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.AnaliseTipo;
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
public class AnaliseTipoDAO extends GenenicoDAO<AnaliseTipo> {
    
    public List<AnaliseTipo> readAnaliseTipoAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<AnaliseTipo> mtds = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_analise_tipo_auditoria.*, "
                    + "tba_audit_revinfo.computador, "
                    + "tba_audit_revinfo.ultima_modificacao, "
                    + "tba_audit_revinfo.user_computador, "
                    + "tba_audit_revinfo.modificado_por, "
                    + "tba_audit_revinfo.motivo "
                    + "FROM tba_analise_tipo_auditoria  "
                    + "INNER JOIN tba_audit_revinfo "
                    + "ON tba_analise_tipo_auditoria.REV = tba_audit_revinfo.id "
                    + "ORDER BY tba_analise_tipo_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                AnaliseTipo mtd = new AnaliseTipo();
                Audit audit = new Audit();
                mtd.setId(rs.getInt("id"));
                mtd.setAnaliseTipo(rs.getString("analise_tipo"));
                mtd.setSiglaAnaliseTipo(rs.getString("sigla_analise_tipo"));
                mtd.setDescricaoAnaliseTipo(rs.getString("descricao_analise_tipo"));
                mtd.setAnaliseTipo_MOD(rs.getInt("analise_tipo_MOD"));
                mtd.setSigla_AnaliseTipo_MOD(rs.getInt("siglaAnaliseTipo_MOD"));
                mtd.setDescricao_AnaliseTipo_MOD(rs.getInt("descricaoAnaliseTipo_MOD"));
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
