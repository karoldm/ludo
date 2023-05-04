package views;

import controllers.ControladorJogo;
import model.Peao;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Karol
 */
public class Board extends javax.swing.JFrame {

    private final ControladorJogo controlador = new ControladorJogo();
    private final ButtonSquare[][] tabuleiro = new ButtonSquare[15][15];
    private boolean jogarDeNovo = false;

    /**
     * Creates new form NewJFrame
     */
    public Board() {
        initComponents();
        generateLudoBoard();
        initPawns();
    }

    private void initPawns() {
        // Iniciando peões azul
        tabuleiro[1][1].setBackground(Color.WHITE);
        tabuleiro[1][1].addPeao(controlador.getJogador1().getPeao(0));

        tabuleiro[4][4].setBackground(Color.WHITE);
        tabuleiro[4][4].addPeao(controlador.getJogador1().getPeao(1));

        tabuleiro[4][1].setBackground(Color.WHITE);
        tabuleiro[4][1].addPeao(controlador.getJogador1().getPeao(2));

        tabuleiro[1][4].setBackground(Color.WHITE);
        tabuleiro[1][4].addPeao(controlador.getJogador1().getPeao(3));

        // Iniciando peões verde
        tabuleiro[10][10].setBackground(Color.WHITE);
        tabuleiro[10][10].addPeao(controlador.getJogador2().getPeao(0));

        tabuleiro[13][13].setBackground(Color.WHITE);
        tabuleiro[13][13].addPeao(controlador.getJogador2().getPeao(1));

        tabuleiro[13][10].setBackground(Color.WHITE);
        tabuleiro[13][10].addPeao(controlador.getJogador2().getPeao(2));

        tabuleiro[10][13].setBackground(Color.WHITE);
        tabuleiro[10][13].addPeao(controlador.getJogador2().getPeao(3));

        // Iniciando peões vermelho
        tabuleiro[1][13].setBackground(Color.WHITE);

        tabuleiro[4][13].setBackground(Color.WHITE);

        tabuleiro[4][10].setBackground(Color.WHITE);

        tabuleiro[1][10].setBackground(Color.WHITE);

        // Iniciando peões amarelo
        tabuleiro[13][1].setBackground(Color.WHITE);

        tabuleiro[13][4].setBackground(Color.WHITE);

        tabuleiro[10][1].setBackground(Color.WHITE);

        tabuleiro[10][4].setBackground(Color.WHITE);
    }

