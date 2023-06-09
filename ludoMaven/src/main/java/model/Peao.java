package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author willm
 */
public class Peao implements Serializable {

    //posicao atual do peao no tabuleiro, considerando
    //o tabuleiro como um array
    private int posicao;
    //0 = vermelho, 1 = azul, 2 = verde, 3 = amarelo
    private byte cor;
    private String id;

    /**
     *
     */
    public ImageIcon icon;
    private int posicaoInicial;

    /**
     * A cor é definido pelo
     *
     * @param cor
     * @param posicaoInicial
     * @param id
     */
    public Peao(byte cor, int posicaoInicial, String id) {
        this.id = id;
        this.posicaoInicial = posicaoInicial;
        this.cor = cor;
        this.posicao = 0;
        try {
            switch (cor) {
                case 0 ->
                    this.icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/peao-vermelho.png")));
                case 1 ->
                    this.icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/peao-amarelo.png")));
                case 2 ->
                    this.icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/peao-azul.png")));
                case 3 ->
                    this.icon = new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/peao-verde.png")));
            }
        } catch (IOException ex) {
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

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

}
