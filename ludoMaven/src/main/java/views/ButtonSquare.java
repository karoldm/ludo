package views;

import model.Peao;
import java.awt.Color;
import java.awt.Dimension;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 *
 * @author Karol
 */
public class ButtonSquare extends JButton implements Serializable {

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
     * @return
     */
    public ArrayList<Peao> getPeoes() {
        return this.peoes;
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
    public void removePeao(Peao peao) {
        Peao peaoToRemove = null;
        for (Peao p : this.peoes) {
            if (p.getId() == peao.getId()) {
                peaoToRemove = p;
                break;
            }
        }
        this.peoes.remove(peaoToRemove);
        if (peoes.isEmpty()) {
            this.setIcon(null);
        }
    }

}
