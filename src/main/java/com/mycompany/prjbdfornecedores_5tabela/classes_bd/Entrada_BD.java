/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prjbdfornecedores_5tabela.classes_bd;

import com.mycompany.prjbdfornecedores_5tabela.objetos.Entrada;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Entrada_Produto;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Fornecedor;
import com.mycompany.prjbdfornecedores_5tabela.objetos.Produto;
import com.mycompany.prjbdfornecedores_5tabela.util.Conexao;
import com.mycompany.prjbdfornecedores_5tabela.util.ManipulaData;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class Entrada_BD
{
    Connection conn;
    ManipulaData md;

    public Entrada_BD()
    {
        conn = new Conexao().conectar();
        md   = new ManipulaData();
    }

    // ----------------------------------------------------------------
    // SALVAR ENTRADA
    // ----------------------------------------------------------------
    public Entrada salvar(Entrada e)
    {
        try
        {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO entrada(data_entrada, total_entrada, idfornecedor) values(?,?,?)",
                Statement.RETURN_GENERATED_KEYS);

            stmt.setDate(1, md.string2Date(e.getData_entrada()));
            stmt.setDouble(2, e.getTotal_entrada());
            stmt.setInt(3, e.getFornecedor().getId_fornecedor());

            int verif = stmt.executeUpdate(); // Executa o SQL no banco

            if (verif > 0)
            {
                ResultSet rs_id = stmt.getGeneratedKeys();
                if (rs_id.next())
                {
                    e.setId_entrada(rs_id.getInt(1));
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return e;
    }

    // ----------------------------------------------------------------
    // SALVAR ENTRADA_PRODUTO
    // ----------------------------------------------------------------
    public Entrada_Produto salvarEntradaProduto(Entrada_Produto ep)
    {
        try
        {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO entrada_produto (identrada, idproduto, quantidade) values(?,?,?)",
                Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, ep.getEntrada().getId_entrada());
            stmt.setInt(2, ep.getProduto().getId_produto());
            stmt.setInt(3, ep.getQuantidade());

            int verif = stmt.executeUpdate(); // Executa o SQL no banco
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return ep;
    }

    // ----------------------------------------------------------------
    // GET ENTRADAS (com produtos via JOIN)
    // ----------------------------------------------------------------
    public List<Entrada> getEntradas()
    {
        List<Entrada>         lstE  = new ArrayList<>();
        List<Entrada_Produto> lstEP = new ArrayList<>();
        ResultSet rs;

        try
        {
            PreparedStatement ppStmt = conn.prepareStatement(
                "SELECT * FROM fornecedor f, entrada e, entrada_produto ep, produto p " +
                "WHERE f.idfornecedor = e.idfornecedor " +
                "AND e.identrada = ep.identrada " +
                "AND ep.idProduto = p.idproduto");

            rs = ppStmt.executeQuery();
            int verif_id = 0;

            while (rs.next())
            {
                Entrada e = getEntrada(rs);

                if (verif_id == e.getId_entrada())
                {
                    // Mesma entrada — adiciona mais um produto
                    lstEP.add(getItens(rs, e));
                    e = lstE.getLast();
                    e.setItens_entrada(lstEP);
                }
                else
                {
                    // Nova entrada
                    lstEP = new ArrayList<>();
                    lstEP.add(getItens(rs, e));
                    e.setItens_entrada(lstEP);
                    lstE.add(e);
                    verif_id = e.getId_entrada();
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return lstE;
    }

    // ----------------------------------------------------------------
    // HELPER — mapeia ResultSet → Entrada
    // ----------------------------------------------------------------
    public Entrada getEntrada(ResultSet rs) throws SQLException
    {
        Entrada    e = new Entrada();
        Fornecedor f = new Fornecedor();

        e.setId_entrada(rs.getInt("identrada"));
        e.setData_entrada(rs.getString("data_entrada"));
        e.setTotal_entrada(rs.getDouble("total_entrada"));

        f.setId_fornecedor(rs.getInt("idfornecedor"));
        f.setNome(rs.getString("nome"));
        f.setCnpj(rs.getString("cnpj"));

        e.setFornecedor(f);

        return e;
    }

    // ----------------------------------------------------------------
    // HELPER — mapeia ResultSet → Entrada_Produto
    // ----------------------------------------------------------------
    public Entrada_Produto getItens(ResultSet rs, Entrada e) throws SQLException
    {
        Entrada_Produto ep = new Entrada_Produto();
        Produto         p  = new Produto();

        p.setId_produto(rs.getInt("idproduto"));
        p.setNome(rs.getString("nome"));
        p.setValor(rs.getDouble("valor"));

        ep.setQuantidade(rs.getInt("quantidade"));
        ep.setProduto(p);

        if (e != null)
        {
            ep.setEntrada(e);
        }

        return ep;
    }
}