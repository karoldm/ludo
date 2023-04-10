package classes;

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

    public Jogador(int iInicial, int jInicial, PosicaoPeaoMap posicoesPeao, String nome) {
        this.iInicial = iInicial;
        this.jInicial = jInicial;
        this.posicoesPeao = posicoesPeao;
        this.nome = nome;
    }
    
    public int[] getPosicaoMap(int posicao){
        return posicoesPeao.posicao.get(posicao);
    }

    public boolean isJogadorLiberado() {
        return jogadorLiberado;
    }

    public void setJogadorLiberado(boolean jogadorLiberado) {
        this.jogadorLiberado = jogadorLiberado;
    }

    public int getiInicial() {
        return iInicial;
    }

    public void setiInicial(int iInicial) {
        this.iInicial = iInicial;
    }

    public int getjInicial() {
        return jInicial;
    }

    public void setjInicial(int jInicial) {
        this.jInicial = jInicial;
    }
    
    @Override
    public String toString(){
        return this.nome;
    }

}
