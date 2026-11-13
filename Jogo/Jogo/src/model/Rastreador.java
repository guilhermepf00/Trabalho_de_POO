package model;

import java.util.Random;
import interfacegrafica.JanelaJogo;

public class Rastreador extends Personagem implements Inimigo {
     private int x;
    private int y;
    private int velocidade;
    private Random gerador = new Random();
    
    private JanelaJogo janela;
    
    public Rastreador ( int x, int y, JanelaJogo aThis) {
        super ( 1 );
        this.x = x;
        this.y = y;
        this.janela = aThis;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
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
            velocidade = 1;
            
            int perseguindo = 0;
            // cordenada  do inimigo e do personagem
            int inimigoX = this.x;
            int inimigoY = this.y;
            int personagemY = janela.getJogadorLinha();
            int personagemX = janela.getJogadorColuna();
            
            // distancia de inimigo para jogador 
            int distanciaY = Math.abs( inimigoY - personagemY );
            int distanciaX = Math.abs( inimigoX - personagemX );
            
            if ( inimigoX == personagemX && distanciaY <= 5 && distanciaY > 0 ){
                
                velocidade = 2;
                perseguindo = 1;
                
                if ( inimigoY < personagemY ){
                    direcaoY = 1;
                }
                
                else if ( inimigoY > personagemY ){
                    direcaoY = -1;
                }
            }
                if ( inimigoY == personagemY && distanciaX <= 5 && distanciaX > 0 ){
                    
                    velocidade = 2;
                    
                    perseguindo = 1;

                  if ( inimigoX < personagemX ){
                    direcaoX = 1;
                }
                
                  else if ( inimigoX > personagemX ){
                    direcaoX = -1;
                }
                    
            }
                if ( perseguindo == 0){
            if (qualC == true) { 
                // caso queira que fique parado: direcaoX = gerador.nextInt(3) - 1;
                direcaoX = gerador.nextBoolean() ? 1 : -1; //desative essa caso
            }else{
                // caso queira que fique parado: direcaoY = gerador.nextInt(3) - 1;
                direcaoY = gerador.nextBoolean() ? 1 : -1; //desative essa caso
            }
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
