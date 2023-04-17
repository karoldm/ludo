package controllers;

import model.Jogador;
import model.Peao;
import connection.Client;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.random.RandomGenerator;
import model.Dado;
import utils.PosicoesPeaoAzul;
import utils.PosicoesPeaoVerde;
import views.ButtonSquare;

/**
 *
 * @author Karol
 */
public class ControladorJogo {

    private final Jogador jogador1 = new Jogador(
            1,
            4,
            new PosicoesPeaoAzul(),
            "Jogador 1",
            (byte) 0);
    private final Jogador jogador2 = new Jogador(
            10,
            13,
            new PosicoesPeaoVerde(),
            "Jogador 2",
            (byte) 1);
    private Dado information = new Dado();
    private Jogador jogadorAtual = null;
    private Client client;
    private Thread thread;

    public ControladorJogo() {
        this.client = new Client(this);
        jogadorAtual = jogador1;
    }

    public void connect(String ip, int port) {
        try {
            this.client.setPort(port);
            this.client.setIp(InetAddress.getByName(ip));
            this.client.connect();
            System.out.println("Conectado com sucesso!");
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
        if (this.jogadorAtual == jogador1 && cor == 0) {
            return true;
        }
        if (this.jogadorAtual == jogador2 && cor == 1) {
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
     */
    public void proximoJogador() {
        this.jogadorAtual = this.jogadorAtual == jogador1 ? jogador2 : jogador1;
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

    public Jogador getJogador1() {
        return jogador1;
    }

    public Jogador getJogador2() {
        return jogador2;
    }

    /**
     *
     * @param p
     * @param novaPosicao
     */
    public void move(Peao p, int novaPosicao) {
        jogadorAtual.getTabuleiro().get(p.getPosicao()).remove(p); //remove o peao da casa atua
        jogadorAtual.getTabuleiro().get(novaPosicao).addPeao(p); //adiciona o peao a nova casa
        p.setPosicao(novaPosicao); //atualiza a posicao do peao
    }

    //Checa se um peao pode ser movido para a nova posição
    /**
     *
     * @param p
     */
    public void checarPosicao(Peao p) {
        int novaPosicao = p.getPosicao() + information.getDado();
        //Se o jogador passou da casa final, ele deve voltar
        //Só pode chegar na casa final se tirar o número exato no dado para parar nela
        if (novaPosicao > 57) {
            int dif = novaPosicao - 57;
            novaPosicao = novaPosicao - 2 * dif;
        }

        this.move(p, novaPosicao);
    }
}
