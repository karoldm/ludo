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

    /**
     *
     */
    public ButtonSquare() {
        super();
        this.peoes = new ArrayList<>();

        Border lineBorder = BorderFactory.createLineBorder(Color.black);
        this.setPreferredSize(new Dimension(40, 40));
        this.setBackground(Color.WHITE);
        this.setBorder(lineBorder);
    }

    /**
     *
     * @return
     */
    public Peao getPeao() {
        return !peoes.isEmpty() ? peoes.get(0) : null;
    }

    /**
     *
     * @param peao
     */
    public void addPeao(Peao peao) {
        this.peoes.add(peao);
        if (peoes.size() == 1) {
            this.setIcon(peao.icon);
        }
    }

    /**
     *
     * @param p
     */
    public void removePeao(Peao p) {
        peoes.remove(p);
        if (peoes.isEmpty()) {
            this.setIcon(null);
        }
    }

}
