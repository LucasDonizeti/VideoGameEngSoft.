/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import projeto_video_game.telefone;

/**
 *
 * @author LucasDonizeti
 */
public class telefoneDAO {

    public static ArrayList<telefone> getTelefone(int id) throws SQLException {
        ArrayList<telefone> telefone = new ArrayList();
        String numero = "";
        String ddd = "";
        int id_telefone;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select pk_id,numero,ddd from tb_telefone where fk_id_cliente=" + id);
            while (res.next()) {
                id_telefone = res.getInt("pk_id");
                numero = res.getString("numero");
                ddd = res.getString("ddd");
                telefone tel = new telefone(id_telefone, numero, ddd);
                boolean add = telefone.add(tel);
            }
            st.close();
            con.close();
            return telefone;
        } catch (SQLException sqle) {
            System.out.print("nao foi possivel conextar: " + sqle);
            return telefone;
        }
    }

    public static void salvarTelefone(telefone telefone) throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            st.executeUpdate("insert into tb_telefone(FK_ID_CLIENTE,NUMERO,ddd) values(" + telefone.getId() + ",'" + telefone.getNumero() + "','" + telefone.getDdd() + "')");
            System.out.print("telefone salvo");
            st.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.print("erro ao inserir valor : " + sqle);
        }
    }

    public static void deleteTelefone(telefone telefone) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();

            st.executeUpdate("DELETE FROM tb_telefone WHERE pk_id='" + telefone.getId() + "'");
            st.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.print("erro ao deletar valor : " + sqle);
        }
    }

    public static void updateTelefone(telefone telefone) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE tb_telefone SET ddd='" + telefone.getDdd() + "' WHERE pk_id=" + telefone.getId());
            st.executeUpdate("UPDATE tb_telefone SET numero='" + telefone.getNumero() + "' WHERE pk_id=" + telefone.getId());
            st.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.print("erro ao deletar valor : " + sqle);
        }
    }
}
