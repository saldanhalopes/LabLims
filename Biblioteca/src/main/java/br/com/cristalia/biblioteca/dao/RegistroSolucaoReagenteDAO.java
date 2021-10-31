package br.com.cristalia.biblioteca.dao;

import br.com.cristalia.biblioteca.connection.ConnectionFactory;
import br.com.cristalia.biblioteca.model.RegistroSolucaoReagente;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author rafael
 */
public class RegistroSolucaoReagenteDAO extends GenenicoDAO<RegistroSolucaoReagente> {
    
    
    public Integer getUltimaNumeroSolucao(String tipo_solucao) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<RegistroSolucaoReagente> root = cq.from(RegistroSolucaoReagente.class);
            cq.where(cb.equal(root.get("tipo"), tipo_solucao));
            cq.orderBy(cb.desc(root.get("id")));
            cq.select(root);
            TypedQuery<RegistroSolucaoReagente> q = em.createQuery(cq);
            q.setMaxResults(1);
            return q.getSingleResult().getNumero();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    
    


//    public List<SolucaoReagente> readAuditoria(Long id) {
//        Connection conn = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        List<SolucaoReagente> solReagetes = new ArrayList<>();
//        try {
//            stmt = conn.prepareStatement("SELECT tba_solucao_reagente_auditoria.*, "
//                    + "tbc_revinfo_auditoria.computador, "
//                    + "tbc_revinfo_auditoria.ultima_modificacao, "
//                    + "tbc_revinfo_auditoria.user_computador, "
//                    + "tbc_revinfo_auditoria.modificado_por, "
//                    + "tbc_revinfo_auditoria.motivo "
//                    + "FROM tba_solucao_reagente_auditoria  "
//                    + "INNER JOIN tbc_revinfo_auditoria "
//                    + "ON tba_solucao_reagente_auditoria.REV = tbc_revinfo_auditoria.id "
//                    + "WHERE metodologia_id = ? "
//                    + "ORDER BY tba_solucao_reagente_auditoria.REV DESC ");
//            stmt.setLong(1, id);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                SolucaoReagente solReagete = new SolucaoReagente();
//                Metodologia metodologia = new Metodologia();
//                Audit audit = new Audit();
//                solReagete.setId(rs.getLong("id"));
//                metodologia.setId(rs.getLong("metodologia_id"));
//                solReagete.setMetodologia(metodologia);
//                solReagete.setDescricao(rs.getString("descricao"));
//                solReagete.setRepouso(rs.getInt("repouso"));
//                solReagete.setObs(rs.getString("obs"));
//                solReagete.setDescricao_MOD(rs.getBoolean("descricao_MOD"));
//                solReagete.setRepouso_MOD(rs.getBoolean("repouso_MOD"));
//                solReagete.setMetodologia_MOD(rs.getBoolean("metodologia_MOD"));
//                solReagete.setObs_MOD(rs.getBoolean("obs_MOD"));
//                solReagete.setVersion(rs.getInt("version"));
//                audit.setMOD(rs.getInt("REVTYPE"));
//                audit.setComputador(rs.getString("computador"));
//                audit.setUserComputador(rs.getString("user_computador"));
//                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
//                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
//                audit.setMotivo(rs.getString("motivo"));
//                solReagete.setAudit(audit);
//                solReagetes.add(solReagete);
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Erro: " + ex);
//        } finally {
//            ConnectionFactory.closeConection(conn, stmt, rs);
//        }
//        return solReagetes;
//    }

}
