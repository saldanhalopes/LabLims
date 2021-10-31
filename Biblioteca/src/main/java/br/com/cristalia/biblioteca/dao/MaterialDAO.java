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
package br.com.cristalia.biblioteca.dao;

import br.com.cristalia.biblioteca.audit.Audit;
import br.com.cristalia.biblioteca.connection.ConnectionFactory;
import br.com.cristalia.biblioteca.model.Material;
import br.com.cristalia.biblioteca.model.TipoMaterial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael.lopes
 */
public class MaterialDAO extends GenenicoDAO<Material> {

    public List<Material> findMaterialByControle(Boolean controlado) throws NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Material.findMaterialByControlado", Material.class)
                    .setParameter("controleEspecial", controlado)
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Material findMaterialByCodMaterial(Integer material_id) throws NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Material.findCodMaterial", Material.class)
                    .setParameter("codMaterial", material_id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Material findMaterialMetodologiaById(Long material_id) throws NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Material.findMaterialMetodologiaById", Material.class)
                    .setParameter("id", material_id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Material> findMaterialByMetodologia(Long metodologia_id) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Material.findMaterialByMetodologia", Material.class)
                    .setParameter("id", metodologia_id)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Boolean checkCodMaterialIsExits(Integer codMaterial) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Material.findCodMaterial", Material.class)
                    .setParameter("codMaterial", codMaterial)
                    .getSingleResult().getCodMaterial() != null;
        } catch (NoResultException ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public List<Material> readMaterialAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Material> materiais = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_material_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_material_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_material_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_material_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Material material = new Material();
                Audit audit = new Audit();
                TipoMaterial tipoMaterial = new TipoMaterial();
                material.setId(rs.getLong("id"));
                material.setCodMaterial(rs.getInt("cod_material"));
                material.setMaterial(rs.getString("material"));
                tipoMaterial.setTipo(rs.getString("tipo_maaterial"));
                material.setTipoMaterial(tipoMaterial);
                material.setControleEspecial(rs.getBoolean("controle_especial"));
                material.setFiscalizado(rs.getString("fiscalizado"));
                material.setCodMaterial_MOD(rs.getBoolean("codMaterial_MOD"));
                material.setMaterial_MOD(rs.getBoolean("material_MOD"));
                material.setTipoMaterial_MOD(rs.getBoolean("tipoMaterial_MOD"));
                material.setControleEspecial_MOD(rs.getBoolean("controleEspecial_MOD"));
                material.setFiscalizado_MOD(rs.getBoolean("fiscalizado_MOD"));
                material.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                material.setAudit(audit);
                materiais.add(material);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return materiais;
    }

}
