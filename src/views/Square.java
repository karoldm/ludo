package views;

import classes.Peao;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author Karol
 */
public class Square extends JButton {

    private Peao peao = null;
    private int quantidade = 0;

    public Square() {
        super();
       
        Border lineBorder = BorderFactory.createLineBorder(Color.black);
        this.setPreferredSize(new Dimension(40, 40));
        this.setBackground(Color.WHITE);
        this.setBorder(lineBorder);
    }

    public Peao getPeao() {
        return peao;
    }

    public void setPeao(Peao peao) {
        this.peao = peao;
        this.quantidade++;
        if (quantidade == 1) {
            this.setIcon(peao.icon);
        }
    }

    public void removePeao() {
        quantidade--;
        if (quantidade == 0) {
            this.setIcon(null);
        }
    }

}
