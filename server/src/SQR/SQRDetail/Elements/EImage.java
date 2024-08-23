package SQR.SQRDetail.Elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;

import javax.imageio.ImageIO;

public class EImage extends ElementAbstract {
    public BufferedImage image;
    public int width, height;

    public EImage(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void paint(BufferedImage img, Graphics2D g, int x, int y) {
        Image imagenEscalada = this.image.getScaledInstance(this.width, this.height, this.image.SCALE_SMOOTH);

        g.setColor(Color.decode("#000000"));
        g.drawRect(x, y, this.width, this.height);
        // g.drawImage(imagenEscalada, x, y, null);
    }

    public EImage load_url(String src) throws Exception {
        URL url = new URL(src);
        this.image = ImageIO.read(url);
        return this;
    }

    public EImage load_b64(String b64) throws IOException {
        byte[] decoded = Base64.getDecoder().decode(b64);
        this.image = ImageIO.read(new ByteArrayInputStream(decoded));
        return this;
    }
}
