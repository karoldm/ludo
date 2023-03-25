package classes;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.random.RandomGenerator;

/**
 *
 * @author Karol
 */
public class Jogo {

    private ArrayList<Peao> peoes = new ArrayList<>();
    private ArrayList<Square> tabuleiro = new ArrayList<>();

    public Jogo() {
        //iniciando arrays de peoes 
        //4 peoes para cada jogador

        this.peoes.add(new Peao((byte) 0, 0));
        this.peoes.add(new Peao((byte) 0, 0));
        this.peoes.add(new Peao((byte) 0, 0));
        this.peoes.add(new Peao((byte) 0, 0));

        this.peoes.add(new Peao((byte) 1, 14));
        this.peoes.add(new Peao((byte) 1, 14));
        this.peoes.add(new Peao((byte) 1, 14));
        this.peoes.add(new Peao((byte) 1, 14));

        //Iniciando tabuleiro com 51 casas
        for (int i = 0; i < 51; i++) {
            tabuleiro.add(new Square());
        }

        //Alocando peões nas suas posições iniciais do tabuleiro
        tabuleiro.get(0).addPeao(peoes.get(0));
        tabuleiro.get(0).addPeao(peoes.get(1));
        tabuleiro.get(0).addPeao(peoes.get(2));
        tabuleiro.get(0).addPeao(peoes.get(3));

        tabuleiro.get(14).addPeao(peoes.get(4));
        tabuleiro.get(14).addPeao(peoes.get(5));
        tabuleiro.get(14).addPeao(peoes.get(6));
        tabuleiro.get(14).addPeao(peoes.get(7));

    }

    //retorna um numero aleatório entre 1 e 6 para simular o dado D6
    public int jogarDado(int jogador) {
        RandomGenerator randomGenerator = new Random();
        return randomGenerator.nextInt(6);
    }

    //Jogador é um byte (0 representa um jogador e 1 o outro)
    public void jogar(int jogador) {
        Scanner in = new Scanner(System.in);

        int dado = jogarDado(jogador);
        System.out.println("Resultado do dado: " + dado);
        System.out.print("Qual peao gostaria de mover? " + (jogador == 0 ? "[0-3]" : "[4-7]"));

        int index = in.nextInt(); //index do peao desejado no array de peoes 
        Peao curr = peoes.get(index);
        if (checarPosicao(curr, dado)) {
            System.out.println("Peao movido, nova posicao: " + curr.getPosicao());
        } else {
            System.out.println("Um peao inimigo esta nessa casa, nova posicao: " + curr.getPosicao());
        }
    }
    
    public void move(Peao p, int novaPosicao){
        tabuleiro.get(p.getPosicao()).remove(p); //remove o peao da casa atual
        tabuleiro.get(novaPosicao).addPeao(p); //adiciona o peao a nova casa
        p.setPosicao(novaPosicao); //atualiza a posicao do peao
    }

    //Checa se um peao pode ser movido para a nova posição
    public boolean checarPosicao(Peao p, int dado) {
        int novaPosicao = p.getPosicao() + dado;
        ArrayList<Peao> peoesNovaPosicao = tabuleiro.get(novaPosicao).getPeoes();
        //Já existe um peão nesse quadrado do tabuleiro, e ele tem a cor diferente
        //ou seja, é do inimigo, logo o peao p deve retornar a casa incial
        if (!peoesNovaPosicao.isEmpty()) {
            for (Peao pNovaPosicao : peoesNovaPosicao) {
                if (pNovaPosicao.getCor() != p.getCor()) {
                    this.move(p, p.posicaoInicial);
                    return false;
                }
            }
        }
        //Se o movimento é válido o peao é movido para a nova casa
        this.move(p, novaPosicao); 
        return true;
    }
}
