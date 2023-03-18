
package classes;

import java.util.ArrayList;
import java.util.Scanner;   

/**
 *
 * @author Karol
 */
public class Jogo {
    private ArrayList<Peao> peoes = new ArrayList<>(); 
    private ArrayList<Square> tabuleiro = new ArrayList<>();
    
    public Jogo(){
        //iniciando arrays de peoes 
        //4 peoes para cada jogador
        
        this.peoes.add(new Peao((byte)0, 0));
        this.peoes.add(new Peao((byte)0, 0));
        this.peoes.add(new Peao((byte)0, 0));
        this.peoes.add(new Peao((byte)0, 0));
        
        this.peoes.add(new Peao((byte)1, 14));
        this.peoes.add(new Peao((byte)1, 14));
        this.peoes.add(new Peao((byte)1, 14));
        this.peoes.add(new Peao((byte)1, 14));

        //Iniciando tabuleiro
        for(int i = 0; i < 51; i++){
            tabuleiro.add(new Square(null));
        }

        tabuleiro.get(0).setPeao(peoes.get(0));
        tabuleiro.get(0).setPeao(peoes.get(1));
        tabuleiro.get(0).setPeao(peoes.get(2));
        tabuleiro.get(0).setPeao(peoes.get(3));

        tabuleiro.get(14).setPeao(peoes.get(4));
        tabuleiro.get(14).setPeao(peoes.get(5));
        tabuleiro.get(14).setPeao(peoes.get(6));
        tabuleiro.get(14).setPeao(peoes.get(7));

    }
    
    //retorna um numero aleatório entre 1 e 6 para simular o dado D6
    public int jogarDado(){
        int result = (int) ((Math.random() * (5)) + 1);
        return result;
    }

    //Jogador é um byte (0 representa um jogador e 1 o outro)
    public void jogar(int jogador){
        Scanner in = new Scanner(System.in); 
        
        int dado = jogarDado();
        System.out.println("Resultado do dado: " + dado);
        System.out.print("Qual peao gostaria de mover? " + (jogador == 0 ? "[0-3]" : "[4-7]"));

        int index = in.nextInt(); //index do peao desejado no array de peoes 
        if(checarPosicao(peoes.get(index), dado))
            System.out.println("Peão movido, nova posição: " + peoes.get(index).getPosicao());
        else 
            System.out.println("Um peão inimigo está nessa casa, nova posição: " + peoes.get(index).getPosicao());
    }
    
    //Checa se um peao pode ser movido para a nova posição
    public boolean checarPosicao(Peao p, int dado){
        int novaPosicao = p.getPosicao() + dado;
        Peao peaoNovaPosicao = tabuleiro.get(novaPosicao).getPeao();
        //Já existe um peão nesse quadrado do tabuleiro, e ele tem a cor diferente
        //ou seja, é do inimigo, logo o peao p deve retornar a casa incial
        if(peaoNovaPosicao != null &&  peaoNovaPosicao.getCor() != p.getCor()){ 
            p.setPosicao(p.posicaoInicial);
            tabuleiro.get(p.posicaoInicial).setPeao(p);
            return false;
        }
        p.setPosicao(novaPosicao); //jogada válida, peao move para nova posicao
        tabuleiro.get(p.getPosicao() + dado).setPeao(p);
        return true;
    }
}
