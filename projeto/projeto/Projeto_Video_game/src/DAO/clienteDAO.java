/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import projeto_video_game.cliente;

/**
 *
 * @author LucasDonizeti
 */
public class clienteDAO {

    public static void ls() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select pk_id,nome,cpf,email from tb_cliente");
            System.out.println("---------------------------------");
            while (res.next()) {
                System.out.print("id:" + res.getInt("pk_id"));
                System.out.print(" - nome:" + res.getString("nome"));
                System.out.print(" - email:" + res.getString("email"));
                System.out.println(" - cpf:" + res.getString("cpf"));
            }
            st.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.print("nao foi possivel conextar : " + sqle);
        }
    }

    public static cliente getCliente(int id) throws SQLException {
        String nome = "";
        String cpf = "";
        String email = "";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select cpf,nome,email from tb_cliente where pk_id=" + id);

            while (res.next()) {
                System.out.println("?LOG: nome: " + res.getString("nome") + " cpf: " + res.getString("cpf") + " email: " + res.getString("email") + " id: " + id);
                nome = res.getString("nome");
                cpf = res.getString("cpf");
                email = res.getString("email");
            }

            cliente cliente = new cliente(nome, cpf, email, id);
            st.close();
            con.close();
            return cliente;
        } catch (SQLException sqle) {
            System.out.print("nao foi possivel conextar: " + sqle);
            return new cliente("fail", "fail", "fail", 0);
        }
    }

    public static int salvarCliente(cliente cliente) throws SQLException {
        int id = 0;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            st.executeUpdate("insert into tb_cliente(cpf,nome,email) values ('" + cliente.getCpf() + "','" + cliente.getNome() + "','" + cliente.getEmail() + "')");
            ResultSet res = st.executeQuery("select pk_id from tb_cliente where cpf='" + cliente.getCpf() + "'");
            while (res.next()) {
                id = res.getInt("pk_id");
            }
            res.close();
            st.close();
            con.close();
            return id;
        } catch (SQLException sqle) {
            System.out.print("erro ao inserir valor : " + sqle);
            return id;
        }
    }

    public static void deleteCliente(cliente cliente) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            enderecoDAO.deleteEndereco(cliente.getEndereco());
            for (int i = 0; i < cliente.getTelefone().size(); i++) {
                telefoneDAO.deleteTelefone(cliente.getTelefone().get(i));
            }
            for (int i = 0; i < cliente.getVideogame().size(); i++) {
                videogameDAO.deleteVideogame(cliente.getVideogame().get(i));
            }
            st.executeUpdate("DELETE FROM tb_cliente WHERE pk_id='" + cliente.getId() + "'");
            st.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.print("erro ao deletar valor : " + sqle);
        }
    }

    public static void updateCliente(cliente cliente) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE tb_cliente SET nome='" + cliente.getNome() + "' WHERE pk_id='" + cliente.getId() + "'");
            st.executeUpdate("UPDATE tb_cliente SET email='" + cliente.getEmail() + "' WHERE pk_id='" + cliente.getId() + "'");
            st.executeUpdate("UPDATE tb_cliente SET cpf='" + cliente.getCpf() + "' WHERE pk_id='" + cliente.getId() + "'");
            st.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.print("erro ao deletar valor : " + sqle);
        }
    }
}
