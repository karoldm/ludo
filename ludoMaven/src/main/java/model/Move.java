package model;

import java.io.Serializable;
import views.ButtonSquare;

public class Move implements Serializable {

    private Jogador jogador;
    private Dado dado;
    private Peao peaoAtual;
    private ButtonSquare oldSquare;

    /**
     *
     * @param jogador
     * @param dado
     * @param peao
     * @param oldSquare
     */
    public Move(Jogador jogador, Dado dado, Peao peao, ButtonSquare oldSquare) {
        this.jogador = jogador;
        this.dado = dado;
        this.peaoAtual = peao;
        this.oldSquare = oldSquare;
    }

    /**
     *
     * @return
     */
    public Jogador getJogador() {
        return jogador;
    }

    /**
     *
     * @param jogador
     */
    public void setJogador(Jogador jogador) {
        this.jogador = jogador;
    }

    /**
     *
     * @return
     */
    public Dado getDado() {
        return dado;
    }

    /**
     *
     * @param dado
     */
    public void setDado(Dado dado) {
        this.dado = dado;
    }

    /**
     *
     * @return
     */
    public Peao getPeao() {
        return peaoAtual;
    }

    /**
     *
     * @param peao
     */
    public void setPeao(Peao peao) {
        this.peaoAtual = peao;
    }

    /**
     *
     * @return
     */
    public Peao getPeaoAtual() {
        return peaoAtual;
    }

    /**
     *
     * @param peaoAtual
     */
    public void setPeaoAtual(Peao peaoAtual) {
        this.peaoAtual = peaoAtual;
    }

    /**
     *
     * @return
     */
    public ButtonSquare getOldSquare() {
        return oldSquare;
    }

    /**
     *
     * @param oldSquare
     */
    public void setOldSquare(ButtonSquare oldSquare) {
        this.oldSquare = oldSquare;
    }

}
