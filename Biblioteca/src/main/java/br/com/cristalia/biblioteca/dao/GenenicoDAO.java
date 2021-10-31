package br.com.cristalia.biblioteca.dao;

import br.com.cristalia.biblioteca.connection.ConnectionFactory;
import br.com.cristalia.biblioteca.interfaces.EntidadeBase;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;

/**
 *
 * @author rafael
 * @param <T>
 */
public class GenenicoDAO<T extends EntidadeBase> {

    public T salvar(T cls) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            em.getTransaction().begin();
            if (cls.getId() == null) {
                em.persist(cls);
            } else {
                cls = em.merge(cls);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new Exception(ex);
        } finally {
            em.close();
        }
        return cls;
    }

    public void remover(Class<T> cls, Long id) throws Exception {
        EntityManager em = ConnectionFactory.em();
        T t = em.find(cls, id);
        try {
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new Exception("Esse registro não pode ser removido!");
        } finally {
            em.close();
        }
    }

    public T findById(Class<T> cls, Long id) throws Exception {
        EntityManager em = ConnectionFactory.em();
        T t = null;
        try {
            t = em.find(cls, id);
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
        return t;
    }

    public List<T> findEntities(Class<T> cls) throws Exception {
        return findList(cls, true, -1, -1);
    }

    public List<T> findEntities(Class<T> cls, String orderBy, Boolean order) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<T> root = cq.from(cls);
            cq.select(root);
            if (order) {
                cq.orderBy(cb.asc(root.get(orderBy)));
            } else {
                cq.orderBy(cb.desc(root.get(orderBy)));
            }
            Query q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public List<T> findEntities(Class<T> cls, String orderBy, Boolean order, int maxResults, int firstResult) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<T> root = cq.from(cls);
            cq.select(root);
            if (order) {
                cq.orderBy(cb.asc(root.get(orderBy)));
            } else {
                cq.orderBy(cb.desc(root.get(orderBy)));
            }
            Query q = em.createQuery(cq);
            q.setMaxResults(maxResults);
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public List<T> findEntities(Class<T> cls, int maxResults) throws Exception {
        return findList(cls, maxResults);
    }

    private List<T> findList(Class<T> cls, int maxResults) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<T> root = cq.from(cls);
            cq.select(root);
            cq.orderBy(cb.asc(root.get("id")));
            Query q = em.createQuery(cq);
            q.setMaxResults(maxResults);
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public List<T> findEntities(Class<T> cls, int maxResults, int firstResult) throws Exception {
        return findList(cls, false, maxResults, firstResult);
    }

    private List<T> findList(Class<T> cls, boolean all, int maxResults, int firstResult) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<T> root = cq.from(cls);
            cq.select(root);
            cq.orderBy(cb.asc(root.get("id")));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public List<T> findListWhere(Class<T> cls, boolean all, Integer maxResults, Integer firstResult, Map<String, String> conditional) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<T> root = cq.from(cls);
            cq.select(root);
            for (String key : conditional.keySet()) {
                cq.where(cb.equal(root.get(key), conditional.get(key)));
            }
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public Integer getCount(Class<T> cls) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<T> rt = cq.from(cls);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }
}
