package br.com.lablims.dao;

import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Form;
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
