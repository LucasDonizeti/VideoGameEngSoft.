/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_video_game;

import DAO.clienteDAO;
import static java.lang.Integer.parseInt;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author LucasDonizeti
 */
public class Projeto_Video_game {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        System.out.println("2rodou");
        cliente c;

        while (true) {
            String nome, cpf, email;
            int n;

            String aux = JOptionPane.showInputDialog("0-listar clientes\n1-recuperar cliente por id\n2-criar cliente\n3-deletar cliente\n4-alterar cliente\n5-sair");
            int x = parseInt(aux);
            switch (x) {
                case 1:
                    aux = JOptionPane.showInputDialog("digite id do cliente");
                    n = parseInt(aux);
                    c = clienteDAO.getCliente(n);
                    System.out.println("nome: " + c.getNome());
                    System.out.println("cpf: " + c.getCpf());
                    System.out.println("email: " + c.getEmail());
                    System.out.println("id: " + c.getId());
                    System.out.println("rua: " + c.getEndereco().getRua());
                    System.out.println("numero: " + c.getEndereco().getNumero());
                    System.out.println("bairro: " + c.getEndereco().getBairro());
                    System.out.println("cidade: " + c.getEndereco().getCidade());
                    System.out.println("telefone: ");
                    ArrayList<telefone> telefone = c.getTelefone();
                    for (int i = 0; i < telefone.size(); i++) {
                        System.out.println("- (" + telefone.get(i).getDdd() + ")" + telefone.get(i).getNumero());
                    }
                    System.out.println("videogame: ");
                    ArrayList<videoGame> videogame = c.getVideogame();
                    for (int i = 0; i < videogame.size(); i++) {
                        System.out.println("-" + videogame.get(i).getMarca() + " - " + videogame.get(i).getModelo());
                        System.out.println(" ano:" + videogame.get(i).getAno());
                        System.out.println(" cor:" + videogame.get(i).getCor());
                        System.out.println(" desbloqueado:" + (videogame.get(i).getDesbloqueado() == true ? "sim" : "não"));
                    }

                    break;
                case 2:
                    nome = JOptionPane.showInputDialog("digite nome");
                    cpf = JOptionPane.showInputDialog("digite cpf");
                    email = JOptionPane.showInputDialog("digite email");
                    c = new cliente(nome, cpf, email);
                    String rua = JOptionPane.showInputDialog("digite rua");
                    aux = JOptionPane.showInputDialog("digite numero");
                    int numero = parseInt(aux);
                    String bairro = JOptionPane.showInputDialog("digite bairro");
                    String cidade = JOptionPane.showInputDialog("digite cidade");
                    c.addEndereco(rua, numero, bairro, cidade);

                    boolean cond = true;
                    while (cond) {
                        String adtel_ddd = JOptionPane.showInputDialog("digite ddd");
                        String adtel_num = JOptionPane.showInputDialog("digite telefone");
                        c.addTelefone(adtel_ddd, adtel_num);
                        String opc = JOptionPane.showInputDialog("1-adicionar outro telefone\n0sair");
                        int opc_cond = parseInt(opc);
                        if (opc_cond == 0) {
                            cond = false;
                        }
                    }
                    cond = true;
                    while (cond) {
                        String adv_marca = JOptionPane.showInputDialog("digite marca");
                        String adv_modelo = JOptionPane.showInputDialog("digite modelo");
                        String adv_ano_string = JOptionPane.showInputDialog("digite ano");
                        int adv_ano = parseInt(adv_ano_string);
                        String adv_cor = JOptionPane.showInputDialog("digite cor");
                        boolean cond_adv_desb = true;
                        int adv_desb = 0;
                        while (cond_adv_desb) {
                            String adv_desbloqueado = JOptionPane.showInputDialog("0-desbloqueado \n1-bloqueado");
                            adv_desb = parseInt(adv_desbloqueado);
                            if (adv_desb == 0) {
                                cond_adv_desb = false;
                            } else if (adv_desb == 1) {
                                cond_adv_desb = false;
                            } else {
                                JOptionPane.showMessageDialog(null, "valor invalido");
                            }
                        }
                        c.addVideogame(adv_marca, adv_modelo, adv_ano, adv_cor, (adv_desb == 0));
                        String opc = JOptionPane.showInputDialog("1-adicionar outro videogame\n0sair");
                        int opc_cond = parseInt(opc);
                        if (opc_cond == 0) {
                            cond = false;
                        }
                    }

                    break;

                case 3:
                    aux = JOptionPane.showInputDialog("digite id do cliente");
                    n = parseInt(aux);
                    c = clienteDAO.getCliente(n);
                    clienteDAO.deleteCliente(c);
                    break;
                case 4:
                    aux = JOptionPane.showInputDialog("digite id do cliente");
                    n = parseInt(aux);
                    c = clienteDAO.getCliente(n);
                    nome = JOptionPane.showInputDialog("digite novo nome. atual:" + c.getNome());
                    cpf = JOptionPane.showInputDialog("digite novo cpf. atual:" + c.getCpf());
                    email = JOptionPane.showInputDialog("digite novo email. atual:" + c.getEmail());
                    c.setCliente(nome, cpf, email);
                    rua = JOptionPane.showInputDialog("digite rua, rua atual:" + c.getEndereco().getRua());
                    aux = JOptionPane.showInputDialog("digite numero, numero atual" + c.getEndereco().getNumero());
                    numero = parseInt(aux);
                    bairro = JOptionPane.showInputDialog("digite bairro, bairro atual:" + c.getEndereco().getBairro());
                    cidade = JOptionPane.showInputDialog("digite cidade, cidade atual:" + c.getEndereco().getCidade());
                    c.getEndereco().setEdereco(rua, numero, bairro, cidade);
                    ArrayList<telefone> setelefone = c.getTelefone();
                    for (int i = 0; i < setelefone.size(); i++) {
                        String set_ddd = JOptionPane.showInputDialog("digite ddd, ddd atual - (" + setelefone.get(i).getDdd() + ")");
                        String set_numero = JOptionPane.showInputDialog("digite numero, numero atual: " + setelefone.get(i).getNumero());
                        setelefone.get(i).setTelefone(set_numero, set_ddd);
                    }
                    ArrayList<videoGame> setvideogame = c.getVideogame();
                    for (int i = 0; i < setvideogame.size(); i++) {
                        String adv_marca = JOptionPane.showInputDialog("digite marca, marca atual:" + setvideogame.get(i).getMarca());
                        String adv_modelo = JOptionPane.showInputDialog("digite modelo, modelo atual" + setvideogame.get(i).getModelo());
                        String adv_ano_string = JOptionPane.showInputDialog("digite ano, ano atual" + setvideogame.get(i).getAno());
                        int adv_ano = parseInt(adv_ano_string);
                        String adv_cor = JOptionPane.showInputDialog("digite cor, cor atual " + setvideogame.get(i).getCor());
                        boolean cond_adv_desb = true;
                        int adv_desb = 0;
                        while (cond_adv_desb) {
                            String adv_desbloqueado = JOptionPane.showInputDialog("0-desbloqueado \n1-bloqueado, atualmente: " + (setvideogame.get(i).getDesbloqueado() == true ? "desbloqueado" : "bloqueado"));
                            adv_desb = parseInt(adv_desbloqueado);
                            if (adv_desb == 0) {
                                cond_adv_desb = false;
                            } else if (adv_desb == 1) {
                                cond_adv_desb = false;
                            } else {
                                JOptionPane.showMessageDialog(null, "valor invalido");
                            }
                        }
                        setvideogame.get(i).setVideogame(adv_marca, adv_modelo, adv_ano, adv_cor, (adv_desb == 0));

                    }

                    break;
                case 5:
                    System.exit(0);
                    break;
                case 0:
                    clienteDAO.ls();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "opcao invalida");
            }
        }

    }

}
