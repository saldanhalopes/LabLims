package br.com.lablims.dao;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Login;
import br.com.lablims.model.Usuario;

/**
 *
 * @author rafael
 */
public class LoginDAO extends GenenicoDAO<Login> {

//    public List<Usuario> getListGrupo(String value) {
//        EntityManager em = ConnectionFactory.em(true);
//        try {
//            Query myQuery = em.createQuery("SELECT u.lastlogin FROM Usuario u WHERE   "
//                    + "g.id LIKE CONCAT('%',:id, '%') OR "
//                    + "g.grupoNome LIKE CONCAT('%',:grupo, '%') OR "
//                    + "g.grupoTipo LIKE CONCAT('%',:tipo, '%') "
//                    + "ORDER BY g.id ASC")
//                    .setParameter("id", value)
//                    .setParameter("grupo", value)
//                    .setParameter("tipo", value);
//
//            return (ArrayList<Usuario>) myQuery.getResultList();
//        } finally {
//            em.close();
//        }
//    }
//        public void loginUsuario(Usuario user) throws TransactionRequiredException {
//        EntityManager em = ConnectionFactory.em(true);
//        try {
//            em.getTransaction().begin();
//            if (em.find(Usuario.class, user.getId()) == null) {
//                throw new TransactionRequiredException("Erro ao Atualizar!");
//            }
//            String update = "UPDATE Login log SET "
//                    + "log.lastlogin = :lastlogin "
//                    + "WHERE log.usuario.id = :id";
//            em.createQuery(update)
//                    .setParameter("lastlogin", new Date())
//                    .setParameter("id", user.getId())
//                    .executeUpdate();
//            em.getTransaction().commit();
//        } catch (TransactionRequiredException ex) {
//            em.getTransaction().rollback();
//            throw new TransactionRequiredException("Erro ao Atualizar!");
//        } finally {
//            em.close();
//        }
//    }
    public void loginUsuario(EntityManager em, Usuario user) throws TransactionRequiredException {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        Login login = new Login();
        try {
            em.getTransaction().begin();
            login.setUsuario(user);
            login.setLastlogin(new Date());
            em.persist(login);
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        }
    }

    public void logoffUsuario(EntityManager em, Usuario user) throws TransactionRequiredException {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        Login login = new Login();
        try {
            em.getTransaction().begin();
            login.setUsuario(user);
            login.setLastlogout(new Date());
            em.persist(login);
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        }
    }

}
