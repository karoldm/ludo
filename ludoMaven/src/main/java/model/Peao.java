package model;

import java.io.FileInputStream;
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

    /**
     *
     */
    public ImageIcon icon;
    private int posicaoInicial;

    /**
     *
     * @param cor
     * @param posicaoInicial
     */
    public Peao(byte cor, int posicaoInicial) {
        this.posicaoInicial = posicaoInicial;
        this.cor = cor;
        this.posicao = 0;
        try {
            switch (cor) {
                case 0 ->
                    this.icon = new ImageIcon(ImageIO.read(new FileInputStream("src\\main\\java\\assets\\peao-azul.png")));
                case 1 ->
                    this.icon = new ImageIcon(ImageIO.read(new FileInputStream("src\\main\\java\\assets\\peao-verde.png")));
                case 2 ->
                    this.icon = new ImageIcon(ImageIO.read(new FileInputStream("src\\main\\java\\assets\\peao-vermelho.png")));
                case 3 ->
                    this.icon = new ImageIcon(ImageIO.read(new FileInputStream("src\\main\\java\\assets\\peao-amarelo.png")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    /**
     *
     * @return
     */
    public int getPosicaoInicial() {
        return posicaoInicial;
    }

    /**
     *
     * @param posicaoInicial
     */
    public void setPosicaoInicial(int posicaoInicial) {
        this.posicaoInicial = posicaoInicial;
    }

    /**
     *
     * @return
     */
    public int getPosicao() {
        return posicao;
    }

    /**
     *
     * @param posicao
     */
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    /**
     *
     * @return
     */
    public byte getCor() {
        return cor;
    }

    /**
     *
     * @param cor
     */
    public void setCor(byte cor) {
        this.cor = cor;
    }
}
