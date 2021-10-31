package br.com.cristalia.biblioteca.dao;

import br.com.cristalia.biblioteca.connection.ConnectionFactory;
import br.com.cristalia.biblioteca.model.Form;
import javax.persistence.EntityManager;

/**
 *
 * @author rafael
 */
public class FormDAO extends GenenicoDAO<Form> {

    public Form getFormByName(String form) {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Form.findByForm", Form.class)
                    .setParameter("formName", form)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

}
