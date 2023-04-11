
package ludo;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import views.Main;

/**
 *
 * @author Karol
 */
public class Ludo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Main jogo = new Main();
       jogo.setName("Ludo");
       jogo.setDefaultCloseOperation(EXIT_ON_CLOSE);
       jogo.setVisible(true);
    }
    
}
