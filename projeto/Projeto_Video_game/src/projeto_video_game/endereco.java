/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_video_game;

import DAO.enderecoDAO;

/**
 *
 * @author LucasDonizeti
 */
public class endereco {

    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private final int id;

    public endereco(String rua, int numero, String bairro, String cidade, int id) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.id = id;
    }

    public void setEdereco(String rua, int numero, String bairro, String cidade) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        enderecoDAO.updateEndereco(this);
    }

    public String getRua() {
        return rua;
    }

    public int getNumero() {
        return numero;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public int getId() {
        return id;
    }

}
