package model;

public class Comum extends Personagem implements Inimigo {
    private int dano;
    public Comum ( String nome, int vida, int dano ) {
        super ( nome, vida );
        this.dano = dano;
    }
     
    public int getDano(){
        return dano;
    }
     
    @Override
    public void movimento(){
    }
}
