package model;

public abstract class Personagem {
    private int vida;
     
    public Personagem ( int vida ) {
        this.vida = vida;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }
    
    
}
