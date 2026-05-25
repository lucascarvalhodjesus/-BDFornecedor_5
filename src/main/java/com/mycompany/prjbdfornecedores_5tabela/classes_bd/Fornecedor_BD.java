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

public class Fornecedor_BD
{
    Connection conn;
    Contato_BD c_BD;

    public Fornecedor_BD()
    {
        conn  = new Conexao().conectar();
        c_BD  = new Contato_BD();
    }

    // ----------------------------------------------------------------
    // SALVAR
    // ----------------------------------------------------------------
    public Fornecedor salvar(Fornecedor f)
    {
        try
        {
            PreparedStatement stmt = conn.prepareStatement
            ("INSERT INTO fornecedor(nome, cnpj) values(?,?)",
                Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCnpj());

            int verif = stmt.executeUpdate(); // Executa o SQL no banco

            if (verif > 0)
            {
                ResultSet rs_id = stmt.getGeneratedKeys();
                if (rs_id.next())
                {
                    f.setId_fornecedor(rs_id.getInt(1));
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return f;
    }

    // ----------------------------------------------------------------
    // GET FORNECEDORES (sem contatos)
    // ----------------------------------------------------------------
    public List<Fornecedor> getFornecedores()
    {
        List<Fornecedor> lstF = new ArrayList<>();
        ResultSet rs;
        try
        {
            PreparedStatement ppStmt = conn.prepareStatement
            ("SELECT * FROM fornecedor");

            rs = ppStmt.executeQuery();
            while (rs.next())
            {
                lstF.add(getFornecedor(rs));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return lstF;
    }

    // ----------------------------------------------------------------
    // GET FORNECEDORES COM CONTATOS (LEFT JOIN)
    // ----------------------------------------------------------------
    public List<Fornecedor> getFornecedores_Contatos()
    {
        List<Fornecedor> lstF = new ArrayList<>();
        List<Contato>    lstC = new ArrayList<>();
        ResultSet rs;
        try
        {
            PreparedStatement ppStmt = conn.prepareStatement
            ("SELECT * FROM fornecedor LEFT JOIN contato ON fornecedor.idfornecedor = contato.idfornecedor");

            rs = ppStmt.executeQuery();
            int verif_id = 0;

            while (rs.next())
            {
                if (rs.getInt("idcontato") == 0)
                {
                    // Fornecedor sem nenhum contato
                    lstF.add(getFornecedor(rs));
                }
                else
                {
                    Fornecedor f = getFornecedor(rs);

                    if (verif_id == f.getId_fornecedor())
                    {
                        // Mesmo fornecedor — apenas adiciona mais um contato
                        lstC.add(c_BD.getContato(rs, f));
                        f = lstF.getLast();
                        f.setContatos(lstC);
                    }
                    else
                    {
                        // Novo fornecedor
                        lstC = new ArrayList<>();
                        lstC.add(c_BD.getContato(rs, f));
                        f.setContatos(lstC);
                        lstF.add(f);
                        verif_id = f.getId_fornecedor();
                    }
                }
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return lstF;
    }

    // ----------------------------------------------------------------
    // HELPER PRIVADO — mapeia ResultSet → Fornecedor
    // ----------------------------------------------------------------
    private Fornecedor getFornecedor(ResultSet rs) throws SQLException
    {
        Fornecedor f = new Fornecedor();

        f.setId_fornecedor(rs.getInt("idfornecedor"));
        f.setNome(rs.getString("nome"));
        f.setCnpj(rs.getString("cnpj"));

        return f;
    }
}