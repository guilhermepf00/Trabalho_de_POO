package jogo;

import javax.swing.SwingUtilities;
import interfacegrafica.TelaMenu;

public class Jogo {
    
    public static volatile boolean fim = false;
    public static int time = 0;
    
    public static void main(String[] args) {
        // Menu Principal
        SwingUtilities.invokeLater(() -> {
            TelaMenu menu = new TelaMenu();
            menu.setVisible(true);
        });
        
        while (!fim){
            try {
                Thread.sleep(1000); // = 1 segundo em jogo
                time++;
                System.out.println("" + time + " Seconds");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
