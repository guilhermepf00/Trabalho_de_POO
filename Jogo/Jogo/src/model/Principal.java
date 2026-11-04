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
    private int dano;
    public Principal ( String nome, int vida, int dano ) {
        super ( nome, vida );
        this.dano = dano;
    }
     
    public int getDano(){
        return dano;
    } 
 }
