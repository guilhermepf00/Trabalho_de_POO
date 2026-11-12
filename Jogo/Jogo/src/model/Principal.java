/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Administrator
 */
public class Principal extends Personagem {
    private int vida, quantidadeBombasSimultâneas, raioDeExplosão;
    
    public Principal ( ) {
        super( 3 );
        this.quantidadeBombasSimultâneas = 3;
        this.raioDeExplosão = 2;
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
