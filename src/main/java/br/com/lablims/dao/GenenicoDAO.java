package br.com.lablims.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.interfaces.EntidadeBase;

/**
 *
 * @author rafael
 * @param <T>
 */
public class GenenicoDAO<T extends EntidadeBase> {

    public T salvar(EntityManager em, T cls) throws Exception {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            em.getTransaction().begin();
            if (cls.getId() == null) {
                em.persist(cls);
            } else {
                if (!em.contains(cls)) {
                    if (em.find(cls.getClass(), cls.getId()) == null) {
                        throw new Exception("Erro ao Atualizar!");
                    }
                }
                cls = em.merge(cls);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new Exception(ex);
        } 
        return cls;
    }

    public void remover(EntityManager em, Class<T> cls, Integer id) throws Exception {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        T t = em.find(cls, id);
        try {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception(ex);
        } 
    }

    public T findById(EntityManager em, Class<T> cls, Integer id) throws Exception {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        T t = null;
        try {
            t = em.find(cls, id);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return t;
    }

    public List<T> findEntities(EntityManager em, Class<T> cls) throws Exception {
        return findList(em, cls, true, -1, -1);
    }

    public List<T> findEntities(EntityManager em, Class<T> cls, int maxResults, int firstResult) throws Exception {
        return findList(em, cls, false, maxResults, firstResult);
    }

    private List<T> findList(EntityManager em, Class<T> cls, boolean all, int maxResults, int firstResult) throws Exception {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(cls));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public List<T> findListAtivo(EntityManager em, Class<T> cls, Boolean ativo) throws Exception {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = cb.createQuery(cls);
            Root<T> from = criteriaQuery.from(cls);
            criteriaQuery.where(cb.equal(from.get("ativo"), ativo));
            TypedQuery<T> query = em.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } 
    }

    public void desativar(EntityManager em, Class<T> cls, Integer id, Boolean ativo) throws Exception {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            em.getTransaction().begin();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaUpdate<T> update = cb.createCriteriaUpdate(cls);
            Root<T> from = update.from(cls);
            update.set("ativo", ativo);
            update.where(from.get("id").in(id));
            em.createQuery(update).executeUpdate();
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new Exception(ex);
        } 
    }

    public int getCount(EntityManager em, Class<T> cls) throws Exception {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<T> rt = cq.from(cls);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
