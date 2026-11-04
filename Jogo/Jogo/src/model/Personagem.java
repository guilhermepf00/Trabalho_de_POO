package model;

public abstract class Personagem {
    private String nome;
    private int vida;
     
    public Personagem ( String nome, int vida ) {
        this.nome = nome;
        this.vida = vida;
    }
     
    public String getNome(){
        return nome;
    }
     
    public int getVida(){
        return vida;
    }
}
