/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import projeto_video_game.videoGame;

/**
 *
 * @author LucasDonizeti
 */
public class videogameDAO {

    public static ArrayList<videoGame> getVideogame(int id) throws SQLException {
        ArrayList<videoGame> videogame = new ArrayList();
        String marca;
        String modelo;
        int ano;
        String cor;
        Boolean desbloqueado;
        int id_videogame;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            ResultSet res = st.executeQuery("select pk_id,FK_ID_CLIENTE,MODELO,MARCA,ANO,COR,DESBLOQUEADO from tb_videogame where fk_id_cliente=" + id);
            while (res.next()) {
                id_videogame = res.getInt("pk_id");
                modelo = res.getString("modelo");
                marca = res.getString("marca");
                ano = res.getInt("ano");
                cor = res.getString("cor");
                desbloqueado = (res.getInt("desbloqueado") == 0);
                videoGame vd = new videoGame(marca, modelo, ano, cor, desbloqueado, id_videogame);
                boolean add = videogame.add(vd);
            }
            st.close();
            con.close();
            return videogame;
        } catch (SQLException sqle) {
            System.out.print("nao foi possivel conextar: " + sqle);
            return videogame;
        }
    }

    public static void salvarVideogame(videoGame videogame) throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            st.executeUpdate("insert into tb_videogame(FK_ID_CLIENTE,MODELO,MARCA,ANO,COR,DESBLOQUEADO) values(" + videogame.getId() + ",'" + videogame.getModelo() + "','" + videogame.getMarca() + "'," + videogame.getAno() + ",'" + videogame.getCor() + "'," + (videogame.getDesbloqueado() == true ? 0 : 1) + ")");
            System.out.print("videogame salvo");
            st.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.print("erro ao inserir valor : " + sqle);
        }
    }

    public static void deleteVideogame(videoGame videogame) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();

            st.executeUpdate("DELETE FROM tb_videogame WHERE pk_id='" + videogame.getId() + "'");
            st.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.print("erro ao deletar valor : " + sqle);
        }
    }

    public static void updateVideogame(videoGame videogame) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String login = "system";
        String senha = "system";
        try {
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            Connection con = (Connection) DriverManager.getConnection(url, login, senha);
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE tb_videogame SET marca='" + videogame.getMarca() + "' WHERE pk_id=" + videogame.getId());
            st.executeUpdate("UPDATE tb_videogame SET modelo='" + videogame.getModelo() + "' WHERE pk_id=" + videogame.getId());
            st.executeUpdate("UPDATE tb_videogame SET ano=" + videogame.getAno() + " WHERE pk_id=" + videogame.getId());
            st.executeUpdate("UPDATE tb_videogame SET marca='" + videogame.getCor() + "' WHERE pk_id=" + videogame.getId());
            st.executeUpdate("UPDATE tb_videogame SET desbloqueado=" + (videogame.getDesbloqueado() == true ? 0 : 1) + " WHERE pk_id=" + videogame.getId());

            st.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.print("erro ao deletar valor : " + sqle);
        }
    }
}
