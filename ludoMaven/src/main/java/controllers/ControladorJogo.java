package controllers;

import model.Jogador;
import model.Peao;
import model.Square;
import connection.Client;
import connection.Server;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.random.RandomGenerator;
import model.Information;
import utils.PosicoesPeaoAzul;
import utils.PosicoesPeaoVerde;
import views.ButtonSquare;

/**
 *
 * @author Karol
 */
public class ControladorJogo {

    private final Jogador jogador1 = new Jogador(10, 13, new PosicoesPeaoVerde(), "Jogador 1");
    private final Jogador jogador2 = new Jogador(1, 4, new PosicoesPeaoAzul(), "Jogador 2");
    private Information information = new Information();
    private Jogador jogadorAtual = null;
    private Client client;
    private Thread thread;

    public ControladorJogo() {
        this.client = new Client(this);

        //iniciando arrays de peoes
        //4 peoes para cada jogador
        for (int i = 0; i < 4; i++) {
            information.getPeoes().add(new Peao((byte) 0, 0));

        }
        for (int i = 0; i < 4; i++) {
            information.getPeoes().add(new Peao((byte) 1, 14));

        }
        //Iniciando tabuleiro com 51 casas
        for (int i = 0; i < 58; i++) {
            information.getTabuleiro().add(new Square());
        }

        //Alocando peões nas suas posições iniciais do tabuleiro
        for (int i = 0; i < 4; i++) {
            information.getTabuleiro().get(0).addPeao(information.getPeoes().get(i));
        }
        for (int i = 0; i < 4; i++) {
            information.getTabuleiro().get(14).addPeao(information.getPeoes().get(4 + i));
        }

        jogadorAtual = jogador1;
    }

    public void connect(String ip, int port) {
        try {
            this.client.setPort(port);
            this.client.setIp(InetAddress.getByName(ip));
            this.client.connect();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ControladorJogo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void host() {
        this.client.hostServer();
    }

    public String getIP() {
        return this.client.getIp().getHostAddress();
    }

    public String getPort() {
        return Integer.toString(this.client.getPort());
    }

    /**
     *
     * @param tabuleiro
     * @return
     */
    public int[] getPosicaoInicialDisponivel(ButtonSquare[][] tabuleiro) {
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

    /**
     *
     * @param cor
     * @return
     */
    public boolean jogadaPermitida(int cor) {
        if (this.jogadorAtual == jogador1 && cor == 1) {
            return true;
        }
        if (this.jogadorAtual == jogador2 && cor == 0) {
            return true;
        }
        return false;
    }

    /**
     *
     * @return
     */
    public Jogador getJogadorAtual() {
        return jogadorAtual;
    }

    /**
     *
     * @return
     */
    public int getDado() {
        return information.getDado();
    }

    /**
     *
     * @param dado
     */
    public void setDado(int dado) {
        information.setDado(dado);
    }

    /**
     *
     * @param i
     * @return
     */
    public Peao getPeao(int i) {
        return information.getPeoes().get(i);
//         peoes.get(i);
    }

    /**
     *
     */
    public void proximoJogador() {
        this.jogadorAtual = this.jogadorAtual == jogador1 ? jogador2 : jogador1;
    }

    /**
     *
     * @return
     */
    public boolean jogoIniciado() {
        return this.jogador1.isJogadorLiberado() || this.jogador2.isJogadorLiberado();
    }

    //retorna um numero aleatório entre 1 e 6 para simular o dado D6
    /**
     *
     */
    public void jogarDado() {
        RandomGenerator randomGenerator = new Random();
        information.setDado(randomGenerator.nextInt(6) + 1);
//         = randomGenerator.nextInt(6) + 1;
//        client.sendMessage(String.valueOf(this.dado));
    }

    /**
     *
     * @param numero
     */
    public void jogarDado(int numero) {

        information.setDado(numero);
    }

    /**
     *
     * @param posicao
     * @return
     */
    public int[] getPosicaoMap(int posicao) {
        return jogadorAtual.getPosicaoMap(posicao);
    }

    /**
     *
     * @param p
     * @param novaPosicao
     */
    public void move(Peao p, int novaPosicao) {
        information.getTabuleiro().get(p.getPosicao()).remove(p); //remove o peao da casa atua
        information.getTabuleiro().get(novaPosicao).addPeao(p); //adiciona o peao a nova casa
        p.setPosicao(novaPosicao); //atualiza a posicao do peao
    }

    //Checa se um peao pode ser movido para a nova posição
    /**
     *
     * @param p
     * @return
     */
    public Peao checarPosicao(Peao p) {
        Peao pInimigo = null;
        int novaPosicao = p.getPosicao() + information.getDado();
        //Se o jogador passou da casa final, ele deve voltar
        //Só pode chegar na casa final se tirar o número exato no dado para parar nela
        if (novaPosicao > 57) {
            int dif = novaPosicao - 57;
            novaPosicao = novaPosicao - 2 * dif;
        }
        ArrayList<Peao> peoesNovaPosicao = information.getTabuleiro().get(novaPosicao).getPeoes();
        System.out.println("NOVA POSICAO: " + novaPosicao);
        //Já existe um peão nesse quadrado do tabuleiro, e ele tem a cor diferente
        //ou seja, é do inimigo, logo o peao p deve retornar a casa incial
        System.out.println("Peao parametro: " + p.toString());
        for (Peao pTeste : information.getPeoes()) {
            System.out.println("Peao: " + pTeste.toString() + "Posicao: " + pTeste.getPosicao());
        }
        if (!peoesNovaPosicao.isEmpty()) {
            for (Peao pNovaPosicao : peoesNovaPosicao) {
                if (pNovaPosicao.getCor() != p.getCor()) {
                    this.move(pNovaPosicao, 0);
                    pInimigo = pNovaPosicao;
                    break;
                }
            }
        }
        //Se o movimento é válido o peao é movido para a nova casa
        this.move(p, novaPosicao);
        return pInimigo;
    }
}
