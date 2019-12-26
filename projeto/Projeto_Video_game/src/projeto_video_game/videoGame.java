/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_video_game;

import DAO.videogameDAO;

/**
 *
 * @author LucasDonizeti
 */
public class videoGame {

    private String marca;
    private String modelo;
    private int ano;
    private String cor;
    private Boolean desbloqueado;
    private final int id;

    public videoGame(String marca, String modelo, int ano, String cor, Boolean desbloqueado, int id) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.desbloqueado = desbloqueado;
    }

    public void setVideogame(String marca, String modelo, int ano, String cor, Boolean desbloqueado) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.desbloqueado = desbloqueado;
        videogameDAO.updateVideogame(this);
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getCor() {
        return cor;
    }

    public Boolean getDesbloqueado() {
        return desbloqueado;
    }

    public int getId() {
        return id;
    }
}
