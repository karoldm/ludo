package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Karol
 */
public class Square implements Serializable {

    //Peões ocupando esse quadrado do tabuleiro
    //Mais de um peao pode estar nessa casa se eles forem do mesmo jogador
    private ArrayList<Peao> peoes = new ArrayList<>();

    /**
     *
     */
    public Square() {
    }

    /**
     *
     * @param peao
     */
    public void addPeao(Peao peao) {
        this.peoes.add(peao);
    }

    /**
     *
     * @return
     */
    public ArrayList<Peao> getPeoes() {
        return this.peoes;
    }

    /**
     *
     * @param p
     */
    public void remove(Peao p) {
        this.peoes.remove(p);
    }
}
