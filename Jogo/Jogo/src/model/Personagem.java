package model;

public abstract class Personagem {
    private int vida, quantidadeBombasSimultâneas, raioDeExplosão;
     
    public Personagem ( ) {
        this.vida = 3;
        this.quantidadeBombasSimultâneas = 1;
        this.raioDeExplosão = 1;
    }
     
    public int getVida(){
        return vida;
    }
}
