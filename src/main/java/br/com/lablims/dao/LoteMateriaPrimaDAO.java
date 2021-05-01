package br.com.lablims.dao;

import br.com.lablims.audit.Audit;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.LoteMateriaPrima;
import br.com.lablims.model.LoteMateriaPrimaInfo;
import br.com.lablims.model.Material;
import br.com.lablims.model.Metodologia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author rafael
 */
public class LoteMateriaPrimaDAO extends GenenicoDAO<LoteMateriaPrima> {

    public Boolean checkLoteMateriaPrimaIsExits(String lote) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("LoteMateriaPrima.findLote", LoteMateriaPrima.class)
                    .setParameter("lote", lote)
                    .getSingleResult().getLote() != null;
        } catch (NoResultException ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public LoteMateriaPrima findLoteMateriaPrimaByLote(String lote) throws NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("LoteMateriaPrima.findLote", LoteMateriaPrima.class)
                    .setParameter("lote", lote)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public LoteMateriaPrima findLoteMateriaPrimaPlanoAnalise(Long lote_id) throws NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("LoteMateriaPrima.findLoteMateriaPrimaPlanoAnalise", LoteMateriaPrima.class)
                    .setParameter("id", lote_id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<LoteMateriaPrima> findListLoteMateriaPrima(Integer maxResults,
            Map<String, String> conditional) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<LoteMateriaPrima> root = cq.from(LoteMateriaPrima.class);
            Join<LoteMateriaPrima, LoteMateriaPrimaInfo> joinInfo = root.join("loteInfo", JoinType.INNER);
            if (conditional != null) {
                for (String key : conditional.keySet()) {
                    switch (conditional.get(key)) {
                        case "Todos":
                            cq.where(cb.notEqual(joinInfo.get(key), "Liberado"));
                            break;
                        case "null":
                            cq.where(cb.and(
                                    cb.notEqual(joinInfo.get("status"), "Liberado"),
                                    cb.isNull(joinInfo.get(key))
                            ));
                            break;
                        case "notNull":
                            cq.where(cb.and(
                                    cb.notEqual(joinInfo.get("status"), "Liberado"),
                                    cb.isNotNull(joinInfo.get(key))
                            ));
                            break;
                        default:
                            cq.where(cb.equal(joinInfo.get(key), conditional.get(key)));
                            break;
                    }
                }
            }
            cq.orderBy(cb.desc(root.get("id")));
            cq.select(root);
            Query q = em.createQuery(cq);
            if (maxResults != null) {
                q.setMaxResults(maxResults);
            }
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public List<Object[]> findLoteStatus(Integer maxResults) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            String sql = "SELECT CAST (tb_lote_materia_prima.id AS INTEGER), tb_lote_materia_prima.lote, "
                    + "tb_lote_materia_prima_info.data_necessidade, tb_lote_materia_prima_info.prev_liberacao, "
                    + "tb_lote_materia_prima_info.obs_cq, tb_lote_materia_prima_info.data_status, "
                    + "tb_lote_materia_prima_info.status, tb_material.cod_material, tb_material.material, "
                    + "(Select COUNT(tb_lote_materia_prima_status.id) FROM tb_lote_materia_prima_status "
                    + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id) as count_analise, "
                    + "(Select COUNT(tb_lote_materia_prima_status.id) FROM tb_lote_materia_prima_status "
                    + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id "
                    + "AND tb_lote_materia_prima_status.status_id = 4) as count_analise_finalizada, "
                    + "CAST((Select tb_plano_analise.setor_id "
                    + "FROM tb_lote_materia_prima_status "
                    + "INNER JOIN tb_plano_analise "
                    + "ON tb_lote_materia_prima_status.plano_analise_id = tb_plano_analise.id "
                    + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id "
                    + "AND tb_plano_analise.setor_id = '11' "
                    + "GROUP BY tb_plano_analise.setor_id) AS INTEGER) as micro, "
                    + "(SELECT MAX(tb_lote_materia_prima_status.previsao_data_hora) "
                    + "FROM tb_lote_materia_prima_status "
                    + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id) as previsaoLab, "
                    + "(SELECT DISTINCT(MAX(tb_solucao_reagente.repouso)) "
                    + "FROM tb_solucao_reagente "
                    + "WHERE tb_solucao_reagente.metodologia_id "
                    + "IN (tb_lote_materia_prima.metodologia_id, "
                    + "tb_lote_materia_prima.metodologia2_id, "
                    + "tb_lote_materia_prima.metodologia3_id) "
                    + "GROUP BY tb_solucao_reagente.metodologia_id) as tempoRepouso, "
                    + "tb_lote_materia_prima_info.data_doc "
                    + "FROM tb_lote_materia_prima "
                    + "INNER JOIN tb_lote_materia_prima_info "
                    + "ON tb_lote_materia_prima.id = tb_lote_materia_prima_info.id "
                    + "LEFT JOIN tb_material "
                    + "ON tb_lote_materia_prima.material_id = tb_material.id "
                    + "WHERE tb_lote_materia_prima_info.status <> 'Liberado' "
                    + "ORDER BY tb_lote_materia_prima.id DESC "
                    + "limit :limit";
            Query query = em.createNativeQuery(sql);
            query.setParameter("limit", maxResults);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Object[]> findLoteStatus(Integer maxResults, Long pa_id) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            String sql = "SELECT CAST (tb_lote_materia_prima.id AS INTEGER), tb_lote_materia_prima.lote, "
                    + "tb_lote_materia_prima_info.data_necessidade, tb_lote_materia_prima_info.prev_liberacao, "
                    + "tb_lote_materia_prima_info.obs_cq, tb_lote_materia_prima_info.data_status, "
                    + "tb_lote_materia_prima_info.status, tb_material.cod_material, tb_material.material, "
                    + "(Select COUNT(tb_lote_materia_prima_status.id) FROM tb_lote_materia_prima_status "
                    + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id) as count_analise, "
                    + "(Select COUNT(tb_lote_materia_prima_status.id) FROM tb_lote_materia_prima_status "
                    + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id "
                    + "AND tb_lote_materia_prima_status.status_id = 4) as count_analise_finalizada, "
                    + "CAST((Select tb_plano_analise.setor_id "
                    + "FROM tb_lote_materia_prima_status "
                    + "INNER JOIN tb_plano_analise "
                    + "ON tb_lote_materia_prima_status.plano_analise_id = tb_plano_analise.id "
                    + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id "
                    + "AND tb_plano_analise.setor_id = '11' "
                    + "GROUP BY tb_plano_analise.setor_id) AS INTEGER) as micro, "
                    + "(SELECT MAX(tb_lote_materia_prima_status.previsao_data_hora) "
                    + "FROM tb_lote_materia_prima_status "
                    + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id) as previsaoLab, "
                    + "(SELECT DISTINCT(MAX(tb_solucao_reagente.repouso)) FROM tb_solucao_reagente "
                    + "WHERE tb_solucao_reagente.metodologia_id IN (tb_lote_materia_prima.metodologia_id, "
                    + "tb_lote_materia_prima.metodologia2_id, tb_lote_materia_prima.metodologia3_id) "
                    + "GROUP BY tb_solucao_reagente.metodologia_id) as tempoRepouso, "
                    + "tb_lote_materia_prima_info.data_doc, "
                    + "(Select tb_analise_status.analise_status FROM tb_lote_materia_prima_status "
                    + "INNER JOIN tb_analise_status ON tb_lote_materia_prima_status.status_id = tb_analise_status.id "
                    + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id "
                    + "AND tb_lote_materia_prima_status.plano_analise_id = :pa_id) as status_analise "
                    + "FROM tb_lote_materia_prima "
                    + "INNER JOIN tb_lote_materia_prima_info "
                    + "ON tb_lote_materia_prima.id = tb_lote_materia_prima_info.id "
                    + "LEFT JOIN tb_material "
                    + "ON tb_lote_materia_prima.material_id = tb_material.id "
                    + "WHERE tb_lote_materia_prima_info.status <> 'Liberado' "
                    + "AND tb_lote_materia_prima.id IN "
                    + "(SELECT tb_lote_materia_prima_status.lote_id "
                    + "FROM tb_lote_materia_prima_status "
                    + "WHERE tb_lote_materia_prima_status.plano_analise_id = :pa_id "
                    + "AND tb_lote_materia_prima_status.status_id <> 4) "
                    + "ORDER BY tb_lote_materia_prima.id DESC "
                    + "limit :limit";
            Query query = em.createNativeQuery(sql);
            query.setParameter("limit", maxResults);
            query.setParameter("pa_id", pa_id);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public Integer getCountLotes() throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LoteMateriaPrima> root = cq.from(LoteMateriaPrima.class);
            Join<LoteMateriaPrima, LoteMateriaPrimaInfo> joinInfo = root.join("loteInfo", JoinType.INNER);
            cq.where(cb.notEqual(joinInfo.get("status"), "Liberado"));
            cq.select(cb.count(root));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public Integer getCountLotesVermelhos() throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LoteMateriaPrima> root = cq.from(LoteMateriaPrima.class);
            Join<LoteMateriaPrima, LoteMateriaPrimaInfo> joinInfo = root.join("loteInfo", JoinType.INNER);
            cq.where(cb.and(
                    cb.notEqual(joinInfo.get("status"), "Liberado"),
                    cb.lessThanOrEqualTo(joinInfo.get("dataNecessidade"), new Date())
            ));
            cq.select(cb.count(root));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public Integer getCountLotesLiberados() throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            Date date = DateUtils.addDays(new Date(), -1);
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LoteMateriaPrima> root = cq.from(LoteMateriaPrima.class);
            Join<LoteMateriaPrima, LoteMateriaPrimaInfo> joinInfo = root.join("loteInfo", JoinType.INNER);
            cq.where(cb.and(
                    cb.equal(joinInfo.get("status"), "Liberado"),
                    cb.greaterThanOrEqualTo(joinInfo.get("dataStatus"), date)
            ));
            cq.select(cb.count(root));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public List<Object[]> getLotesLiberados(Integer dias) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            Date date = DateUtils.addDays(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH), -dias);
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LoteMateriaPrima> root = cq.from(LoteMateriaPrima.class);
            Join<LoteMateriaPrima, LoteMateriaPrimaInfo> joinInfo = root.join("loteInfo", JoinType.INNER);
            cq.where(cb.and(
                    cb.equal(joinInfo.get("status"), "Liberado"),
                    cb.between(joinInfo.get("dataStatus"), date, new Date())
            ));
            Expression<Timestamp> ts = joinInfo.get("dataStatus"); // both
            Expression<Integer> day = cb.function("day", Integer.class, ts);
            Expression<java.sql.Date> date_month = joinInfo.get("dataStatus"); // date only
            Expression<Integer> month = cb.function("month", Integer.class, date_month);
            cq.multiselect(
                    cb.count(root).alias("ct"),
                    day,
                    month
            );
            cq.groupBy(
                    day,
                    month
            );
            cq.orderBy(cb.asc(month), cb.asc(day));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public Integer getCountLotesAmostragem() throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LoteMateriaPrima> root = cq.from(LoteMateriaPrima.class);
            Join<LoteMateriaPrima, LoteMateriaPrimaInfo> joinInfo = root.join("loteInfo", JoinType.INNER);
            cq.where(cb.and(
                    cb.or(
                            cb.equal(joinInfo.get("status"), "Entrada de Mercadoria"),
                            cb.equal(joinInfo.get("status"), "Não Especificado")
                    ),
                    cb.notEqual(joinInfo.get("status"), "Liberado")
            ));
            cq.select(cb.count(root));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public Integer getCountLotesCq() throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LoteMateriaPrima> root = cq.from(LoteMateriaPrima.class);
            Join<LoteMateriaPrima, LoteMateriaPrimaInfo> joinInfo = root.join("loteInfo", JoinType.INNER);
            cq.where(cb.and(
                    cb.or(
                            cb.equal(joinInfo.get("status"), "Amostrado"),
                            cb.equal(joinInfo.get("status"), "Controle de Qualidade"),
                            cb.equal(joinInfo.get("status"), "Analisando")
                    ),
                    cb.notEqual(joinInfo.get("status"), "Liberado")
            ));
            cq.select(cb.count(root));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public List<LoteMateriaPrima> readAuditoria(Integer maxResults) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<LoteMateriaPrima> lotes = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_lote_materia_prima_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_lote_materia_prima_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_lote_materia_prima_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_lote_materia_prima_auditoria.REV DESC "
                    + "limit ?");
            stmt.setInt(1, maxResults);
            rs = stmt.executeQuery();
            while (rs.next()) {
                LoteMateriaPrima lote = new LoteMateriaPrima();
                Material mat = new Material();
                Metodologia mtd1 = new Metodologia();
                Metodologia mtd2 = new Metodologia();
                Metodologia mtd3 = new Metodologia();
                Audit audit = new Audit();
                lote.setId(rs.getLong("id"));
                lote.setLote(rs.getString("lote"));
                mat.setId(rs.getLong("material_id"));
                mtd1.setId(rs.getLong("metodologia_id"));
                mtd2.setId(rs.getLong("metodologia2_id"));
                mtd3.setId(rs.getLong("metodologia3_id"));
                lote.setMaterial(mat);
                lote.setMetodologia(mtd1);
                lote.setMetodologia2(mtd2);
                lote.setMetodologia3(mtd3);
                lote.setLote_MOD(rs.getBoolean("lote_MOD"));
                lote.setMaterial_MOD(rs.getBoolean("material_MOD"));
                lote.setMetodologia_MOD(rs.getBoolean("metodologia_MOD"));
                lote.setMetodologia2_MOD(rs.getBoolean("metodologia2_MOD"));
                lote.setMetodologia3_MOD(rs.getBoolean("metodologia3_MOD"));
                lote.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                lote.setAudit(audit);
                lotes.add(lote);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return lotes;
    }
}
