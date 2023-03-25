
package classes;

import java.util.ArrayList;

/**
 *
 * @author Karol
 */
public class Square {
    //Peões ocupando esse quadrado do tabuleiro
    //Mais de um peao pode estar nessa casa se eles forem do mesmo jogador
    private ArrayList<Peao> peoes = new ArrayList<>();

    public Square(){
    }
    
    public void addPeao(Peao peao){
      this.peoes.add(peao);
    }

    public ArrayList<Peao> getPeoes(){
      return this.peoes;
    }
    
    public void remove(Peao p){
        this.peoes.remove(p); 
    }
}
