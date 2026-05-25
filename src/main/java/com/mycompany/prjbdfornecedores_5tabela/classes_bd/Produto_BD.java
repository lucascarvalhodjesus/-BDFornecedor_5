/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjbdfornecedores_5tabela.classes_bd;

import com.mycompany.prjbdfornecedores_5tabela.objetos.Produto;
import com.mycompany.prjbdfornecedores_5tabela.util.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Produto_BD
{
    Connection conn;

    public Produto_BD()
    {
        conn = new Conexao().conectar();
    }

    public Produto salvar(Produto p)
    {
        try
        {
            // CORRIGIDO: prepareStatment → prepareStatement
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO produto(nome, valor) values (?,?)",
                Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getValor());

            int verif = stmt.executeUpdate();
            if (verif > 0)
            {
                ResultSet rs_id = stmt.getGeneratedKeys();
                if (rs_id.next())
                {
                    p.setId_produto(rs_id.getInt(1));
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return p;
    }

    public List<Produto> getProdutos()
    {
        List<Produto> lstC = new ArrayList<>();
        ResultSet rs;
        try
        {
            PreparedStatement ppStmt = conn.prepareStatement("SELECT * FROM produto");
            rs = ppStmt.executeQuery();
            while (rs.next())
            {
                lstC.add(getProduto(rs));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return lstC;
    }

    public Produto getProduto(Produto p)
    {
        ResultSet rs;
        try
        {
            PreparedStatement ppStmt = conn.prepareStatement(
                "SELECT * FROM produto WHERE idproduto = ?");
            ppStmt.setInt(1, p.getId_produto());
            rs = ppStmt.executeQuery();
            rs.next();
            p.setNome(rs.getString("nome"));
            p.setValor(rs.getDouble("valor"));
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return p;
    }

    private Produto getProduto(ResultSet rs) throws SQLException
    {
        Produto p = new Produto();
        p.setId_produto(rs.getInt("idproduto"));
        p.setNome(rs.getString("nome"));
        p.setValor(rs.getDouble("valor"));
        return p;
    }
}