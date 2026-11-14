
package interfacegrafica;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sprites {
    public static BufferedImage spritePlayer;
    public static BufferedImage spriteComum;
    public static BufferedImage spriteRastreador;
    public static BufferedImage spriteSabotador;
    
    public static void carregarSprites(){
        try {
            spritePlayer = ImageIO.read(new File("src/Imagens/Player.png"));
            spriteComum = ImageIO.read(new File("src/Imagens/Scully.png"));
            spriteRastreador = ImageIO.read(new File("src/Imagens/Girl.png"));
            spriteSabotador = ImageIO.read(new File("src/Imagens/Devil.png"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
