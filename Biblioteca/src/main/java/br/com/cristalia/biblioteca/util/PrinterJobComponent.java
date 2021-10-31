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
package br.com.cristalia.biblioteca.util;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 *
 * @author rafael.lopes
 */
public class PrinterJobComponent {
//    PrinterJob pj = PrinterJob.getPrinterJob();
//        pj.setJobName(" Print Component ");
//
//        pj.setPrintable(new Printable() {
//            @Override
//            public int print(Graphics pg, PageFormat pf, int pageNum) {
//                if (pageNum > 0) {
//                    return Printable.NO_SUCH_PAGE;
//                }
//
//                Graphics2D g2 = (Graphics2D) pg;
//                g2.translate(pf.getImageableX(), pf.getImageableY());
//                jPanel1.paint(g2);
//                return Printable.PAGE_EXISTS;
//            }
//        });
//        if (pj.printDialog() == false) {
//            return;
//        }
//
//        try {
//            pj.print();
//        } catch (PrinterException ex) {
//            // handle exception
//        }
}
