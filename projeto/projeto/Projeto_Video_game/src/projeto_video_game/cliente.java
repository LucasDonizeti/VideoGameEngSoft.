/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_video_game;

/**
 *
 * @author LucasDonizeti
 */
import DAO.clienteDAO;
import DAO.enderecoDAO;
import DAO.telefoneDAO;
import DAO.videogameDAO;
import java.sql.SQLException;
import java.util.ArrayList;

public class cliente {

    private final int id;
    private String nome;
    private String cpf;
    private String email;
    private endereco endereco;
    private ArrayList<telefone> telefone = new ArrayList();
    private ArrayList<videoGame> videogame = new ArrayList();

    public cliente(String nome, String cpf, String email) throws SQLException {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.id = clienteDAO.salvarCliente(this);
    }

    public cliente(String nome, String cpf, String email, int id) throws SQLException {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.id = id;
        this.endereco = enderecoDAO.getEndereco(this.id);
        this.telefone = telefoneDAO.getTelefone(this.id);
        this.videogame = videogameDAO.getVideogame(this.id);
    }

    public void setCliente(String nome, String cpf, String email) throws SQLException {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        clienteDAO.updateCliente(this);
    }

    public void deletarCliente() throws SQLException {
        clienteDAO.deleteCliente(this);
    }

    public void addEndereco(String rua, int numero, String bairro, String cidade) throws SQLException {
        this.endereco = new endereco(rua, numero, bairro, cidade, this.id);
        enderecoDAO.salvarEndereco(this.endereco);
    }

    public void addTelefone(String ddd, String numero) throws SQLException {
        telefone tel = new telefone(this.id, numero, ddd);
        this.telefone.add(tel);
        telefoneDAO.salvarTelefone(tel);
    }

    public void addVideogame(String marca, String modelo, int ano, String cor, Boolean desbloqueado) throws SQLException {
        videoGame v = new videoGame(marca, modelo, ano, cor, desbloqueado, this.id);
        this.videogame.add(v);
        videogameDAO.salvarVideogame(v);
    }

    public ArrayList<telefone> getTelefone() {
        return this.telefone;
    }

    public ArrayList<videoGame> getVideogame() {
        return this.videogame;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public endereco getEndereco() {
        return endereco;
    }

    public int getId() {
        return this.id;
    }
}
