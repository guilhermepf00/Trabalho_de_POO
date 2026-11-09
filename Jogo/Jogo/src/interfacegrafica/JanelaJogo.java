
package interfacegrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.*;
import model.Comum;

import java.util.Random;

public class JanelaJogo extends JPanel{
    private static final int LINHAS = 21;
    private static final int COLUNAS = 21;
    private static final int CELULA = 24;
    private final int[][] mapa = new int[LINHAS][COLUNAS];
    private int jogadorLinha = 1;
    private int jogadorColuna = 1;
    
    // adicionando inimigo
    private Comum inimigo = new Comum (2,5);
    
    public JanelaJogo() {
        int pilarExtra = 20;
        Random gerador = new Random();
        setPreferredSize (new Dimension (COLUNAS * CELULA, LINHAS * CELULA ));
        for (int i = 0; i < LINHAS; i++){
            for (int c = 0; c < COLUNAS; c++){
                boolean borda = i == 0 || c == 0 || i == LINHAS -1 || c == COLUNAS -1;
                boolean pilar = i % 2 == 0 && c % 2 == 0;
                int chance = gerador.nextInt(101);
                int chanceBox = gerador.nextInt(101);
                boolean random = gerador.nextBoolean();
                if (chance > 90 && !borda){
                    if (random == true && pilarExtra > 0){
                        if (i != 0 || c != 0 || i != LINHAS -1 || c != COLUNAS -1){
                            mapa[i][c] = (borda || pilar || random) ? 1 : 0;
                            pilarExtra--;
                        }
                    }
                } else if (chanceBox > 50 && !borda && !pilar){
                    boolean boxSeed = gerador.nextBoolean();
                    if (i != 0 || c != 0 || i != LINHAS -1 || c != COLUNAS -1 ){
                        mapa[i][c] = (boxSeed) ? 2 : 0;
                    }
                }else{
                    mapa[i][c] = (borda || pilar) ? 1 : 0;
                }
            }
        }
        vincular("UP", -1, 0);
        vincular("DOWN", 1, 0);
        vincular("LEFT", 0, -1);
        vincular("RIGHT", 0, 1);
        
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < LINHAS; i++){
            for (int c = 0; c < COLUNAS; c++){
                int x = c * CELULA;
                int y = i * CELULA;
                
                g.setColor(mapa[i][c] == 1 ? Color.DARK_GRAY : (mapa[i][c] == 2 ? Color.ORANGE : new Color(210, 235, 210)));
                
                g.fillRect(x,y, CELULA, CELULA);
                g.setColor(Color.GRAY);
                g.drawRect(x, y, CELULA, CELULA);
            }
        }
        g.setColor(Color.BLUE);
        g.fillOval(jogadorColuna * CELULA + 3, jogadorLinha * CELULA + 3, CELULA - 6, CELULA - 6);
        
        // inimigo
        g.setColor(Color.RED); // 
        g.fillOval(inimigo.getX() * CELULA + 3, inimigo.getY() * CELULA + 3, CELULA - 6, CELULA - 6);
    }
    
    private void mover(int di, int dc) {
        int novaLinha = jogadorLinha + di;
        int novaColuna = jogadorColuna + dc;
        if (novaLinha < 0 || novaLinha >= LINHAS || novaColuna < 0 || novaColuna >= COLUNAS) {
            return;
        }
        if (mapa[novaLinha][novaColuna] == 1) {
            return;
        }
        jogadorLinha = novaLinha;
        jogadorColuna = novaColuna;
        repaint();
        inimigo.movimento();
    }

    private void vincular(String tecla, int di, int dc) {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(tecla), tecla);
        getActionMap().put(tecla, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mover(di, dc);
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame janela = new JFrame("Tabuleiro Basico");
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setContentPane(new JanelaJogo());
            janela.pack();
            janela.setResizable(false);
            janela.setLocationRelativeTo(null);
            janela.setVisible(true);
        });
    }
}
