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
package br.com.lablims.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author rafael.lopes
 */
public class InputFileExcel {
//    try {
//
//            FileInputStream excelFile = new FileInputStream(new File("C:\\Users\\rafael.lopes\\Desktop\\tyeste.xlsx"));
//            Workbook workbook = new XSSFWorkbook(excelFile);
//            Sheet datatypeSheet = workbook.getSheetAt(0);
//            Iterator<Row> iterator = datatypeSheet.iterator();
//            Boolean ws = false;
//            loop:
//            while (iterator.hasNext()) {
//                Row currentRow = iterator.next();
//                Iterator<Cell> cellIterator = currentRow.iterator();
//                while (cellIterator.hasNext()) {
//                    Cell currentCell = cellIterator.next();
//                    if (currentCell.getCellTypeEnum() == CellType.STRING) {
//                        if (currentCell.getStringCellValue().contains("RESUMO DE MATERIAL EM QUARENTENA")) {
//                            ws = true;
//                        }
//                        if (currentCell.getStringCellValue().contains("LOTES BLOQUEADOS")) {
//                            break loop;
//                        }
//                    }
//                    if (currentCell.getCellTypeEnum() == CellType.STRING && ws) {
//                        System.out.print(currentCell.getColumnIndex() + "-" + currentCell.getStringCellValue() + "\t");
//                    } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC && ws) {
//                        System.out.print(currentCell.getColumnIndex() + "-" + currentCell.getNumericCellValue() + "\t");
//                    }
//                }
//                if (ws) {
//                    System.out.println();
//                }
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println(e.toString());
//        } catch (IOException e) {
//            System.out.println(e.toString());
//        }
}
