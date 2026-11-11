package interfacegrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.swing.AbstractAction;
import javax.swing.*;
import model.Comum;
import model.Rastreador;
import model.Sabotador;
import model.Principal;
import model.Bomba;

import java.util.Random;

public class JanelaJogo extends JPanel{
    private static final int LINHAS = 21;
    private static final int COLUNAS = 21;
    private static final int CELULA = 24;
    private final int[][] mapa = new int[LINHAS][COLUNAS];
    private int jogadorLinha = 1;
    private int jogadorColuna = 1;
    
    private Principal jogador = new Principal();
    
    private Comum inimigoC;
    private Rastreador inimigoR;
    private Sabotador inimigoS;
    
    Set <Bomba> bomba = new LinkedHashSet();
    private int bombasAtivas = 0;
    
    public JanelaJogo() {
        inimigoC = new Comum(1, 19, this);
        inimigoR = new Rastreador(19, 1, this);
        inimigoS = new Sabotador(19, 19, this);
        
        gerarMapa();
        
        Timer cooldownComumC = new Timer(1000, e -> {
            inimigoC.movimento();
            repaint();
            
        });
        Timer cooldownComumR = new Timer(1000, e -> {
            inimigoR.movimento();
            repaint();
        });
        
        Timer cooldownComumS = new Timer(1000, e -> {
            inimigoS.movimento();
            repaint();
        });
        
        cooldownComumC.start();
        cooldownComumR.start();
        cooldownComumS.start();
        
        vincular("UP", -1, 0);
        vincular("DOWN", 1, 0);
        vincular("LEFT", 0, -1);
        vincular("RIGHT", 0, 1);
        vincular("SPACE", 0, 0);
    }
    
    public void gerarMapa(){
        
        int pilarExtra = 20;
        Random gerador = new Random();
        setPreferredSize (new Dimension (COLUNAS * CELULA, LINHAS * CELULA ));
        for (int i = 0; i < LINHAS; i++){
            for (int c = 0; c < COLUNAS; c++){
                boolean borda = i == 0 || c == 0 || i == LINHAS -1 || c == COLUNAS -1;
                boolean pilar = i % 2 == 0 && c % 2 == 0;
                boolean random = gerador.nextBoolean();
                int chance = gerador.nextInt(101);
                int chanceBox = gerador.nextInt(101);
                boolean canBox = i > 2 && c > 2 && chanceBox > 60;
                if (chance > 90 && random == true && pilarExtra > 0){
                    if (i != 0 || c != 0 || i != LINHAS -1 || c != COLUNAS -1){
                        mapa[i][c] = (borda || pilar || random) ? 1 : 0;
                        pilarExtra--;
                    }
                } else {  
                    mapa[i][c] = (borda || pilar) ? 1 : (canBox ? 2 : 0);
                }
                if (!borda){
                    if (i == 1 || c == 1 || i == 19 || c == 19){
                        mapa[i][c] = 0;
                    }
                }
                if (i == 1 && c == 3 || i == 3 && c == 1){
                    mapa[i][c] = 2;
                }
            }
        }
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
        
        // Bomba
        g.setColor(Color.YELLOW);
        for( Bomba b : bomba )
            g.fillOval(b.getColuna() * CELULA + 3, b.getLinha() * CELULA + 3, CELULA - 6, CELULA - 6);
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
        
    }

    private void vincular(String tecla, int di, int dc) {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(tecla), tecla);
        getActionMap().put(tecla, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tecla.equals("SPACE")){
                    plantarBomba();
                } else {
                    mover(di, dc);
                }
            }
        });
    }
    
    public void plantarBomba(){
        if( bombasAtivas == jogador.getQuantidadeBombasSimultâneas() ){
            return;
        }
        
        bombasAtivas++;
        
        Bomba novaBomba = new Bomba( jogadorLinha, jogadorColuna);
        bomba.add(novaBomba);
        repaint();
        
        Timer explosão = new Timer(5000, e ->{
        int linhaInicio = novaBomba.getLinha() - 1 * jogador.getRaioDeExplosão();
        int linhaFim = novaBomba.getLinha() + 1 * jogador.getRaioDeExplosão();

        int colunaInicio = novaBomba.getColuna() - 1 * jogador.getRaioDeExplosão();
        int colunaFim = novaBomba.getColuna() + 1 * jogador.getRaioDeExplosão();
        
        for (int i = linhaInicio; i <= linhaFim; i++){
            for (int j = colunaInicio; j <= colunaFim; j++){
                if (i >= 0 && i < LINHAS && j >= 0 && j < COLUNAS) {
                    if (mapa[i][j] == 2){
                        mapa[i][j] = 0;
                    }
                }
            }
        }
        
        bomba.remove(novaBomba);
        bombasAtivas--;
        
        repaint(); // Atualiza a tela imediatamente após limpar os blocos ao redor
        });
        
        explosão.start();
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