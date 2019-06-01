/*
 * Copyright (C) 2018 rafael
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.lablims.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Grupo;
import br.com.lablims.model.RegraAcesso;
import javax.persistence.Query;

/**
 *
 * @author rafael
 */
public class RegraAcessoDAO extends GenenicoDAO<RegraAcesso> {

    public List<RegraAcesso> getListAcesso(EntityManager em, Integer grupo_id) {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<RegraAcesso> criteria = cb.createQuery(RegraAcesso.class);
            Root<RegraAcesso> rootColuna = criteria.from(RegraAcesso.class);
            Join<RegraAcesso, Grupo> joinGrupo = rootColuna.join("grupo", JoinType.INNER);
            criteria.where(cb.equal(joinGrupo.get("id"), grupo_id));
            criteria.select(rootColuna);
            TypedQuery<RegraAcesso> myQuery = em.createQuery(criteria);
            return (ArrayList<RegraAcesso>) myQuery.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
//    private List<RegraAcesso> getListAcesso(EntityManager em, Integer grupo_id) {
//        if (!em.isOpen()) {
//            em = ConnectionFactory.em(true);
//        }
//        try {
//            return (ArrayList<RegraAcesso>) em.createQuery("SELECT g FROM RegraAcesso g "
//                    + "WHERE g.grupo.id LIKE :grupo_id")
//                    .setParameter("grupo_id", grupo_id)
//                    .getResultList();
//        } catch (Exception e) {
//            return null;
//        }
//    }
    
    public String stringAcesso(EntityManager em, Integer grupo_id) {
        StringBuilder sb = new StringBuilder();
        try {
            for (RegraAcesso regraAcesso : getListAcesso(em, grupo_id)) {
                sb.append(regraAcesso.getForm().getId());
                sb.append("-");
                sb.append(regraAcesso.getAcesso().getAcesso());
                sb.append(";");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro : " + ex);
        } finally {
            if (sb.toString().isEmpty()) {
                sb.append("1-Ler;");
            }
        }
        return sb.toString();
    }

    public List<RegraAcesso> getList(EntityManager em, Integer id) {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            Query myQuery = em.createQuery("SELECT r FROM RegraAcesso r WHERE r.grupo.id = :id")
                    .setParameter("id", id);
            return (ArrayList<RegraAcesso>) myQuery.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

}
