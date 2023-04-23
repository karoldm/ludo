package views;

import controllers.Controller;
import model.Peao;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Karol
 */
public class Board extends javax.swing.JFrame {

    private final Controller controller = Controller.getInstance();
    private final ButtonSquare[][] tabuleiro = new ButtonSquare[15][15];
    private boolean jogarDeNovo = false;
    private final ImageIcon[] dadoImages = new ImageIcon[6];

    /**
     * Creates new form NewJFrame
     */
    public Board() {
        initComponents();
        generateLudoBoard();
        initPawns();
        initDado();
        selecaoNumero.setVisible(false);
        jogarSelecionado.setVisible(false);
    }

    private void initDado() {
        try {
            Image img = ImageIO.read(new FileInputStream("src\\main\\java\\assets\\dado-2.png"));
            Image newImage = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            dadoImages[0] = new ImageIcon(newImage);

            img = ImageIO.read(new FileInputStream("src\\main\\java\\assets\\dado-2.png"));
            newImage = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            dadoImages[1] = new ImageIcon(newImage);

            img = ImageIO.read(new FileInputStream("src\\main\\java\\assets\\dado-3.png"));
            newImage = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            dadoImages[2] = new ImageIcon(newImage);

            img = ImageIO.read(new FileInputStream("src\\main\\java\\assets\\dado-4.png"));
            newImage = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            dadoImages[3] = new ImageIcon(newImage);

            img = ImageIO.read(new FileInputStream("src\\main\\java\\assets\\dado-5.png"));
            newImage = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            dadoImages[4] = new ImageIcon(newImage);

            img = ImageIO.read(new FileInputStream("src\\main\\java\\assets\\dado-6.png"));
            newImage = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            dadoImages[5] = new ImageIcon(newImage);

            dadoImage.setIcon(dadoImages[0]);

        } catch (IOException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Erro ao criar tabuleiro!", "Erro", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    private void initPawns() {
        // Iniciando peões azul
        tabuleiro[1][1].addPeao(controller.getJogador1().getPeao(0));
        tabuleiro[4][4].addPeao(controller.getJogador1().getPeao(1));
        tabuleiro[4][1].addPeao(controller.getJogador1().getPeao(2));
        tabuleiro[1][4].addPeao(controller.getJogador1().getPeao(3));

        // Iniciando peões verde
        tabuleiro[10][10].addPeao(controller.getJogador2().getPeao(0));
        tabuleiro[13][13].addPeao(controller.getJogador2().getPeao(1));
        tabuleiro[13][10].addPeao(controller.getJogador2().getPeao(2));
        tabuleiro[10][13].addPeao(controller.getJogador2().getPeao(3));

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
                if (i > 0 && j > 0 && i < 5 && j < 5) {
                    tabuleiro[i][j].setBackground(Color.WHITE);
                } else {
                    tabuleiro[i][j].setBackground(Color.RED);
                }
                tabuleiro[i][j].setBorder(null);
            }
        }
        for (int i = 9; i < 15; i++) {
            for (int j = 0; j < 6; j++) {
                if (i > 9 && j > 0 && i < 14 && j < 5) {
                    tabuleiro[i][j].setBackground(Color.WHITE);
                } else {
                    tabuleiro[i][j].setBackground(Color.GREEN);
                }
                tabuleiro[i][j].setBorder(null);
            }
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 9; j < 15; j++) {
                if (i > 0 && j > 9 && i < 5 && j < 14) {
                    tabuleiro[i][j].setBackground(Color.WHITE);
                } else {
                    tabuleiro[i][j].setBackground(Color.BLUE);
                }
                tabuleiro[i][j].setBorder(null);
            }
        }
        for (int i = 9; i < 15; i++) {
            for (int j = 9; j < 15; j++) {
                if (i > 9 && j > 9 && i < 14 && j < 14) {
                    tabuleiro[i][j].setBackground(Color.WHITE);
                } else {
                    tabuleiro[i][j].setBackground(Color.YELLOW);
                }
                tabuleiro[i][j].setBorder(null);
            }
        }

        // Desenhando trilhas
        tabuleiro[8][1].setBackground(Color.GREEN);
        tabuleiro[7][1].setBackground(Color.GREEN);

        for (int i = 1; i < 7; i++) {
            tabuleiro[7][i].setBackground(Color.GREEN);
        }

        tabuleiro[6][13].setBackground(Color.BLUE);
        tabuleiro[7][13].setBackground(Color.BLUE);

        for (int i = 8; i < 13; i++) {
            tabuleiro[7][i].setBackground(Color.BLUE);
        }

        tabuleiro[1][6].setBackground(Color.RED);
        tabuleiro[1][7].setBackground(Color.RED);

        for (int i = 2; i < 7; i++) {
            tabuleiro[i][7].setBackground(Color.RED);
        }

        tabuleiro[13][8].setBackground(Color.YELLOW);
        tabuleiro[13][7].setBackground(Color.YELLOW);

        for (int i = 8; i < 13; i++) {
            tabuleiro[i][7].setBackground(Color.YELLOW);
        }

        try {
            Image img = ImageIO.read(new FileInputStream("src\\main\\java\\assets\\center.png"));
            Image newImage = img.getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            tabuleiro[7][7].setIcon(new ImageIcon(newImage));
            tabuleiro[7][7].setBorder(null);

            img = ImageIO.read(new FileInputStream("src\\main\\java\\assets\\arrow-red.png"));
            newImage = img.getScaledInstance(45, 40, Image.SCALE_DEFAULT);
            tabuleiro[0][7].setIcon(new ImageIcon(newImage));

            img = ImageIO.read(new FileInputStream("src\\main\\java\\assets\\arrow-blue.png"));
            newImage = img.getScaledInstance(45, 40, Image.SCALE_DEFAULT);
            tabuleiro[7][14].setIcon(new ImageIcon(newImage));

            img = ImageIO.read(new FileInputStream("src\\main\\java\\assets\\arrow-green.png"));
            newImage = img.getScaledInstance(45, 40, Image.SCALE_DEFAULT);
            tabuleiro[7][0].setIcon(new ImageIcon(newImage));

            img = ImageIO.read(new FileInputStream("src\\main\\java\\assets\\arrow-yellow.png"));
            newImage = img.getScaledInstance(45, 40, Image.SCALE_DEFAULT);
            tabuleiro[14][7].setIcon(new ImageIcon(newImage));

            img = ImageIO.read(new FileInputStream("src\\main\\java\\assets\\star.png"));
            newImage = img.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            tabuleiro[6][2].setIcon(new ImageIcon(newImage));
            tabuleiro[2][8].setIcon(new ImageIcon(newImage));
            tabuleiro[12][6].setIcon(new ImageIcon(newImage));
            tabuleiro[8][12].setIcon(new ImageIcon(newImage));

        } catch (IOException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Erro ao criar tabuleiro!", "Erro", JOptionPane.ERROR_MESSAGE);
            this.dispose();
        }
    }

    private void moverPeao(ButtonSquare square) {
        if (controller.getDado() == 0) {
            return;
        }
        //recuperando o peão do quadrado clicado
        Peao peao = square.getPeao();
        //peao != null verifica se o jogador não clicou numa casa vazia
        //controlador.jogadaPermitida verifica se o jogador não tentou mover um peão inimigo
        if (peao != null && controller.jogadaPermitida(peao.getCor())) {
            if (peao.getPosicao() == 57) {
                System.out.println("Peao ja chegou na zona final");
                return;
            }
            if (peao.getPosicao() == 0) {
                if (controller.getDado() != 6) {
                    System.out.println("So pode mover esse peão se tirar 6");
                    return;
                } else {
                    controller.setDado(1);
                }
            }
            //recuperando posição do peao no tabuleiro
            controller.checarPosicao(peao);
            int[] posicoes = controller.getPosicaoMap(peao.getPosicao());
            int i = posicoes[0];
            int j = posicoes[1];
            //recuperando possíveis peões na nova posição
            ArrayList<Peao> currPeoes = tabuleiro[i][j].getPeoes();
            for (Peao p : currPeoes) {
                //Se existe um peão na nova posição e ele é do inimigo, movemos ele para a casa inicial
                if (p.getCor() != peao.getCor()) {
                    controller.move(p, 0);
                    controller.proximoJogador();
                    int[] posicoesInciais = controller.getPosicaoInicialDisponivel(this.tabuleiro);
                    int m = posicoesInciais[0];
                    int n = posicoesInciais[1];
                    tabuleiro[m][n].addPeao(p);
                    tabuleiro[i][j].removePeao(p);
                    controller.proximoJogador();
                    break;
                }
            }
            tabuleiro[i][j].addPeao(peao);
            square.removePeao(peao);
            controller.setDado(0);
            buttonJogarDado.setEnabled(true);
            jogarSelecionado.setEnabled(true);
            System.out.println(peao.toString() + " - " + peao.getPosicao());
            if (jogarDeNovo) {
                jogarDeNovo = false;
            } else {
                controller.proximoJogador();
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
        dadoImage = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuJogar = new javax.swing.JMenu();
        menuSerHost = new javax.swing.JMenuItem();
        menuConectar = new javax.swing.JMenuItem();
        menuDesconectar = new javax.swing.JMenuItem();
        menuDebug = new javax.swing.JCheckBoxMenuItem();
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

        dadoImage.setBackground(new java.awt.Color(0, 0, 0));
        dadoImage.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        dadoImage.setFocusCycleRoot(true);
        dadoImage.setMaximumSize(new java.awt.Dimension(40, 40));
        dadoImage.setMinimumSize(new java.awt.Dimension(40, 40));
        dadoImage.setPreferredSize(new java.awt.Dimension(40, 40));

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

        menuDesconectar.setText("Desconectar");
        menuDesconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDesconectarActionPerformed(evt);
            }
        });
        menuJogar.add(menuDesconectar);

        menuDebug.setText("Debug");
        menuDebug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDebugActionPerformed(evt);
            }
        });
        menuJogar.add(menuDebug);

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
                    .addComponent(selecaoNumero)
                    .addComponent(jogarSelecionado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonJogarDado, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dadoImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollPaneJogadas, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dadoImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonJogarDado, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
        controller.jogarDado();
        textJogadas.setText(textJogadas.getText() + "\nJogador " + controller.getJogadorAtual().toString() + ": " + controller.getDado());
        dadoImage.setIcon(dadoImages[controller.getDado() - 1]);
//        if (controlador.ismyTurn()) {
        if (controller.getJogadorAtual().todosOsPeoesNoInicioOuFim()) {
            if (controller.getDado() == 6) {
                jogarDeNovo = true;
                buttonJogarDado.setEnabled(false);
            } else {
                controller.setDado(0);
                controller.proximoJogador();
            }
        } else {
            if (controller.getDado() == 6) {
                jogarDeNovo = true;
            }
            buttonJogarDado.setEnabled(false);
//            }
        }
    }//GEN-LAST:event_buttonJogarDadoActionPerformed

    private void jogarSelecionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jogarSelecionadoActionPerformed
        // TODO add your handling code here:
        controller.jogarDado((int) selecaoNumero.getValue());
        textJogadas.setText(textJogadas.getText() + "\nJogador " + controller.getJogadorAtual().toString() + ": " + controller.getDado());
        controller.proximoJogador();
        dadoImage.setIcon(dadoImages[controller.getDado() - 1]);

        if (controller.getJogadorAtual().todosOsPeoesNoInicioOuFim()) {
            if (controller.getDado() == 6) {
                this.jogarDeNovo = true;
                buttonJogarDado.setEnabled(false);
            } else {
                controller.setDado(0);
                controller.proximoJogador();
            }
        } else {
            if (controller.getDado() == 6) {
                jogarDeNovo = true;
            }
            buttonJogarDado.setEnabled(false);
            buttonJogarDado.setEnabled(false);
            jogarSelecionado.setEnabled(false);
        }

    }//GEN-LAST:event_jogarSelecionadoActionPerformed

    private void menuDebugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDebugActionPerformed
        // TODO add your handling code here:
        if (menuDebug.isSelected()) {
            jogarSelecionado.setVisible(true);
            selecaoNumero.setVisible(true);
        } else {
            jogarSelecionado.setVisible(false);
            selecaoNumero.setVisible(false);
        }
    }//GEN-LAST:event_menuDebugActionPerformed

    private void menuDesconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDesconectarActionPerformed
        // TODO add your handling code here:
        controller.cancel();
    }//GEN-LAST:event_menuDesconectarActionPerformed

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
    private javax.swing.JPanel boardGame;
    private javax.swing.JButton buttonJogarDado;
    private javax.swing.JButton dadoImage;
    private javax.swing.JButton jogarSelecionado;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem menuConectar;
    private javax.swing.JCheckBoxMenuItem menuDebug;
    private javax.swing.JMenuItem menuDesconectar;
    private javax.swing.JMenu menuJogar;
    private javax.swing.JMenu menuRegras;
    private javax.swing.JMenuItem menuSerHost;
    private javax.swing.JMenuItem menuVerRegras;
    private javax.swing.JScrollPane scrollPaneJogadas;
    private javax.swing.JSpinner selecaoNumero;
    private javax.swing.JTextArea textJogadas;
    // End of variables declaration//GEN-END:variables
}
