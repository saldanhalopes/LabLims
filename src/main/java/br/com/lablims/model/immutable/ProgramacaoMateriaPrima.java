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
package br.com.lablims.model.immutable;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

/**
 *
 * @author rafael.lopes
 */
@Entity
@Immutable
@Subselect(
        "SELECT CAST (tb_lote_materia_prima.id AS INTEGER) as mp_id, tb_lote_materia_prima.lote, "
        + "tb_lote_materia_prima_info.data_necessidade, tb_lote_materia_prima_info.prev_liberacao, "
        + "tb_lote_materia_prima_info.obs_cq, tb_lote_materia_prima_info.data_status, "
        + "tb_lote_materia_prima_info.status, tb_material.cod_material, tb_material.material, "
        + "(Select COUNT(tb_lote_materia_prima_status.id) FROM tb_lote_materia_prima_status "
        + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id) as count_analise, "
        + "(Select COUNT(tb_lote_materia_prima_status.id) FROM tb_lote_materia_prima_status "
        + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id "
        + "AND tb_lote_materia_prima_status.status_id = 4) as count_analise_finalizada, "
        + "CAST((Select tb_plano_analise.setor_id "
        + "FROM tb_lote_materia_prima_status "
        + "INNER JOIN tb_plano_analise "
        + "ON tb_lote_materia_prima_status.plano_analise_id = tb_plano_analise.id "
        + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id "
        + "AND tb_plano_analise.setor_id = '11' "
        + "GROUP BY tb_plano_analise.setor_id) AS INTEGER) as micro, "
        + "(SELECT MAX(tb_lote_materia_prima_status.previsao_data_hora) "
        + "FROM tb_lote_materia_prima_status "
        + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id) as previsaoLab, "
        + "(SELECT DISTINCT(MAX(tb_solucao_reagente.repouso)) FROM tb_solucao_reagente "
        + "WHERE tb_solucao_reagente.metodologia_id IN (tb_lote_materia_prima.metodologia_id, "
        + "tb_lote_materia_prima.metodologia2_id, tb_lote_materia_prima.metodologia3_id) "
        + "GROUP BY tb_solucao_reagente.metodologia_id) as tempoRepouso, "
        + "(Select tb_analise_status.analise_status FROM tb_lote_materia_prima_status "
        + "INNER JOIN tb_analise_status ON tb_lote_materia_prima_status.status_id = tb_analise_status.id "
        + "WHERE tb_lote_materia_prima_status.lote_id = tb_lote_materia_prima.id "
        + "AND tb_lote_materia_prima_status.plano_analise_id = :pa_id) as status_analise "
        + "FROM tb_lote_materia_prima "
        + "INNER JOIN tb_lote_materia_prima_info "
        + "ON tb_lote_materia_prima.id = tb_lote_materia_prima_info.id "
        + "LEFT JOIN tb_material "
        + "ON tb_lote_materia_prima.material_id = tb_material.id "
        + "WHERE tb_lote_materia_prima_info.status <> 'Liberado' "
        + "AND tb_lote_materia_prima.id IN "
        + "(SELECT tb_lote_materia_prima_status.lote_id "
        + "FROM tb_lote_materia_prima_status "
)
public class ProgramacaoMateriaPrima implements Serializable {

    @Id
    private Integer mp_id;

    private String lote;

    public Integer getMp_id() {
        return mp_id;
    }

    public void setMp_id(Integer mp_id) {
        this.mp_id = mp_id;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

}
