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
import model.Rastreador;
import model.Sabotador;

import java.util.Random;

public class JanelaJogo extends JPanel{
    private static final int LINHAS = 21;
    private static final int COLUNAS = 21;
    private static final int CELULA = 24;
    private final int[][] mapa = new int[LINHAS][COLUNAS];
    private int jogadorLinha = 1;
    private int jogadorColuna = 1;
    
    private Comum inimigoC;
    private final Rastreador inimigoR;
    private final Sabotador inimigoS;
    
    public JanelaJogo() {
        inimigoC = new Comum(2, 5, this);
        inimigoR = new Rastreador(5, 2, this);
        inimigoS = new Sabotador(4, 3, this);
        
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
                if (chance > 90 && !borda && i > 2 && c > 2){
                    if (random == true && pilarExtra > 0){
                        if (i != 0 || c != 0 || i != LINHAS -1 || c != COLUNAS -1){
                            mapa[i][c] = (borda || pilar || random) ? 1 : 0;
                            pilarExtra--;
                        }
                    }
                } else if (chanceBox > 50 && !borda && !pilar && i > 2 && c > 2){
                    boolean boxSeed = gerador.nextBoolean();
                    if (i != 0 || c != 0 || i != LINHAS -1 || c != COLUNAS -1 ){
                        mapa[i][c] = (boxSeed) ? 2 : 0;
                    }
                }else{
                    mapa[i][c] = (borda || pilar) ? 1 : 0;
                }
            }
        }
        
        Timer cooldownComum = new Timer(1000, e -> {
            inimigoC.movimento();
            repaint();
        });
        
        cooldownComum.start();
        
        vincular("UP", -1, 0);
        vincular("DOWN", 1, 0);
        vincular("LEFT", 0, -1);
        vincular("RIGHT", 0, 1);
        vincular("SPACE", 0, 0);
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
        
        // inimigo Comum
        g.setColor(Color.RED); 
        g.fillOval(inimigoC.getX() * CELULA + 3, inimigoC.getY() * CELULA + 3, CELULA - 6, CELULA - 6);
        
        // inimigo Rastreador
        g.setColor(Color.BLACK);
        g.fillOval(inimigoR.getX() * CELULA + 3, inimigoR.getY() * CELULA + 3, CELULA - 6, CELULA - 6);
        
        // inimigo Sabotador
        g.setColor(Color.MAGENTA);
        g.fillOval(inimigoS.getX() * CELULA + 3, inimigoS.getY() * CELULA + 3, CELULA - 6, CELULA - 6);
    }
    
    private void mover(int di, int dc) {
        int novaLinha = jogadorLinha + di;
        int novaColuna = jogadorColuna + dc;
        if (novaLinha < 0 || novaLinha >= LINHAS || novaColuna < 0 || novaColuna >= COLUNAS) {
            return;
        }
        if (mapa[novaLinha][novaColuna] == 1 || mapa[novaLinha][novaColuna] == 2) {
            return;
        }
        jogadorLinha = novaLinha;
        jogadorColuna = novaColuna;
        repaint();
        
        inimigoC.movimento();
        inimigoR.movimento();
        inimigoS.movimento();
    }

    private void vincular(String tecla, int di, int dc) {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(tecla), tecla);
        getActionMap().put(tecla, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tecla.equals("SPACE")){
                    limpar();
                } else {
                    mover(di, dc);
                }
            }
        });
    }
    
    public void limpar(){
        int linhaInicio = jogadorLinha - 1;
        int linhaFim = jogadorLinha + 1;

        int colunaInicio = jogadorColuna - 1;
        int colunaFim = jogadorColuna + 1;
        
        for (int i = linhaInicio; i <= linhaFim; i++){
            for (int j = colunaInicio; j <= colunaFim; j++){
                if (i >= 0 && i < LINHAS && j >= 0 && j < COLUNAS) {
                    if (mapa[i][j] == 2){
                        mapa[i][j] = 0;
                    }
                }
            }
        }
        repaint(); // Atualiza a tela imediatamente após limpar os blocos ao redor
    }
    
    public boolean podeAndar(int linha, int coluna) {
        if (linha < 0 || linha >= LINHAS || coluna < 0 || coluna >= COLUNAS){
            return false;
        }
        return mapa[linha][coluna] != 1;
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