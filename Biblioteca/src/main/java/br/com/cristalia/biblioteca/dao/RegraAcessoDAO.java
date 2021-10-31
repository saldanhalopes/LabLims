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
package br.com.cristalia.biblioteca.dao;

import br.com.cristalia.biblioteca.audit.Audit;
import br.com.cristalia.biblioteca.connection.ConnectionFactory;
import br.com.cristalia.biblioteca.model.Acesso;
import br.com.cristalia.biblioteca.model.Form;
import br.com.cristalia.biblioteca.model.Grupo;
import br.com.cristalia.biblioteca.model.RegraAcesso;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rafael
 */
public class RegraAcessoDAO extends GenenicoDAO<RegraAcesso> {

    public List<RegraAcesso> getListAcesso(Long grupo_id) {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<RegraAcesso> cq = cb.createQuery(RegraAcesso.class);
            Root<RegraAcesso> rootColuna = cq.from(RegraAcesso.class);
            Join<RegraAcesso, Grupo> joinGrupo = rootColuna.join("grupo", JoinType.INNER);
            cq.where(cb.equal(joinGrupo.get("id"), grupo_id));
            cq.select(rootColuna);
            TypedQuery<RegraAcesso> myQuery = em.createQuery(cq);
            return (ArrayList<RegraAcesso>) myQuery.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public String stringAcesso(Long grupo_id) {
        StringBuilder sb = new StringBuilder();
        try {
            for (RegraAcesso regraAcesso : getListAcesso(grupo_id)) {
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

    public List<RegraAcesso> getList(Integer id) {
        EntityManager em = ConnectionFactory.em();
        try {
            return (ArrayList<RegraAcesso>) em.createNamedQuery("RegraAcesso.findById", RegraAcesso.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public RegraAcesso getAcessoByGrupoForm(Long gupo_id, Long form_id) {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("RegraAcesso.findAcesso", RegraAcesso.class)
                    .setParameter("grupo_id", gupo_id)
                    .setParameter("form_id", form_id)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<RegraAcesso> readRegraAcessoAuditoria(Long grupo_id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<RegraAcesso> regraAcessos = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_regra_acesso_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_regra_acesso_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_regra_acesso_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "WHERE tba_regra_acesso_auditoria.grupo_id = " + grupo_id
                    + " ORDER BY tba_regra_acesso_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                RegraAcesso regraAcesso = new RegraAcesso();
                Audit audit = new Audit();
                Acesso acesso = new Acesso();
                Form form = new Form();
                Grupo grupo = new Grupo();
                regraAcesso.setId(rs.getLong("id"));
                acesso.setId(rs.getLong("acesso_id"));
                form.setId(rs.getLong("form_id"));
                grupo.setId(rs.getLong("grupo_id"));
                regraAcesso.setAcesso(acesso);
                regraAcesso.setForm(form);
                regraAcesso.setGrupo(grupo);
                regraAcesso.setAcesso_MOD(rs.getBoolean("acesso_MOD"));
                regraAcesso.setForm_MOD(rs.getBoolean("form_MOD"));
                regraAcesso.setGrupo_MOD(rs.getBoolean("grupo_MOD"));
                regraAcesso.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                regraAcesso.setAudit(audit);
                regraAcessos.add(regraAcesso);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return regraAcessos;
    }

}
