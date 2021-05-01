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
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_analise_tipo_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_analise_tipo_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_analise_tipo_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                AnaliseTipo mtd = new AnaliseTipo();
                Audit audit = new Audit();
                mtd.setId(rs.getLong("id"));
                mtd.setAnaliseTipo(rs.getString("analise_tipo"));
                mtd.setSiglaAnaliseTipo(rs.getString("sigla_analise_tipo"));
                mtd.setDescricaoAnaliseTipo(rs.getString("descricao_analise_tipo"));
                mtd.setAnaliseTipo_MOD(rs.getBoolean("analisetipo_MOD"));
                mtd.setSigla_AnaliseTipo_MOD(rs.getBoolean("siglaanalisetipo_MOD"));
                mtd.setDescricao_AnaliseTipo_MOD(rs.getBoolean("descricaoanalisetipo_MOD"));
                mtd.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
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
