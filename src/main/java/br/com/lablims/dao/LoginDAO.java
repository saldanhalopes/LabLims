package br.com.lablims.dao;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Login;
import br.com.lablims.model.Usuario;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
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
public class LoginDAO extends GenenicoDAO<Login> {

    public void loginUsuario(Usuario user) throws TransactionRequiredException {
        EntityManager em = ConnectionFactory.em();
        Login login = new Login();
        String computador;
        try {
            em.getTransaction().begin();
            login.setUsuario(user);
            login.setLastLogin(new Date());
            try {
                computador = java.net.InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException ex) {
                computador = "not found :" + ex;
            }
            login.setComputador(computador);
            em.persist(login);
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        } finally {
            em.close();
        }
    }

    public void logoffUsuario(Usuario user) throws TransactionRequiredException {
        EntityManager em = ConnectionFactory.em();
        Login login = new Login();
        String computador;
        try {
            em.getTransaction().begin();
            login.setUsuario(user);
            login.setLastLogout(new Date());
            try {
                computador = java.net.InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException ex) {
                computador = "not found :" + ex;
            }
            login.setComputador(computador);
            em.persist(login);
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        } finally {
            em.close();
        }
    }

    public List<Object[]> findListRegistroEntrada(Integer maxResults) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<Login> root = cq.from(Login.class);
            Join<Login, Usuario> joinUser = root.join("usuario", JoinType.INNER);
            cq.multiselect(
                    joinUser.get("id"),
                    joinUser.get("usuario"),
                    joinUser.get("nome"),
                    root.get("computador"),
                    root.get("lastLogin"),
                    root.get("lastLogout"),
                    root.get("id")
            );
            cq.orderBy(cb.desc(root.get("id")));
            TypedQuery<Object[]> q = em.createQuery(cq);
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

}
