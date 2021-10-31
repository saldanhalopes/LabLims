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
package br.com.cristalia.colunas.dao;

import br.com.cristalia.biblioteca.connection.ConnectionFactory;
import br.com.cristalia.biblioteca.dao.GenenicoDAO;
import br.com.cristalia.colunas.model.ColunaLog;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author rafael
 */
public class ColunaLogDAO extends GenenicoDAO<ColunaLog> {
    
    public List<ColunaLog> findByColuna(Long id) {
        EntityManager em = ConnectionFactory.em();
        try {
            Query myQuery = em.createQuery("SELECT colLog FROM ColunaLog colLog I"
                    + "NNER JOIN colLog.colunaUtil colUtil "
                    + "WHERE colUtil.id = :colUtil_id "
                    + "ORDER BY colLog.id ASC")
                    .setParameter("colUtil_id", id);
            return myQuery.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
}
