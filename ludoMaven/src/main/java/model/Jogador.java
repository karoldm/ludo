package model;

import java.io.Serializable;
import java.util.ArrayList;
import utils.PosicaoPeaoMap;

public class Jogador implements Serializable {

    private int iInicial;
    private int jInicial;
    private final PosicaoPeaoMap posicoesPeao;
    private final String nome;
    private ArrayList<Square> tabuleiro = new ArrayList<>();
    private ArrayList<Peao> peoes = new ArrayList<>();

    /**
     *
     * @param iInicial
     * @param jInicial
     * @param posicoesPeao
     * @param nome
     * @param cor
     */
    public Jogador(int iInicial, int jInicial, PosicaoPeaoMap posicoesPeao, String nome, byte cor) {
        this.iInicial = iInicial;
        this.jInicial = jInicial;
        this.posicoesPeao = posicoesPeao;
        this.nome = nome;

        for (int i = 0; i < 4; i++) {
            this.peoes.add(new Peao(cor, 0));
        }

        //Iniciando tabuleiro com 58 casas
        for (int i = 0; i < 58; i++) {
            this.tabuleiro.add(new Square());
        }
    }

    /**
     *
     * @param posicao
     * @return
     */
    public int[] getPosicaoMap(int posicao) {
        return posicoesPeao.posicao.get(posicao);
    }

    /**
     *
     * @return
     */
    public boolean todosOsPeoesNoInicioOuFim() {
        for (Peao p : this.peoes) {
            if (p.getPosicao() != 0 && p.getPosicao() != 57) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return
     */
    public int getiInicial() {
        return iInicial;
    }

    /**
     *
     * @param iInicial
     */
    public void setiInicial(int iInicial) {
        this.iInicial = iInicial;
    }

    /**
     *
     * @return
     */
    public int getjInicial() {
        return jInicial;
    }

    /**
     *
     * @param jInicial
     */
    public void setjInicial(int jInicial) {
        this.jInicial = jInicial;
    }

    /**
     *
     * @return
     */
    public ArrayList<Square> getTabuleiro() {
        return tabuleiro;
    }

    /**
     *
     * @param tabuleiro
     */
    public void setTabuleiro(ArrayList<Square> tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    /**
     *
     * @return
     */
    public ArrayList<Peao> getPeoes() {
        return peoes;
    }

    /**
     *
     * @param peoes
     */
    public void setPeoes(ArrayList<Peao> peoes) {
        this.peoes = peoes;
    }

    /**
     *
     * @param i
     * @return
     */
    public Peao getPeao(int i) {
        return this.peoes.get(i);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.nome;
    }

}
