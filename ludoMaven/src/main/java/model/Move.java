/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import views.ButtonSquare;

/**
 *
 * @author Willian
 */
public class Move implements Serializable {

    private Jogador jogador;
    private Dado dado;
    private Peao peaoAtual;
    private ButtonSquare oldSquare;

    public Move(Jogador jogador, Dado dado, Peao peao, ButtonSquare oldSquare) {
        this.jogador = jogador;
        this.dado = dado;
        this.peaoAtual = peao;
        this.oldSquare = oldSquare;
    }

    public Jogador getJogador() {
        return jogador;
    }

    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    public Dado getDado() {
        return dado;
    }

    public void setDado(Dado dado) {
        this.dado = dado;
    }

    public Peao getPeao() {
        return peaoAtual;
    }

    public void setPeao(Peao peao) {
        this.peaoAtual = peao;
    }

    public Peao getPeaoAtual() {
        return peaoAtual;
    }

    public void setPeaoAtual(Peao peaoAtual) {
        this.peaoAtual = peaoAtual;
    }

    public ButtonSquare getOldSquare() {
        return oldSquare;
    }

    public void setOldSquare(ButtonSquare oldSquare) {
        this.oldSquare = oldSquare;
    }

}
