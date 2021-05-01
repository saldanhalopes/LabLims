package br.com.lablims.dao;

import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Analise;
import br.com.lablims.model.AnaliseTipo;
import br.com.lablims.model.LoteMateriaPrima;
import br.com.lablims.model.LoteMateriaPrimaInfo;
import br.com.lablims.model.LoteMateriaPrimaStatus;
import br.com.lablims.model.Material;
import br.com.lablims.model.Metodologia;
import br.com.lablims.model.PlanoAnalise;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

/**
 *
 * @author rafael
 */
public class LoteMateriaPrimaStatusDAO extends GenenicoDAO<LoteMateriaPrimaStatus> {

    public LoteMateriaPrimaStatus findStatusByLoteAndPlanoAnalise(Long lote_id, Long pa_id) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("LoteMateriaPrimaStatus.findStatusByLoteAndPlanoAnalise", LoteMateriaPrimaStatus.class)
                    .setParameter("lote_id", lote_id)
                    .setParameter("pa_id", pa_id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<LoteMateriaPrimaStatus> findStatusByPlanoAnalise(Long pa_id) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("LoteMateriaPrimaStatus.findStatusByPlanoAnalise", LoteMateriaPrimaStatus.class)
                    .setParameter("pa_id", pa_id)
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<LoteMateriaPrimaStatus> findLotesByStatus(Long lote_id) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("LoteMateriaPrimaStatus.findStatus", LoteMateriaPrimaStatus.class)
                    .setParameter("lote_id", lote_id)
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<LoteMateriaPrimaStatus> findLotesByStatusAndAnalise(Long lote_id, Long analise_id) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("LoteMateriaPrimaStatus.findStatusByLoteAndAnalise", LoteMateriaPrimaStatus.class)
                    .setParameter("lote_id", lote_id)
                    .setParameter("analise_id", analise_id)
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Object[]> findLoteStatusAgrupado(Long status, Long lab) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<LoteMateriaPrimaStatus> root = cq.from(LoteMateriaPrimaStatus.class);
            Join<LoteMateriaPrimaStatus, LoteMateriaPrima> joinLote = root.join("lote", JoinType.INNER);
            Join<LoteMateriaPrima, LoteMateriaPrimaInfo> joinLoteInfo = joinLote.join("loteInfo", JoinType.INNER);
            Join<LoteMateriaPrimaStatus, PlanoAnalise> joinPA = root.join("planoAnalise", JoinType.INNER);
            Join<PlanoAnalise, Metodologia> joinMtd = joinPA.join("metodologia", JoinType.INNER);
            Join<PlanoAnalise, Analise> joinAnls = joinPA.join("analise", JoinType.INNER);
            Join<PlanoAnalise, AnaliseTipo> joinAnlsTipo = joinPA.join("analiseTipo", JoinType.INNER);
            cq.multiselect(cb.count(root.get("lote")),
                    joinMtd.get("codMetodo"),
                    joinMtd.get("metodo"),
                    joinAnls.get("analise"),
                    joinAnlsTipo.get("analiseTipo"),
                    cb.min(joinLoteInfo.get("dataNecessidade")),
                    root.get("planoAnalise").get("id"),
                    cb.max(root.get("previsaoDataHora")),
                    cb.min(joinLoteInfo.get("prevLiberacao")),
                    joinPA.get("descricao")
            );
            cq.where(
                    cb.and(
                            cb.notEqual(root.get("analiseStatus"), status),
                            cb.equal(joinPA.get("setor"), lab),
                            cb.notEqual(joinLoteInfo.get("status"), "Liberado")
                    )
            );
            cq.groupBy(joinMtd.get("codMetodo"),
                    joinMtd.get("metodo"),
                    joinAnls.get("analise"),
                    joinAnlsTipo.get("analiseTipo"),
                    root.get("planoAnalise"),
                    joinPA.get("descricao")
            );
            TypedQuery<Object[]> q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public List<Object[]> findLoteStatusByAnalise(Long status, Long lab) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<LoteMateriaPrimaStatus> root = cq.from(LoteMateriaPrimaStatus.class);
            Join<LoteMateriaPrimaStatus, LoteMateriaPrima> joinLote = root.join("lote", JoinType.INNER);
            Join<LoteMateriaPrima, LoteMateriaPrimaInfo> joinLoteInfo = joinLote.join("loteInfo", JoinType.INNER);
            Join<LoteMateriaPrimaStatus, PlanoAnalise> joinPA = root.join("planoAnalise", JoinType.INNER);
            Join<PlanoAnalise, Analise> joinAnls = joinPA.join("analise", JoinType.INNER);
            Join<PlanoAnalise, AnaliseTipo> joinAnlsTipo = joinPA.join("analiseTipo", JoinType.INNER);
            Join<LoteMateriaPrima, Material> joinMat = joinLote.join("material", JoinType.INNER);
            Join<LoteMateriaPrimaStatus, LoteMateriaPrima> joinAnlsStatus = root.join("analiseStatus", JoinType.INNER);
            cq.multiselect(joinLote.get("lote"),
                    joinMat.get("codMaterial"),
                    joinMat.get("material"),
                    joinAnls.get("analise"),
                    joinAnlsTipo.get("analiseTipo"),
                    joinLoteInfo.get("dataNecessidade"),
                    joinLote.get("id"),
                    root.get("previsaoDataHora"),
                    joinPA.get("id"),
                    joinLoteInfo.get("prevLiberacao"),
                    joinAnlsStatus.get("analiseStatus"),
                    root.get("celula"),
                    root.get("id"),
                    root.get("statusDataHora"),
                    joinLoteInfo.get("status"),
                    joinLoteInfo.get("dataStatus"),
                    joinPA.get("descricao")
            );
            cq.where(
                    cb.and(
                            cb.notEqual(root.get("analiseStatus"), status),
                            cb.equal(joinPA.get("setor"), lab),
                            cb.notEqual(joinLoteInfo.get("status"), "Liberado")
                    )
            );
            TypedQuery<Object[]> q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public Integer getCountLotesEmAnalise() throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LoteMateriaPrimaStatus> root = cq.from(LoteMateriaPrimaStatus.class);
            cq.where(cb.equal(root.get("analiseStatus").get("id"), 2));
            cq.select(cb.countDistinct(root.get("lote")));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

}
