
package jogo;

import javax.swing.SwingUtilities;
import interfacegrafica.TelaMenu;

public class Jogo {
    
    public static void main(String[] args) {
        // Menu Principal
        SwingUtilities.invokeLater(() -> {
            TelaMenu menu = new TelaMenu();
            menu.setVisible(true);
        });
    }
}
