package SQR.SQRImage;

import java.awt.Graphics2D;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;

import SQR.SQR;

public class SQRImage {

    private BufferedImage image;

    public SQRImage(String b64) {
        try {
            byte[] decoded = Base64.getDecoder().decode(b64);
            this.image = ImageIO.read(new ByteArrayInputStream(decoded));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void fill(SQR qr, Graphics2D g, int width, int multiple) {
        int size = 8 * multiple; // par;
        int xc = (width * multiple) / 2;
        if (qr.getColorImageBackground() != null) {
            g.setColor(Color.decode(qr.getColorImageBackground()));
            RoundRectangle2D r = new RoundRectangle2D.Float(xc - (size / 2), xc - (size / 2), size, size, size / 2,
                    size / 2);
            g.fill(r);
        }
        // g.setColor(Color.decode(qr.getColorHeader()));

        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        int p = (int) (size * 0.85);
        g.drawImage(this.image, xc - (p / 2), xc - (p / 2), p, p, null);

    }

}
