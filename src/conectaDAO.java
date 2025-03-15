
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Adm
 */
public class conectaDAO {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Conexão com o banco bem sucedida");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11_01", "root", "corpcapsule1");
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver não disponível: " + ex.getMessage());
            return null;
            
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
            
        }
    }
}
