/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import projeto_video_game.endereco;

/**
 *
 * @author LucasDonizeti
 */
public class enderecoDAO {

    public static endereco getEndereco(int id) throws SQLException {
        String rua = "";
        int numero = 0;
        String bairro = "";
        String cidade = "";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select rua,numero,bairro,cidade,fk_id_cliente from tb_endereco where fk_id_cliente=" + id);
            res.next();
            rua = res.getString("rua");
            numero = res.getInt("numero");
            bairro = res.getString("bairro");
            cidade = res.getString("cidade");
            endereco endereco = new endereco(rua, numero, bairro, cidade, id);
            st.close();
            con.close();
            return endereco;
        } catch (SQLException sqle) {
            System.out.print("nao foi possivel conextar: " + sqle);
            return new endereco("fail", 0, "fail", "fail", 0);
        }
    }

    public static void salvarEndereco(endereco endereco) throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            st.executeUpdate("insert into tb_endereco( fk_id_cliente,bairro,cidade,rua,numero)  values (" + endereco.getId() + ",'" + endereco.getBairro() + "','" + endereco.getCidade() + "','" + endereco.getRua() + "'," + endereco.getNumero() + ")");
            System.out.print("endereco salvo");
            st.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.print("erro ao inserir valor : " + sqle);
        }
    }

    public static void deleteEndereco(endereco endereco) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();

            st.executeUpdate("DELETE FROM tb_endereco WHERE pk_id='" + endereco.getId() + "'");
            st.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.print("erro ao deletar valor : " + sqle);
        }
    }

    public static void updateEndereco(endereco endereco) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE tb_endereco SET rua='" + endereco.getRua() + "' WHERE pk_id=" + endereco.getId());
            st.executeUpdate("UPDATE tb_endereco SET numero='" + endereco.getNumero() + "' WHERE pk_id=" + endereco.getId());
            st.executeUpdate("UPDATE tb_endereco SET bairro='" + endereco.getBairro() + "' WHERE pk_id=" + endereco.getId());
            st.executeUpdate("UPDATE tb_endereco SET cidade='" + endereco.getCidade() + "' WHERE pk_id=" + endereco.getId());
            st.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.print("erro ao deletar valor : " + sqle);
        }
    }

}
