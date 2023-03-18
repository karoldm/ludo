
package classes;

/**
 *
 * @author Karol
 */
public class Square {
    private Peao peao;

    public Square(Peao peao){
      this.peao = peao;
    }
    
    public void setPeao(Peao peao){
      this.peao = peao;
    }

    public Peao getPeao(){
      return this.peao;
    }
}
