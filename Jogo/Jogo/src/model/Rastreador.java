package model;
import java.util.Random;

public class Rastreador extends Personagem implements Inimigo {
    private int x;
    private int y;
    private int velocidade = 1;
    private int larguraMapa = 18;
    private int alturaMapa = 18;
    private Random gerador = new Random();
    public Rastreador ( int x, int y ) {
        super ( 1 );
        this.x = x;
        this.y = y;
    }

 // movimentaçao aleatoria do inimigo Rastreador "por enquanto" 
    @Override
    public void movimento(){
    int direcaoX = gerador.nextInt(3) - 1;
    int direcaoY = gerador.nextInt(3) - 1;
    
       if ( direcaoX >= 0 || direcaoX <= larguraMapa ){
             x += velocidade * direcaoX;
       }
       if ( direcaoY >= 0 || direcaoY <= alturaMapa ){
            y += velocidade * direcaoY;
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
