package model;

import java.io.Serializable;
import views.ButtonSquare;

/**
 *
 * @author willm
 */
public class Move implements Serializable {

    private Jogador jogador;
    private Dado dado;
    private Peao peaoAtual;
    private int[] oldPosition;
    private boolean jogador1;
    private boolean jogador2;

    /**
     * Construtor da classe para ser enviado pela rede
     *
     * @param jogador
     * @param dado
     * @param peao
     * @param oldPosition
     */
    public Move(Jogador jogador, Dado dado, Peao peao, int[] oldPosition) {
        this.jogador = jogador;
        this.dado = dado;
        this.peaoAtual = peao;
        this.oldPosition = oldPosition;
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
    public int[] getOldPosition() {
        return oldPosition;
    }

    /**
     *
     * @param oldPosition
     */
    public void setOldPosition(int[] oldPosition) {
        this.oldPosition = oldPosition;
    }

}
