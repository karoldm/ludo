package controllers;

import model.Jogador;
import model.Peao;
import connection.Connection;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.random.RandomGenerator;
import javax.swing.JOptionPane;
import model.Dado;
import model.Move;
import model.Square;
import utils.PosicoesPeaoAzul;
import utils.PosicoesPeaoVerde;
import views.Board;
import views.ButtonSquare;

/**
 *
 * @author Bruno Augusto Furquim
 * @author Gabriel Ribeiro Ferreira
 * @author Karolyne Domiciano Marques
 * @author Willian Yoshio Murayama
 */
public class Controller {

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
    private Dado dado = new Dado();
    private Jogador jogadorAtual = null;
    private Connection connection;
    private Thread thread;
    private static Controller controller;
    private Board board;

    /**
     *
     * @return
     */
    private Controller() {
        this.connection = new Connection(this);
        jogadorAtual = jogador1;
    }

    /**
     * Implementação do padrão Singleton, apenas uma instância da classe é
     * criado durante a execução
     *
     * @return
     */
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    /**
     * A conexão chama este método e atualiza o tabuleiro com a jogada do
     * oponente
     *
     * @param move
     */
    public void updateMove(Move move) {
        if (move.isJogadorDesistiu()) {
            desistir();
            JOptionPane.showMessageDialog(board, "Seu inimigo desistiu do Jogo!");
            board.disableButton();
            return;
        }
        if (move.getPeao() != null) {
            board.updateMove(move);
        }
        if (!move.isPlayAgain()) {
            board.enableButton();
        } else {
            board.disableButton();
        }
        board.updateChat(String.valueOf(move.getDado().getDado()));
    }

    /**
     * Envia o objeto serializado para o oponente atraves da conexão
     *
     * @param move
     */
    public void sendMove(Move move) {
        connection.sendMove(move);
        if (!move.isPlayAgain()) {
            board.disableButton();
        }
    }

    /**
     * Inicializa a conexão do jogo e define a aplicação como cliente
     *
     * @param ip
     * @param port
     */
    public void connect(String ip, int port) {
        try {
            this.connection.setPort(port);
            this.connection.setIp(InetAddress.getByName(ip));
            this.connection.connect();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Cancela a conexão do jogo
     */
    public void cancel() {
        connection.cancelHost();
        connection.disconnect();
    }

    /**
     * Define a aplicação como host
     */
    public void host() {
        this.connection.host();
        jogadorAtual = jogador2;
    }

    /**
     * Interrompe a thread
     */
    public void interrupt() {
        thread.interrupt();
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
     */
    public void jogadorConectado() {
        this.thread = new Thread(this.connection);
        this.thread.start();
        JOptionPane.showMessageDialog(null, "Oponente conectado!");
    }

    /**
     * Retorna um numero aleatório entre 1 e 6 para simular o dado D6
     */
    public void jogarDado() {
        RandomGenerator randomGenerator = new Random();
        dado.setDado(randomGenerator.nextInt(6) + 1);
    }

    /**
     * Retorna o número escolhido pelo jogador para debug
     *
     * @param numero
     */
    public void jogarDado(int numero) {
        dado.setDado(numero);
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
     * @return
     */
    public Jogador getJogador1() {
        return jogador1;
    }

    /**
     *
     * @return
     */
    public Jogador getJogador2() {
        return jogador2;
    }

    /**
     * Realiza a movimentação do peão
     *
     * @param p
     * @param novaPosicao
     */
    public void move(Peao p, int novaPosicao) {
        jogadorAtual.getTabuleiro().get(p.getPosicao()).remove(p); //remove o peao da casa atua
        jogadorAtual.getTabuleiro().get(novaPosicao).addPeao(p); //adiciona o peao a nova casa
        p.setPosicao(novaPosicao); //atualiza a posicao do peao
    }

    /**
     * Checa se um peao pode ser movido para a nova posição
     *
     * @param p
     */
    public void checarPosicao(Peao p) {
        int novaPosicao = p.getPosicao() + dado.getDado();
        //Se o jogador passou da casa final, ele deve voltar
        //Só pode chegar na casa final se tirar o número exato no dado para parar nela
        if (novaPosicao > 57) {
            int dif = novaPosicao - 57;
            novaPosicao = novaPosicao - 2 * dif;
        }

        this.move(p, novaPosicao);
    }

    /**
     * Checa se existe um vencedor
     *
     * @param jogadorAtual
     */
    public void checkVictory(Jogador jogadorAtual) {
        if (jogadorAtual.todosOsPeoesNoFim()) {
            String message = "Vencedor: " + jogadorAtual.toString();
            JOptionPane.showMessageDialog(null, message);
        }
    }

    /**
     *
     * @return
     */
    public Dado getInformation() {
        return dado;
    }

    /**
     *
     * @param information
     */
    public void setInformation(Dado information) {
        this.dado = information;
    }

    /**
     *
     * @return
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     *
     * @param connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     *
     * @return
     */
    public Thread getThread() {
        return thread;
    }

    /**
     *
     * @param thread
     */
    public void setThread(Thread thread) {
        this.thread = thread;
    }

    /**
     *
     * @return
     */
    public static Controller getController() {
        return controller;
    }

    /**
     *
     * @param controller
     */
    public static void setController(Controller controller) {
        Controller.controller = controller;
    }

    /**
     *
     * @return
     */
    public Board getBoard() {
        return board;
    }

    /**
     *
     * @param board
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     *
     * @return
     */
    public String getIP() {
        return this.connection.getIp().getHostAddress();
    }

    /**
     *
     * @return
     */
    public String getPort() {
        return Integer.toString(this.connection.getPort());
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
        return dado.getDado();
    }

    /**
     *
     * @param dado
     */
    public void setDado(int dado) {
        this.dado.setDado(dado);
    }

    /**
     *
     */
    public void desistir() {
        board.resetGame();
    }
}
