
package ludo;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import views.Mainframe;

/**
 *
 * @author Karol
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Mainframe jogo = new Mainframe();
       jogo.setName("Ludo");
       jogo.setDefaultCloseOperation(EXIT_ON_CLOSE);
       jogo.setVisible(true);
    }
    
}
