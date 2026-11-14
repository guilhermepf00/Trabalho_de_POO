package model;

public interface Inimigo {
     
    public abstract void movimento();
    public abstract void pararMovimento();
    // x e y são os pontos horizontais e vertu=icais dos pixel para movimento dos inimigos 
    int getX();
    int getY();
     
}
