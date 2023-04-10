package classes;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Karol
 */
public class Peao {

    private int posicao; //posicao atual do peao no tabuleiro, considerando 
    //o tabuleiro como um array
    private byte cor; //0 = vermelho, 1 = azul, 2 = verde, 3 = amarelo
    public ImageIcon icon;
    private int posicaoInicial;

    public Peao(byte cor, int posicaoInicial) {
        this.posicaoInicial = posicaoInicial;
        this.cor = cor;
        this.posicao = 0;
        try {
            switch (cor) {
                case 0 -> this.icon = new ImageIcon(ImageIO.read(getClass().getResource("/assets/peao-azul.png")));
                case 1 -> this.icon = new ImageIcon(ImageIO.read(getClass().getResource("/assets/peao-verde.png")));
                case 2 -> this.icon = new ImageIcon(ImageIO.read(getClass().getResource("/assets/peao-vermelho.png")));
                case 3 -> this.icon = new ImageIcon(ImageIO.read(getClass().getResource("/assets/peao-amarelo.png")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public int getPosicaoInicial() {
        return posicaoInicial;
    }

    public void setPosicaoInicial(int posicaoInicial) {
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
