/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjbdfornecedores_5tabela.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ManipulaData
{
    // Formato esperado na entrada: dd/MM/yyyy
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Converte uma String no formato dd/MM/yyyy para java.sql.Date,
     * que é o tipo aceito pelo PreparedStatement.setDate().
     *
     * @param dataStr  data no formato dd/MM/yyyy
     * @return         java.sql.Date correspondente, ou null se inválida
     */
    public Date string2Date(String dataStr)
    {
        try
        {
            java.util.Date utilDate = sdf.parse(dataStr);
            return new Date(utilDate.getTime());
        }
        catch (ParseException ex)
        {
            System.out.println("Erro ao converter data: " + dataStr);
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Converte um java.sql.Date para String no formato dd/MM/yyyy.
     *
     * @param date  java.sql.Date a converter
     * @return      String formatada, ou "" se null
     */
    public String date2String(Date date)
    {
        if (date == null) return "";
        return sdf.format(date);
    }
}
