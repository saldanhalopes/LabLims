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

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * O <code>Audit</code> implementa um classe para os campos na Trilha de
 * Auditoria.
 *
 * @author rafae.lopes
 * @version 1.00
 */
public class Audit implements Serializable {

    private Timestamp ultimaModificacao;

    private String ultimaModificacaoPor;

    private String computador;

    private String userComputador;

    private String motivo;

    private Integer MOD;

    public Audit() {
    }

    public Timestamp getUltimaModificacao() {
        return ultimaModificacao;
    }

    public void setUltimaModificacao(Timestamp ultimaModificacao) {
        this.ultimaModificacao = ultimaModificacao;
    }

    public String getUltimaModificacaoPor() {
        return ultimaModificacaoPor;
    }

    public void setUltimaModificacaoPor(String ultimaModificacaoPor) {
        this.ultimaModificacaoPor = ultimaModificacaoPor;
    }

    public String getComputador() {
        return computador;
    }

    public void setComputador(String computador) {
        this.computador = computador;
    }

    public String getUserComputador() {
        return userComputador;
    }

    public void setUserComputador(String userComputador) {
        this.userComputador = userComputador;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Integer getMOD() {
        return MOD;
    }

    public void setMOD(Integer MOD) {
        this.MOD = MOD;
    }


    @Override
    public String toString() {
        return "Audit{" + "ultimaModificacao=" + ultimaModificacao + ", ultimaModificacaoPor=" + ultimaModificacaoPor + ", computador=" + computador + ", userComputador=" + userComputador + ", motivo=" + motivo + ", MOD=" + MOD + '}';
    }

    

}
