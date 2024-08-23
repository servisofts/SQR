import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class writeText {

    public static void main(String[] args) {
        writeText qe = new writeText(1024, 1024);
        qe.create();

    }

    int width, height;

    public writeText(int width, int height) {
        this.width = width;
        this.height = height;

    }

    public void create() {
        try {
            // CREO LA IMAGEN
            BufferedImage fondoBlanco = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = fondoBlanco.createGraphics();
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, this.width, this.height);

            // CARGAR LA IMAGEN EN EL CENTRO
            BufferedImage imagen = ImageIO.read(new File("other/papper.png"));
            int nuevaAnchura = this.width / 2;
            int nuevaAltura = (int) (((double) nuevaAnchura / imagen.getWidth()) * imagen.getHeight());
            Image imagenEscalada = imagen.getScaledInstance(nuevaAnchura, nuevaAltura, Image.SCALE_SMOOTH);
            int posX = (this.width - nuevaAnchura) / 2;
            int posY = (this.height - nuevaAltura) / 2;
            g2d.drawImage(imagenEscalada, posX, posY, null);

            // CREO EL TEXTO
            String texto = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
            Font fuente = new Font("Arial", Font.PLAIN, 24);
            g2d.setFont(fuente);
            g2d.setColor(Color.BLACK);

            // Calcular el tamaño del texto
            FontMetrics fm = g2d.getFontMetrics();
            int textoAncho = fm.stringWidth(texto);
            int textoAlto = fm.getHeight();

            // Calcular las coordenadas del texto centrado
            // int textoX = (this.width - textoAncho) / 2;
            int textoY = posY + nuevaAltura + textoAlto;

            // Dibujar el texto centrado en la imagen
            String tx = ajustarTexto(texto, this.width / (textoAncho / texto.length()));
            System.out.println();
            g2d.drawString(tx, 0, textoY);

            // GUARDAMOS EL QR
            ImageIO.write(fondoBlanco, "png", new File("other/papper2.png"));
            g2d.dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String ajustarTexto(String texto, int anchoMaximo) {
        StringBuilder resultado = new StringBuilder();
        String[] palabras = texto.split(" ");
        int anchoActual = 0;
        for (int i = 0; i < palabras.length; i++) {
            String palabra = palabras[i];
            int longitudPalabra = palabra.length();
            if (anchoActual + longitudPalabra > anchoMaximo) {
                resultado.append("\n");
                anchoActual = 0;
            }
            resultado.append(palabra).append(" ");
            anchoActual += longitudPalabra + 1;
        }
        return resultado.toString();
    }

}
