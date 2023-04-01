
package ludo;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import views.UIJogo;

/**
 *
 * @author Karol
 */
public class Ludo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       UIJogo jogo = new UIJogo();
       jogo.setName("Ludo");
       jogo.setDefaultCloseOperation(EXIT_ON_CLOSE);
       jogo.setVisible(true);
    }
    
}
