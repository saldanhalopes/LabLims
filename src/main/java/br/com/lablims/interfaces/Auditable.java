/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lablims.interfaces;

import br.com.lablims.audit.Audit;

/**
 *
 * @author rafael.lopes
 */
public interface Auditable {

    Audit getAudit();

    void setAudit(Audit audit);
}
