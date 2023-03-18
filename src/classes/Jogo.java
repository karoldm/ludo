
package classes;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Karol
 */
public class Jogo {
    private ArrayList<Peao> peoes = new ArrayList<>(); 
    private ArrayList<Peao> peoes = new ArrayList<>(); 
    
    public Jogo(){
        //iniciando arrays de peoes 
        //4 peoes para cada jogador
        
        this.peoes.add(new Peao((byte)0, 0));
        this.peoes.add(new Peao((byte)0, 0));
        this.peoes.add(new Peao((byte)0, 0));
        this.peoes.add(new Peao((byte)0, 0));
        
        this.peoes.add(new Peao((byte)1, 15));
        this.peoes.add(new Peao((byte)1, 15));
        this.peoes.add(new Peao((byte)1, 15));
        this.peoes.add(new Peao((byte)1, 15));
    }
    
    //retorna um numero aleatório entre 1 e 6 para simular o dado D6
    public int jogarDado(){
        int result = (int) ((Math.random() * (5)) + 1);
        return result;
    }
    
    //Checa se existe mais de um peão na mesma posição do tabuleiro
    //Se existir e os peões forem da mesma cor, retorna true (jogava válida) 
    //Se existir e os peões forem de cores diferentes, retorna false (jogada
    //inválida, peao p retorna a casa inicial)
    public boolean checkPositions(Peao p, int novaPosicao){
        Iterator<Peao> peoesIterator = peoes.iterator();
        
        while(peoesIterator.hasNext()){
            Peao curr = peoesIterator.next();
            if(curr.getPosicao() == novaPosicao && //peao curr esta na mesma posicao 
                    curr.getCor() != p.getCor()) //cores diferentes
                p.setPosicao(p.posicaoInicial); //retornando o peao a posicao incial
        }
        p.setPosicao(novaPosicao); //jogada válida, peao move para nova posicao
    }
}
