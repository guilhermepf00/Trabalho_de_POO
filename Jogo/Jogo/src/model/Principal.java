package model;

public class Principal extends Personagem {
    private int quantidadeBombasSimultâneas, raioDeExplosão;
    
    public Principal ( ) {
        super( 3 );
        this.quantidadeBombasSimultâneas = 3;
        this.raioDeExplosão = 2;      
    }
    
    public void tomarDano(){
        setVida(getVida() - 1);
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
