/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_video_game;

import DAO.telefoneDAO;

/**
 *
 * @author LucasDonizeti
 */
public class telefone {

    private String numero;
    private String ddd;
    private final int id;

    public telefone(int id, String numero, String ddd) {
        this.numero = numero;
        this.ddd = ddd;
        this.id = id;
    }

    public void setTelefone(String numero, String ddd) {
        this.numero = numero;
        this.ddd = ddd;
        telefoneDAO.updateTelefone(this);
    }

    public String getNumero() {
        return numero;
    }

    public String getDdd() {
        return ddd;
    }

    public int getId() {
        return id;
    }

}
