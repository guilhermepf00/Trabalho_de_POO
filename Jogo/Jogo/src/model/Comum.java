package model;

import java.util.Random;
import interfacegrafica.JanelaJogo;

public class Comum extends Personagem implements Inimigo {
    private int x;
    private int y;
    private int velocidade = 1;
    private int larguraMapa = 19;
    private int alturaMapa = 19;
    private Random gerador = new Random();
    
    private JanelaJogo janela;
    
    public Comum ( int x, int y, JanelaJogo janela ) {
        super ( 1 );
        this.x = x;
        this.y = y;
        this.janela = janela;
    }
     // movimentaçao aleatoria do inimigo comum 
    @Override
    public void movimento(){
        boolean moveu = false;
    int direcaoX = gerador.nextInt(3) - 1;
    int direcaoY = gerador.nextInt(3) - 1;
    
       if ( direcaoX >= 0 || direcaoX <= larguraMapa ){
             x += velocidade * direcaoX;
       }
       if ( direcaoY >= 0 || direcaoY <= alturaMapa ){
            y += velocidade * direcaoY;
    }
        
        int novaColuna = 0;
        int novaLinha = 0;
        
        
        while(!moveu){
            
            int direcaoX = 0;
            int direcaoY = 0;
        
            boolean qualC = gerador.nextBoolean();
            
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

