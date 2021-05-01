package br.com.lablims.dao;

import br.com.lablims.audit.Audit;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.LoteMateriaPrima;
import br.com.lablims.model.LoteMateriaPrimaInfo;
import br.com.lablims.model.UnidadeMedida;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author rafael
 */
public class LoteMateriaPrimaInfoDAO extends GenenicoDAO<LoteMateriaPrimaInfo> {

 public void salvarLoteMateriaPrimaInfo(LoteMateriaPrimaInfo loteInfo, LoteMateriaPrima lote) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            em.getTransaction().begin();
            lote = em.find(LoteMateriaPrima.class, lote.getId());
            loteInfo.setLote(lote);
            em.persist(loteInfo);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public void atualizarLoteMateriaPrimaInfo(LoteMateriaPrimaInfo loteInfo) throws TransactionRequiredException {
        EntityManager em = ConnectionFactory.em();
        try {
            em.getTransaction().begin();
            if (em.find(LoteMateriaPrimaInfo.class, loteInfo.getId()) == null) {
                throw new TransactionRequiredException("Erro ao Atualizar!");
            }
            String update = "UPDATE LoteMateriaPrimaInfo lm SET "
                    + "lm.obsCq = :obsCq, "
                    + "lm.prevLiberacao = :prevLiberacao, "
                    + "lm.qtdEstoque = :qtdEstoque, "
                    + "lm.status = :status, "
                    + "lm.tempoEstimadoLiberacao = :tempoEstimadoLiberacao, "
                    + "lm.version = :version "
                    + "WHERE u.id = :id";
            em.createQuery(update)
                    .setParameter("obsCq", loteInfo.getObsCq())
                    .setParameter("prevLiberacao", loteInfo.getPrevLiberacao())
                    .setParameter("qtdEstoque", loteInfo.getQtdEstoque())
                    .setParameter("status", loteInfo.getStatus())
                    .setParameter("id", loteInfo.getId())
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        } finally {
            em.close();
        }
    }

    public List<LoteMateriaPrimaInfo> readAuditoria(Long lote_id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<LoteMateriaPrimaInfo> lotesInfo = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_lote_materia_prima_info_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_lote_materia_prima_info_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_lote_materia_prima_info_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "WHERE tba_lote_materia_prima_info_auditoria.id = ? "
                    + "ORDER BY tba_lote_materia_prima_info_auditoria.REV DESC ");
            stmt.setLong(1, lote_id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                LoteMateriaPrimaInfo loteInfo = new LoteMateriaPrimaInfo();
                UnidadeMedida unid = new UnidadeMedida();
                Audit audit = new Audit();
                loteInfo.setId(rs.getLong("id"));
                loteInfo.setDataNecessidade(rs.getDate("data_necessidade"));
                loteInfo.setDataStatus(rs.getDate("data_status"));
                loteInfo.setDataVenciamento(rs.getDate("data_vencimento"));
                loteInfo.setObsCq(rs.getString("obs_cq"));
                loteInfo.setPrevLiberacao(rs.getDate("prev_liberacao"));
                loteInfo.setQtdEstoque(rs.getBigDecimal("qtd_estoque"));
                loteInfo.setQtdVolumes(rs.getInt("qtd_volumes"));
                loteInfo.setStatus(rs.getString("status"));
                unid.setUnidade(rs.getString("unidade"));
                loteInfo.setDataNecessidade_MOD(rs.getBoolean("datanecessidade_MOD"));
                loteInfo.setDataStatus_MOD(rs.getBoolean("datastatus_MOD"));
                loteInfo.setDataVenciamento_MOD(rs.getBoolean("datavenciamento_MOD"));
                loteInfo.setObsCq_MOD(rs.getBoolean("obscq_MOD"));
                loteInfo.setPrevLiberacao_MOD(rs.getBoolean("prevliberacao_MOD"));
                loteInfo.setQtdEstoque_MOD(rs.getBoolean("qtdestoque_MOD"));
                loteInfo.setQtdNecessaria_MOD(rs.getBoolean("qtdnecessaria_MOD"));
                loteInfo.setQtdVolumes_MOD(rs.getBoolean("qtdvolumes_MOD"));
                loteInfo.setStatus_MOD(rs.getBoolean("status_MOD"));
                loteInfo.setUnidade_MOD(rs.getBoolean("unidade_MOD"));
                loteInfo.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                loteInfo.setAudit(audit);
                lotesInfo.add(loteInfo);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return lotesInfo;
    }

    public List<Object[]> getLotesStatusEntrada(Integer dias) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            Date date = DateUtils.addDays(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH), -dias);
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<LoteMateriaPrimaInfo> root = cq.from(LoteMateriaPrimaInfo.class);
            cq.where(cb.between(root.get("dataEntrada"), date, new Date()));
            Expression<Timestamp> ts = root.get("dataEntrada"); // both
            Expression<Integer> day = cb.function("day", Integer.class, ts);
            Expression<java.sql.Date> date_month = root.get("dataEntrada"); // date only
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

    public Integer getCountStatusEntradaLotes(Date inicio, Date fim) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<LoteMateriaPrimaInfo> root = cq.from(LoteMateriaPrimaInfo.class);
            cq.select(cb.count(root));
            cq.where(cb.between(root.get("dataEntrada"), inicio, fim));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public Integer getCountStatusAmostragemLotes(Date inicio, Date fim) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<LoteMateriaPrimaInfo> root = cq.from(LoteMateriaPrimaInfo.class);
            cq.select(cb.count(root));
            cq.where(cb.between(root.get("dataAmostragem"), inicio, fim));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public Integer getCountStatusEmCqLotes(Date inicio, Date fim) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<LoteMateriaPrimaInfo> root = cq.from(LoteMateriaPrimaInfo.class);
            cq.select(cb.count(root));
            cq.where(cb.between(root.get("dataCq"), inicio, fim));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public Integer getCountStatusLiberadoLotes(Date inicio, Date fim) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<LoteMateriaPrimaInfo> root = cq.from(LoteMateriaPrimaInfo.class);
            cq.select(cb.count(root));
            cq.where(cb.between(root.get("dataLiberado"), inicio, fim));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public Integer getCountStatusDocumentalLotes(Date inicio, Date fim) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<LoteMateriaPrimaInfo> root = cq.from(LoteMateriaPrimaInfo.class);
            cq.select(cb.count(root));
            cq.where(cb.between(root.get("dataDoc"), inicio, fim));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }
}
