package model;

import utils.PosicaoPeaoMap;

/**
 *
 * @author Karol
 */
public class Jogador {

    private int iInicial;
    private int jInicial;
    private final PosicaoPeaoMap posicoesPeao;
    private final String nome;
    private boolean jogadorLiberado = false;

    /**
     *
     * @param iInicial
     * @param jInicial
     * @param posicoesPeao
     * @param nome
     */
    public Jogador(int iInicial, int jInicial, PosicaoPeaoMap posicoesPeao, String nome) {
        this.iInicial = iInicial;
        this.jInicial = jInicial;
        this.posicoesPeao = posicoesPeao;
        this.nome = nome;
    }
    
    /**
     *
     * @param posicao
     * @return
     */
    public int[] getPosicaoMap(int posicao){
        return posicoesPeao.posicao.get(posicao);
    }

    /**
     *
     * @return
     */
    public boolean isJogadorLiberado() {
        return jogadorLiberado;
    }

    /**
     *
     * @param jogadorLiberado
     */
    public void setJogadorLiberado(boolean jogadorLiberado) {
        this.jogadorLiberado = jogadorLiberado;
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
    @Override
    public String toString(){
        return this.nome;
    }

}
