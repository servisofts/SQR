import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class writeText {

    public static void main(String[] args) {
        
        try {
            // Cargar la imagen
            BufferedImage imagen = ImageIO.read(new File("ssl/sqr_pemQR.png"));
            
            // Crear un objeto Graphics2D para dibujar en la imagen
            Graphics2D g2d = imagen.createGraphics();
            
            // Definir la fuente y el color del texto
            Font fuente = new Font("Arial", Font.BOLD, 24);
            Color colorTexto = Color.WHITE;
            
            // Escribir el texto en la imagen
            g2d.setFont(fuente);
            g2d.setColor(colorTexto);
            g2d.drawString("Texto a escribir", 100, 100); // Cambia las coordenadas para posicionar el texto en la imagen
            
            // Guardar la imagen con el texto escrito
            ImageIO.write(imagen, "jpg", new File("ssl/sqr_pemQR.png"));
            
            // Cerrar el objeto Graphics2D
            g2d.dispose();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
