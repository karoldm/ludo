package controllers;

import classes.Jogador;
import classes.Peao;
import classes.Square;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.RandomGenerator;
import utils.PosicoesPeaoAzul;
import utils.PosicoesPeaoVerde;
import views.ButtonSquare;

/**
 *
 * @author Karol
 */
public class ControladorJogo {

    private final ArrayList<Peao> peoes = new ArrayList<>();
    private final ArrayList<Square> tabuleiro = new ArrayList<>();
    private Jogador jogadorAtual = null;
    private int dado = 0;
    private final Jogador jogador1 = new Jogador( 10, 13, new PosicoesPeaoVerde(), "Jogador 1");
    private final Jogador jogador2 = new Jogador( 1, 4, new PosicoesPeaoAzul(), "Jogador 2");

    public ControladorJogo() {
        //iniciando arrays de peoes 
        //4 peoes para cada jogador

        this.peoes.add(new Peao((byte) 0));
        this.peoes.add(new Peao((byte) 0));
        this.peoes.add(new Peao((byte) 0));
        this.peoes.add(new Peao((byte) 0));

        this.peoes.add(new Peao((byte) 1));
        this.peoes.add(new Peao((byte) 1));
        this.peoes.add(new Peao((byte) 1));
        this.peoes.add(new Peao((byte) 1));

        //Iniciando tabuleiro com 51 casas
        for (int i = 0; i < 58; i++) {
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

        jogadorAtual = jogador1;
    }

    public int[] getPosicaoInicialDisp(ButtonSquare[][] tabuleiro) {
        int i = jogadorAtual.getiInicial();
        int j = jogadorAtual.getjInicial();

        if (tabuleiro[i][i].getPeao() == null) {
            return new int[]{i, i};
        } else if (tabuleiro[j][j].getPeao() == null) {
            return new int[]{j, j};
        } else if (tabuleiro[i][j].getPeao() == null) {
            return new int[]{i, j};
        } else if (tabuleiro[j][i].getPeao() == null) {
            return new int[]{j, i};
        }

        return null;
    }

    public boolean jogadaPermitida(int cor) {
        if (this.jogadorAtual == jogador1 && cor == 1) {
            return true;
        }
        if (this.jogadorAtual == jogador2 && cor == 0) {
            return true;
        }
        return false;
    }

    public Jogador getJogadorAtual() {
        return jogadorAtual;
    }

    public int getDado() {
        return dado;
    }

    public void setDado(int dado) {
        this.dado = dado;
    }

    public Peao getPeao(int i) {
        return peoes.get(i);
    }

    public void proximoJogador() {
        this.jogadorAtual = this.jogadorAtual == jogador1 ? jogador2 : jogador1;
    }
    
    public boolean jogoIniciado(){
        return this.jogador1.isJogadorLiberado() || this.jogador2.isJogadorLiberado();
    }

    //retorna um numero aleatório entre 1 e 6 para simular o dado D6
    public void jogarDado() {
        RandomGenerator randomGenerator = new Random();
        this.dado = randomGenerator.nextInt(6) + 1;
    }

    public int[] getPosicaoMap(int posicao) {
        return jogadorAtual.getPosicaoMap(posicao);
    }

    public void move(Peao p, int novaPosicao) {
        tabuleiro.get(p.getPosicao()).remove(p); //remove o peao da casa atual
        tabuleiro.get(novaPosicao).addPeao(p); //adiciona o peao a nova casa
        p.setPosicao(novaPosicao); //atualiza a posicao do peao
    }

    //Checa se um peao pode ser movido para a nova posição
    public void checarPosicao(Peao p) {
        int novaPosicao = p.getPosicao() + this.dado;
        //Se o jogador passou da casa final, ele deve voltar
        //Só pode chegar na casa final se tirar o número exato no dado para parar nela
        if(novaPosicao > 57) { 
            int dif = novaPosicao - 57;
            novaPosicao = novaPosicao - 2*dif;
        }
        ArrayList<Peao> peoesNovaPosicao = tabuleiro.get(novaPosicao).getPeoes();
        //Já existe um peão nesse quadrado do tabuleiro, e ele tem a cor diferente
        //ou seja, é do inimigo, logo o peao p deve retornar a casa incial
        if (!peoesNovaPosicao.isEmpty()) {
            for (Peao pNovaPosicao : peoesNovaPosicao) {
                if (pNovaPosicao.getCor() != p.getCor()) {
                    this.move(p, 0);
                }
            }
        }
        //Se o movimento é válido o peao é movido para a nova casa
        this.move(p, novaPosicao);
    }
}