    private void generateLudoBoard() {
        boardGame.setSize(600, 600);
        boardGame.setLayout(new GridLayout(15, 15));

        // Criando tabuleiro 15x15
        // O grid layout do java cria uma tabela da seguinte forma:
        // |2,0  2,1  2,2|
        // |1,0  1,1  1,2|
        // |0,0  0,1  0,2|
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                ButtonSquare squareButton = new ButtonSquare();
                squareButton.addActionListener((ActionEvent e) -> {
                    moverPeao(squareButton);
                });
                boardGame.add(squareButton, i, j);
                tabuleiro[i][j] = squareButton;
            }
        }

        // Criando os quatro quadrados coloridos dos cantos
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                tabuleiro[i][j].setBackground(Color.BLUE);
            }
        }
        for (int i = 9; i < 15; i++) {
            for (int j = 0; j < 6; j++) {
                tabuleiro[i][j].setBackground(Color.YELLOW);
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 9; j < 15; j++) {
                tabuleiro[i][j].setBackground(Color.RED);
            }
        }
        for (int i = 9; i < 15; i++) {
            for (int j = 9; j < 15; j++) {
                tabuleiro[i][j].setBackground(Color.GREEN);
            }
        }

        // Desenhando trilhas
        tabuleiro[8][1].setBackground(Color.YELLOW);
        tabuleiro[7][1].setBackground(Color.YELLOW);

        for (int i = 1; i < 6; i++) {
            tabuleiro[7][i].setBackground(Color.YELLOW);
        }

        tabuleiro[6][13].setBackground(Color.RED);
        tabuleiro[7][13].setBackground(Color.RED);

        for (int i = 9; i < 13; i++) {
            tabuleiro[7][i].setBackground(Color.RED);
        }

        tabuleiro[1][6].setBackground(Color.BLUE);
        tabuleiro[1][7].setBackground(Color.BLUE);

        for (int i = 2; i < 6; i++) {
            tabuleiro[i][7].setBackground(Color.BLUE);
        }

        tabuleiro[13][8].setBackground(Color.GREEN);
        tabuleiro[13][7].setBackground(Color.GREEN);

        for (int i = 9; i < 13; i++) {
            tabuleiro[i][7].setBackground(Color.GREEN);
        }

        // Desenhando quadrado central (chegada)
        for (int i = 6; i < 9; i++) {
            for (int j = 6; j < 9; j++) {
                tabuleiro[i][j].setBackground(Color.BLACK);
            }
        }

        try {
            Image img = ImageIO.read(new FileInputStream("src\\main\\java\\assets\\goal.png"));
            Image newImage = img.getScaledInstance(30, 25, Image.SCALE_DEFAULT);
            tabuleiro[7][7].setIcon(new ImageIcon(newImage));
            tabuleiro[7][7].setDisabledIcon(new ImageIcon(newImage));
        } catch (IOException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Erro ao criar tabuleiro!", "Erro", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    private void moverPeao(ButtonSquare square) {
        if (controlador.getDado() == 0) {
            return;
        }

        //recuperando o peão do quadrado clicado
        Peao peao = square.getPeao();

        //peao != null verifica se o jogador não clicou numa casa vazia 
        //controlador.jogadaPermitida verifica se o jogador não tentou mover um peão inimigo
        if (peao != null && controlador.jogadaPermitida(peao.getCor())) {

            if (peao.getPosicao() == 57) {
                System.out.println("Peao ja chegou na zona final");
                return;
            }
            if (peao.getPosicao() == 0) {
                if (controlador.getDado() != 6) {
                    System.out.println("So pode mover esse peão se tirar 6");
                    return;
                } else {
                    controlador.setDado(1);
                }
            }

            //recuperando posição do peao no tabuleiro
            controlador.checarPosicao(peao);
            int[] posicoes = controlador.getPosicaoMap(peao.getPosicao());
            int i = posicoes[0];
            int j = posicoes[1];
            //recuperando possíveis peões na nova posição
            ArrayList<Peao> currPeoes = tabuleiro[i][j].getPeoes();

            for (Peao p : currPeoes) {
                //Se existe um peão na nova posição e ele é do inimigo, movemos ele para a casa inicial
                if (p.getCor() != peao.getCor()) {
                    controlador.move(p, 0);
                    controlador.proximoJogador();
                    int[] posicoesInciais = controlador.getPosicaoInicialDisponivel(this.tabuleiro);
                    int m = posicoesInciais[0];
                    int n = posicoesInciais[1];
                    tabuleiro[m][n].addPeao(p);
                    tabuleiro[i][j].removePeao(p);
                    controlador.proximoJogador();
                    break;
                }
            }

            tabuleiro[i][j].addPeao(peao);
            square.removePeao(peao);

            controlador.setDado(0);
            buttonJogarDado.setEnabled(true);
            jogarSelecionado.setEnabled(true);
            System.out.println(peao.toString() + " - " + peao.getPosicao());

            if (jogarDeNovo) {
                jogarDeNovo = false;
            } else {
                controlador.proximoJogador();
            }

        } else {
            System.out.println("Jogada nao permitida");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boardGame = new javax.swing.JPanel();
        buttonJogarDado = new javax.swing.JButton();
        scrollPaneJogadas = new javax.swing.JScrollPane();
        textJogadas = new javax.swing.JTextArea();
        selecaoNumero = new javax.swing.JSpinner();
        jogarSelecionado = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuJogar = new javax.swing.JMenu();
        menuSerHost = new javax.swing.JMenuItem();
        menuConectar = new javax.swing.JMenuItem();
        debug = new javax.swing.JCheckBoxMenuItem();
        Desistir = new javax.swing.JMenuItem();
        menuRegras = new javax.swing.JMenu();
        menuVerRegras = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ludo");
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setName("frameGame"); // NOI18N
        setResizable(false);

        boardGame.setBackground(new java.awt.Color(255, 255, 255));
        boardGame.setMaximumSize(new java.awt.Dimension(600, 580));
        boardGame.setMinimumSize(new java.awt.Dimension(600, 580));
        boardGame.setPreferredSize(new java.awt.Dimension(600, 580));

        javax.swing.GroupLayout boardGameLayout = new javax.swing.GroupLayout(boardGame);
        boardGame.setLayout(boardGameLayout);
        boardGameLayout.setHorizontalGroup(
            boardGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        boardGameLayout.setVerticalGroup(
            boardGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        buttonJogarDado.setBackground(new java.awt.Color(153, 153, 255));
        buttonJogarDado.setForeground(new java.awt.Color(255, 255, 255));
        buttonJogarDado.setText("Jogar dado");
        buttonJogarDado.setMaximumSize(new java.awt.Dimension(300, 60));
        buttonJogarDado.setMinimumSize(new java.awt.Dimension(300, 60));
        buttonJogarDado.setPreferredSize(new java.awt.Dimension(300, 60));
        buttonJogarDado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonJogarDadoActionPerformed(evt);
            }
        });

        scrollPaneJogadas.setBackground(new java.awt.Color(255, 255, 255));
        scrollPaneJogadas.setMaximumSize(new java.awt.Dimension(300, 540));
        scrollPaneJogadas.setMinimumSize(new java.awt.Dimension(300, 540));
        scrollPaneJogadas.setPreferredSize(new java.awt.Dimension(300, 540));

        textJogadas.setEditable(false);
        textJogadas.setColumns(20);
        textJogadas.setRows(5);
        scrollPaneJogadas.setViewportView(textJogadas);

        jogarSelecionado.setText("Jogar número selecionado");
        jogarSelecionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogarSelecionadoActionPerformed(evt);
            }
        });

        menuBar.setPreferredSize(new java.awt.Dimension(95, 20));

        menuJogar.setText("Jogar");

        menuSerHost.setText("Ser host");
        menuSerHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSerHostActionPerformed(evt);
            }
        });
        menuJogar.add(menuSerHost);

        menuConectar.setText("Conectar");
        menuConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuConectarActionPerformed(evt);
            }
        });
        menuJogar.add(menuConectar);

        debug.setSelected(true);
        debug.setText("Debug");
        debug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugActionPerformed(evt);
            }
        });
        menuJogar.add(debug);

        Desistir.setText("Desistir");
        Desistir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesistirActionPerformed(evt);
            }
        });
        menuJogar.add(Desistir);

        menuBar.add(menuJogar);

        menuRegras.setText("Regras");

        menuVerRegras.setText("Ver regras");
        menuVerRegras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVerRegrasActionPerformed(evt);
            }
        });
        menuRegras.add(menuVerRegras);

        menuBar.add(menuRegras);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(boardGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneJogadas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonJogarDado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(selecaoNumero)
                    .addComponent(jogarSelecionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPaneJogadas, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonJogarDado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selecaoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jogarSelecionado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 32, Short.MAX_VALUE))
                    .addComponent(boardGame, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuSerHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSerHostActionPerformed
        Host hostIP = new Host();
        hostIP.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        hostIP.setVisible(true);
    }//GEN-LAST:event_menuSerHostActionPerformed

    private void menuConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuConectarActionPerformed
        // TODO add your handling code here:
        Connect connect = new Connect();
        connect.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        connect.setVisible(true);
    }//GEN-LAST:event_menuConectarActionPerformed

    private void menuVerRegrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVerRegrasActionPerformed
        Rules rules = new Rules();
        rules.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        rules.setVisible(true);
    }//GEN-LAST:event_menuVerRegrasActionPerformed

    private void buttonJogarDadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonJogarDadoActionPerformed
        controlador.jogarDado();
        textJogadas.setText(textJogadas.getText() + "\nJogador " + controlador.getJogadorAtual().toString() + ": " + controlador.getDado());

        if (controlador.getJogadorAtual().todosOsPeoesNoInicioOuFim()) {
            if (controlador.getDado() == 6) {
                this.jogarDeNovo = true;
                buttonJogarDado.setEnabled(false);
            } else {
                controlador.setDado(0);
                controlador.proximoJogador();
            }
        } else {
            if (controlador.getDado() == 6) {
                jogarDeNovo = true;
            }
            buttonJogarDado.setEnabled(false);
        }
    }//GEN-LAST:event_buttonJogarDadoActionPerformed

    private void jogarSelecionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jogarSelecionadoActionPerformed
        // TODO add your handling code here:
        controlador.jogarDado((int) selecaoNumero.getValue());
        textJogadas.setText(textJogadas.getText() + "\nJogador " + controlador.getJogadorAtual().toString() + ": " + controlador.getDado());
        controlador.proximoJogador();

        if (controlador.getJogadorAtual().todosOsPeoesNoInicioOuFim()) {
            if (controlador.getDado() == 6) {
                this.jogarDeNovo = true;
                buttonJogarDado.setEnabled(false);
            } else {
                controlador.setDado(0);
                controlador.proximoJogador();
            }
        } else {
            if (controlador.getDado() == 6) {
                jogarDeNovo = true;
            }
            buttonJogarDado.setEnabled(false);
            buttonJogarDado.setEnabled(false);
            jogarSelecionado.setEnabled(false);
        }

    }//GEN-LAST:event_jogarSelecionadoActionPerformed

    private void debugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugActionPerformed
        // TODO add your handling code here:
        if (debug.isSelected()) {
            jogarSelecionado.setVisible(true);
            selecaoNumero.setVisible(true);
        } else {
            jogarSelecionado.setVisible(false);
            selecaoNumero.setVisible(false);
        }
    }//GEN-LAST:event_debugActionPerformed

    private void DesistirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesistirActionPerformed
        controlador.desistir();
        //this.initPawns();
    }//GEN-LAST:event_DesistirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Board.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Board.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Board.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Board.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Board().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Desistir;
    private javax.swing.JPanel boardGame;
    private javax.swing.JButton buttonJogarDado;
    private javax.swing.JCheckBoxMenuItem debug;
    private javax.swing.JButton jogarSelecionado;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuConectar;
    private javax.swing.JMenu menuJogar;
    private javax.swing.JMenu menuRegras;
    private javax.swing.JMenuItem menuSerHost;
    private javax.swing.JMenuItem menuVerRegras;
    private javax.swing.JScrollPane scrollPaneJogadas;
    private javax.swing.JSpinner selecaoNumero;
    private javax.swing.JTextArea textJogadas;
    // End of variables declaration//GEN-END:variables
}
