/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        conn = new conectaDAO().getConnection();
        if (conn != null) {
            String SQL = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(SQL)) {
                ps.setString(1, produto.getNome());
                ps.setInt(2, produto.getValor());
                ps.setString(3, produto.getStatus());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Conexão não estabelecida na hora da inserção");
        }
    }

    public void venderProduto(int id) {
        Connection conn = conectaDAO.getConnection();

        String SQL = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";

        try (PreparedStatement psp = conn.prepareStatement(SQL)) {
            psp.setInt(1, id);
            psp.executeUpdate();
            JOptionPane.showMessageDialog(null, "Item alterado para status de vendido!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possível vender o item");
        }
    }

    public List<String[]> listarProdutos() {
        List<String[]> produtos = new ArrayList<>();
        String SQL = "SELECT id, nome, valor, status FROM produtos";

        try (Connection conn = conectaDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

            try (ResultSet resultado = stmt.executeQuery()) {
                while (resultado.next()) {
                    String[] linha = {
                        resultado.getString("id"),
                        resultado.getString("nome"),
                        String.valueOf(resultado.getInt("valor")),
                        resultado.getString("status")
                    };
                    produtos.add(linha);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public List<String[]> listarProdutosVendidos() {
        List<String[]> produtosVendido = new ArrayList<>();
        String SQL = "SELECT id, nome, valor, status FROM produtos WHERE status = 'Vendido' ";

        try (Connection conn = conectaDAO.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL)) {

            try (ResultSet resultado = stmt.executeQuery()) {
                while (resultado.next()) {
                    String[] linha = {
                        resultado.getString("id"),
                        resultado.getString("nome"),
                        String.valueOf(resultado.getInt("valor")),
                        resultado.getString("status")
                    };
                    produtosVendido.add(linha);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtosVendido;
    }

}
