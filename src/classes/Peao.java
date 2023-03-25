
package classes;

/**
 *
 * @author Karol
 */
public class Peao {
    private int posicao; //posicao atual do peao no tabuleiro, considerando 
    //o tabuleiro como um array
    private byte cor; //0 = vermelho, 1 = azul 
    public int posicaoInicial; //posição que o peao inicia no tabuleiro
    
    public Peao(byte cor, int posicaoInicial){
        this.cor = cor;
        this.posicao = posicaoInicial;
        this.posicaoInicial = posicaoInicial;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public byte getCor() {
        return cor;
    }

    public void setCor(byte cor) {
        this.cor = cor;
    }
}
