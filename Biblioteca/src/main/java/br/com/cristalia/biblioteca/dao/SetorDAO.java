/*
 * Copyright (C) 2018 rafael.lopes
 *
 * Este programa é um software livre: você pode redistribuí-lo e / ou modificar
 * sob os termos da GNU General Public License, conforme publicado pela
 * a Free Software Foundation, seja a versão 3 da Licença, quanto
 * qualquer versão posterior.
 *
 * Este programa é distribuído na esperança de que seja útil,
 * mas SEM QUALQUER GARANTIA; sem a garantia implícita de
 * COMERCIALIZAÇÃO OU APTIDÃO PARA UM PROPÓSITO PARTICULAR. Veja o
 * GNU General Public License para obter mais detalhes.
 *
 * Você deve ter recebido uma cópia da GNU General Public License
 *  juntamente com este programa. Caso contrário, veja <http://www.gnu.org/licenses/>.
 */
package br.com.cristalia.biblioteca.dao;

import br.com.cristalia.biblioteca.audit.Audit;
import br.com.cristalia.biblioteca.connection.ConnectionFactory;
import br.com.cristalia.biblioteca.model.Departamento;
import br.com.cristalia.biblioteca.model.Setor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class SetorDAO extends GenenicoDAO<Setor> {

    public List<Setor> readSetorLaboratorio() throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<Setor> root = cq.from(Setor.class);
            cq.where(cb.equal(root.get("tipo"), "Analítico"));
            List<Order> orderList = new ArrayList();
            orderList.add(cb.asc(root.get("departamento")));
            orderList.add(cb.asc(root.get("setor")));
            cq.orderBy(orderList);
            cq.select(root);
            Query q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public Setor findBySiglaSetor(String setor) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Setor.findBySiglaSetor", Setor.class)
                    .setParameter("siglaSetor", setor)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Setor> readSetorAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Setor> setores = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_setor_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_setor_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_setor_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_setor_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Setor setor = new Setor();
                Departamento depart = new Departamento();
                Audit audit = new Audit();
                setor.setId(rs.getLong("id"));
                setor.setSetor(rs.getString("setor"));
                setor.setSiglaSetor(rs.getString("sigla_setor"));
                setor.setDescricaoSetor(rs.getString("descricao_setor"));
                setor.setTipo(rs.getString("tipo"));
                depart.setId(rs.getLong("departamento_id"));
                setor.setDepartamento(depart);
                setor.setSetor_MOD(rs.getBoolean("setor_MOD"));
                setor.setSigla_Setor_MOD(rs.getBoolean("siglaSetor_MOD"));
                setor.setDescricao_Setor_MOD(rs.getBoolean("descricaoSetor_MOD"));
                setor.setTipo_MOD(rs.getBoolean("tipo_MOD"));
                setor.setDepartamento_MOD(rs.getBoolean("departamento_MOD"));
                setor.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                setor.setAudit(audit);
                setores.add(setor);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return setores;
    }

}
