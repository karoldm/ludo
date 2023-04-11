package ludo;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import views.Board;

/**
 *
 * @author Karol
 */
public class Ludo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board jogo = new Board();
        jogo.setVisible(true);
    }

}
