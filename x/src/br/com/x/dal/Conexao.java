package br.com.x.dal;

import java.sql.*;

/**
 *
 * @author muniz
 */
public class Conexao {

    //Método responsável por estabelecer a conexão com o banco
    public static Connection conector() {

        java.sql.Connection conexao = null;

        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/x";
        String user = "root";
        String pass = "saosao";

        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, pass);
            return conexao;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
