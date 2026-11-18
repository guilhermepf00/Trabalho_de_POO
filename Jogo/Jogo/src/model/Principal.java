package model;
import javax.swing.Timer;

public class Principal extends Personagem {
    private int quantidadeBombasSimultâneas, raioDeExplosão;
    private Timer timerInvulneravel;
    private boolean invulneravel;
    
    public Principal ( ) {
        super( 3 );
        this.quantidadeBombasSimultâneas = 3;
        this.raioDeExplosão = 2;
        this.invulneravel = false;
        
        this.timerInvulneravel = new Timer( 3000, e -> {
            invulneravel = false;
            timerInvulneravel.stop();
        });
        this.timerInvulneravel.setRepeats(false);
    }
    
    public void tomarDano(){
        if( invulneravel == false ){
            setVida(getVida() - 1);
            this.invulneravel = true;
            this.timerInvulneravel.start();
        }
    }

    public int getQuantidadeBombasSimultâneas() {
        return quantidadeBombasSimultâneas;
    }

    public void setQuantidadeBombasSimultâneas(int quantidadeBombasSimultâneas) {
        this.quantidadeBombasSimultâneas = quantidadeBombasSimultâneas;
    }

    public int getRaioDeExplosão() {
        return raioDeExplosão;
    }

    public void setRaioDeExplosão(int raioDeExplosão) {
        this.raioDeExplosão = raioDeExplosão;
    }
    
 }
