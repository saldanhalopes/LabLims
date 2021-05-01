/*
 * Copyright (C) 2019 rafael.lopes
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
package br.com.lablims.dao;

import br.com.lablims.audit.Audit;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Material;
import br.com.lablims.model.Metodologia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael.lopes
 */
public class MetodologiaDAO extends GenenicoDAO<Metodologia> {

    public Boolean checkCodMetodoIsExits(String mtd) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Metodologia.findCodMetodo", Metodologia.class)
                    .setParameter("cod_metodo", mtd)
                    .getSingleResult().getCodMetodo() != null;
        } catch (NoResultException ex) {
            return false;
        } finally {
            em.close();
        }
    }
    
    public Metodologia findMetodoByMaterial(Long material_id) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Metodologia.findMetodoByMaterial", Metodologia.class)
                    .setParameter("id", material_id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public Metodologia findMetodoWithMaterial(Long metodologia_id) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Metodologia.findMetodoWithMaterial", Metodologia.class)
                    .setParameter("id", metodologia_id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Metodologia> findMetodologias(List<Long> mtd_ids) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Metodologia.findMetodologias", Metodologia.class)
                    .setParameter("ids", mtd_ids)
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<Metodologia> readMetodologiaAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Metodologia> mtds = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_metodologia_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_metodologia_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_metodologia_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_metodologia_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                Audit audit = new Audit();
                mtd.setId(rs.getLong("id"));
                mtd.setCodMetodo(rs.getString("cod_metodo"));
                mtd.setMetodo(rs.getString("metodo"));
                mtd.setCategoria(rs.getString("categoria"));
                mtd.setObs(rs.getString("obs"));
                mtd.setCodMetodo_MOD(rs.getBoolean("codMetodo_MOD"));
                mtd.setMetodo_MOD(rs.getBoolean("metodo_MOD"));
                mtd.setCategoria_MOD(rs.getBoolean("categoria_MOD"));
                mtd.setObs_MOD(rs.getBoolean("obs_MOD"));
                mtd.setMaterial_MOD(rs.getBoolean("material_MOD"));
                mtd.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                mtd.setAudit(audit);
                mtds.add(mtd);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return mtds;
    }
    
    public List<Metodologia> readMetodologiaMaterialAuditoria(Long id) {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Metodologia> mtds = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tb_metodologia_material_AUD.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tb_metodologia_material_aud  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tb_metodologia_material_aud.REV = tbc_revinfo_auditoria.id "
                    + "WHERE metodologia_id = ? "
                    + "ORDER BY tb_metodologia_material_aud.REV DESC ");
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Metodologia mtd = new Metodologia();
                Material mat = new Material();
                Audit audit = new Audit();
                mtd.setId(rs.getLong("metodologia_id"));
                mat.setId(rs.getLong("material_id"));
                HashSet<Material> matHash = new HashSet<>();
                matHash.add(mat);
                mtd.setMaterial(matHash);
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                mtd.setAudit(audit);
                mtds.add(mtd);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return mtds;
    }

}
