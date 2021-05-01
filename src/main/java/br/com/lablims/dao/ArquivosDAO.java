package br.com.lablims.dao;

import br.com.lablims.audit.Audit;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Arquivos;
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
public class ArquivosDAO extends GenenicoDAO<Arquivos> {

    public List<Arquivos> readAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Arquivos> arquivos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_arquivos_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tb_arquivos_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tb_arquivos_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tb_arquivos_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Arquivos arquivo = new Arquivos();
                Audit audit = new Audit();
                arquivo.setId(rs.getLong("id"));
                arquivo.setNome(rs.getString("nome"));
                arquivo.setDescricao(rs.getString("descricao"));
                arquivo.setTipo(rs.getString("tipo"));
                arquivo.setTamanho(rs.getDouble("tamanho"));
                arquivo.setNome_MOD(rs.getBoolean("nome_MOD"));
                arquivo.setDescricao_MOD(rs.getBoolean("descricao_MOD"));
                arquivo.setTipo_MOD(rs.getBoolean("tipo_MOD"));
                arquivo.setTamanho_MOD(rs.getBoolean("tamanho_MOD"));
                arquivo.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                arquivo.setAudit(audit);
                arquivos.add(arquivo);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return arquivos;
    }

}
