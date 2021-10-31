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
package br.com.cristalia.biblioteca.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author rafael
 */
public class DataHora {

    private Timestamp timestampdatahora;

    public Timestamp getTimestampdatahora() {
        return timestampdatahora;
    }

    public void setTimestampdatahora(Timestamp timestampdatahora) {
        this.timestampdatahora = timestampdatahora;
    }

    public static String getStringDate(Date d) {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return time.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getStringDate(Timestamp d) {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return time.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getStringDateTimeSimple(Date d) {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            return time.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getStringDateTime(Date d) {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return time.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getStringDateTimeShort(Date d) {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            return time.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    public static String getStringDateTime(Timestamp d) {
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return time.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    public static String DateTimeNow() {
        Date d = new Date();
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return time.format(d);
    }

    public static String DateNow() {
        Date d = new Date();
        SimpleDateFormat time = new SimpleDateFormat("dd/MM/yyyy");
        return time.format(d);
    }

    public static String TimeNow() {
        Date d = new Date();
        SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
        return time.format(d);
    }

    public static Timestamp getTimestampDate(String data) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return new Timestamp(format.parse(data).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Timestamp getTimestampDate(Date data) {
        try {
            return new Timestamp(data.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getDatetoLocalDate(LocalDate data) {
        try {
            return Date.from(data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            return null;
        }
    }

    public static LocalDate getLocalDatetoDate(Date data) {
        try {
            return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (Exception e) {
            return null;
        }
    }

    public static Timestamp getTimestampDateTime(String data) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return new Timestamp(format.parse(data).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getDateTime(String data) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return new Date(format.parse(data).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getDateTimeDataBase(String data) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return new Date(format.parse(data).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Integer getYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.YEAR);
    }

    public static Integer getMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.MONTH + 1);
    }

    public static String DateFileNow() {
        Date d = new Date();
        SimpleDateFormat time = new SimpleDateFormat("(dd_MM_yyyy-HH_mm)");
        return time.format(d);
    }

    public static String DateSql() {
        Date d = new Date();
        SimpleDateFormat time = new SimpleDateFormat("(yyyy-MM-dd)");
        return time.format(d);
    }

    public static String DateAsCodigoNow() {
        Date d = new Date();
        SimpleDateFormat time = new SimpleDateFormat("ddMMyyyyHHmm");
        return time.format(d);
    }

    public static String getStringDateFinalAno(Date d) {
        SimpleDateFormat time = new SimpleDateFormat("yy");
        try {
            return time.format(d);
        } catch (Exception e) {
            return "";
        }
    }

    public static Date getDateTimeByTexto(String data) {
        String[] splited = data.split(" ");
        String texto = splited[0].replace(".", "/") + "/" + getYear() + " " + splited[1] + ":00";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return new Date(format.parse(texto).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getDateTimeBySimpleTexto(String data) {
        String texto = data + ":00";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            return new Date(format.parse(texto).getTime());
        } catch (ParseException e) {
            return null;
        }
    }

}
