package model;

public class Sabotador extends Personagem implements Inimigo {
    private int x;
    private int y;
    private int velocidade = 1;
    public Sabotador ( int x, int y ) {
        super ( 1 );
        this.x = x;
        this.y = y;
    }
 
     @Override
    public void movimento(){
        x += velocidade;
        y += velocidade;
        
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