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
package br.com.lablims.audit;

import br.com.lablims.util.Cript;
import br.com.lablims.util.DataHora;
import br.com.lablims.view.config.FrmSalvar;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.util.Date;
import javax.swing.JOptionPane;
import org.hibernate.envers.EntityTrackingRevisionListener;
import org.hibernate.envers.RevisionListener;
import org.hibernate.envers.RevisionType;

/**
 * O <code>AuditListener</code> extende a classe <code>RevisionListener</code> e
 * a classe <code>EntityTrackingRevisionListener</code>. Essa classe implementa
 * o metodo que carrega os campos persolanizados para a auditoria.
 *
 * @author rafae.lopes
 * @version 1.00
 * @see br.com.lablims.audit.AuditEntityType
 * @see br.com.lablims.audit.AuditRevision
 * @see org.hibernate.envers.DefaultRevisionEntity
 */
public class AuditListener implements RevisionListener, EntityTrackingRevisionListener {

    /**
     * Método que cria o retorno dos dados da Auditoria.
     *
     * @param revisionEntity Object - Recebe o objeto do tipo revisionEntity.
     */
    @Override
    public void newRevision(Object revisionEntity) {
        try {
            String computador;
            String user = System.getProperty("user") == null ? "not found" : System.getProperty("user");
            String userComputador = System.getProperty("user.name");
            try {
                computador = java.net.InetAddress.getLocalHost().getHostName();
            } catch (UnknownHostException ex1) {
                computador = "not found";
            }
            //Cria objeto AuditRevision que seta os valores do tipo revisionEntity
            AuditRevision auditRevision = (AuditRevision) revisionEntity;
            auditRevision.setUltimaModificacaoPor(user);
            auditRevision.setUltimaModificacao(new Date());
            auditRevision.setComputador(computador);
            auditRevision.setUserComputador(userComputador);
            auditRevision.setMotivo(FrmSalvar.motivo == null ? "Alterado: "
                    + DataHora.getStringDateTime(new Date())
                    : FrmSalvar.motivo);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar newRevision: " + ex);
        }
    }

    /**
     * Método que cria o retorno dos dados do AuditEntityType.
     *
     * @param entityClass Class - Recebe a classe do objeto.
     * @param entityName String - Recebe o nome da Entidade.
     * @param entityId Serializable - Recebe o Id da Entidade.
     * @param revisionType RevisionType - Recebe o objeto do tipo revisionType.
     * @param revisionEntity Object - Recebe o objeto do tipo revisionEntity.
     */
    @Override
    public void entityChanged(Class entityClass, String entityName,
            Serializable entityId, RevisionType revisionType,
            Object revisionEntity) {
        try {
            String entityClassName = entityClass.getName();
            Integer modifiedType = revisionType.ordinal();
            Integer idEntity = Integer.parseInt(entityId.toString());
            ((AuditRevision) revisionEntity).addModifiedEntityType(entityClassName, modifiedType, idEntity);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar entityChanged: " + ex);
        }
    }

}
