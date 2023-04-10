package views;

import classes.Peao;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author Karol
 */
public class ButtonSquare extends JButton {

    private final ArrayList<Peao> peoes;

    public ButtonSquare() {
        super();
        this.peoes = new ArrayList<>();
       
        Border lineBorder = BorderFactory.createLineBorder(Color.black);
        this.setPreferredSize(new Dimension(40, 40));
        this.setBackground(Color.WHITE);
        this.setBorder(lineBorder);
    }

    public Peao getPeao() {
        return !peoes.isEmpty() ? peoes.get(0) : null;
    }

    public void addPeao(Peao peao) {
        this.peoes.add(peao);
        if (peoes.size() == 1) {
            this.setIcon(peao.icon);
        }
    }

    public void removePeao() {
        peoes.remove(0);
        if (peoes.isEmpty()) {
            this.setIcon(null);
        }
    }

}
