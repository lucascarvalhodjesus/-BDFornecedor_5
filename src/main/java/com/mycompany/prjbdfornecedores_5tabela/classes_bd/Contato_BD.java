/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjbdfornecedores_5tabela.classes_bd;

import com.mycompany.prjbdfornecedores_5tabela.objetos.Contato;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Fornecedor;
import com.mycompany.prjbdfornecedores_5tabela.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Contato_BD
{
    Connection conn;

    public Contato_BD()
    {
        conn = new Conexao().conectar();
    }

    public Contato salvar(Contato c)
    {
        try
        {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO contato(tipo_contato, contato, idfornecedor) values(?,?,?)",
                Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, c.getTipo_contato());
            stmt.setString(2, c.getContato());
            stmt.setInt(3, c.getFornecedor().getId_fornecedor());

            int verif = stmt.executeUpdate();
            if (verif > 0)
            {
                ResultSet rs_id = stmt.getGeneratedKeys();
                if (rs_id.next())
                {
                    c.setId_contato(rs_id.getInt(1));
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return c;
    }

    public List<Contato> getContatos()
    {
        List<Contato> lstC = new ArrayList<>();
        ResultSet rs;
        try
        {
            // CORRIGIDO: "contatos" → "contato"
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM contato");
            rs = ppStmt.executeQuery();
            while (rs.next())
            {
                lstC.add(getContato(rs, null));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return lstC;
    }

    public Contato getContato(ResultSet rs, Fornecedor f) throws SQLException
    {
        Contato c = new Contato();
        c.setId_contato(rs.getInt("idcontato"));
        c.setTipo_contato(rs.getString("tipo_contato"));
        c.setContato(rs.getString("contato"));
        if (f != null)
        {
            f.setId_fornecedor(rs.getInt("idfornecedor"));
            c.setFornecedor(f);
        }
        return c;
    }
}