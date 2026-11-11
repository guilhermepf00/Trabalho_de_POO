package model;

import java.util.Random;
import interfacegrafica.JanelaJogo;

public class Rastreador extends Personagem implements Inimigo {
     private int x;
    private int y;
    private int velocidade = 1;
    private int larguraMapa = 19;
    private int alturaMapa = 19;
    private Random gerador = new Random();
    
    private JanelaJogo janela;
    
    public Rastreador ( int x, int y, JanelaJogo aThis) {
        super ( 1 );
        this.x = x;
        this.y = y;
        this.janela = aThis;
    }
    
    @Override
    public void movimento(){
        boolean moveu = false;
        int novaColuna = 0;
        int novaLinha = 0;
        
        int tentativas = 0;
        
        while(!moveu && tentativas < 50){
            tentativas++;
            boolean qualC = gerador.nextBoolean();
            int direcaoX = 0;
            int direcaoY = 0;
            if (qualC == true) { 
                // caso queira que fique parado: direcaoX = gerador.nextInt(3) - 1;
                direcaoX = gerador.nextBoolean() ? 1 : -1; //desative essa caso
            }else{
                // caso queira que fique parado: direcaoY = gerador.nextInt(3) - 1;
                direcaoY = gerador.nextBoolean() ? 1 : -1; //desative essa caso
            }
            
            //caso ficar parado seja uma opção
            /*if (direcaoX == 0 && direcaoY == 0){
                break;
            }*/
            
            novaColuna = x + (velocidade * direcaoX);
            novaLinha = y + (velocidade * direcaoY);
            
            if (janela.podeAndar(novaLinha, novaColuna)){
                this.x = novaColuna;
                this.y = novaLinha;
                moveu = true;
            }
        }
    }
    
    @Override
    public int getX (){
        return x;
    }
    
    @Override
    public int getY(){
        return y;
    }
        
}
