/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Willian
 */
public class Information {

    private ArrayList<Peao> peoes = new ArrayList<>();
    private ArrayList<Square> tabuleiro = new ArrayList<>();
    private int dado = 0;

    public ArrayList<Peao> getPeoes() {
        return peoes;
    }

    public void setPeoes(ArrayList<Peao> peoes) {
        this.peoes = peoes;
    }

    public ArrayList<Square> getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(ArrayList<Square> tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public int getDado() {
        return dado;
    }

    public void setDado(int dado) {
        this.dado = dado;
    }

}
