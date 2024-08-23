package SQR.SQRImage;

import java.awt.Graphics2D;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;

import SQR.SQR;
import SQR.Utils;

public class SQRImage {

    private BufferedImage image;

    public SQRImage(String src, boolean net) {
        try {
            URL url = new URL(src);
            this.image = ImageIO.read(url);
            // ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // ImageIO.write(this.image, "png", baos);
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    public SQRImage(String b64) {
        try {
            byte[] decoded = Base64.getDecoder().decode(b64);
            this.image = ImageIO.read(new ByteArrayInputStream(decoded));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }
    }

    public void fill(SQR qr, Graphics2D g, int width, int multiple) {
        if(this.image == null){
            return;
        }
        double mitad = (int) width / 2;
        int tercio = (int) (width / 3);

        int size = tercio * multiple;
        int fw = 0;
        int fh = 0;
        int iw = this.image.getWidth();
        int ih = this.image.getHeight();
        double er = (double) iw / (double) ih;
        if (iw < ih) {
            int sobrante = ih - size;
            fh = size;
            fw = (int) (iw - (sobrante * er));

        } else {
            int sobrante = iw - size;
            fw = size;
            fh = (int) (ih - (sobrante / er));
        }

        Utils.setBackgroundColor(qr, g);
        int border = (int) (multiple * 2);
        g.fillRect(
                (int) (mitad * multiple) - ((fw - multiple) / 2),
                (int) (mitad * multiple) - ((fh - multiple) / 2),
                fw,
                fh);
        // g.fillRect(
        // (mitad - (tercio / 2)) * multiple,
        // (mitad - (tercio / 2)) * multiple,
        // size,
        // size);
        Utils.resetBackgroundColor(g);
        g.drawImage(this.image,
                (int) (mitad * multiple) - ((fw - multiple) / 2) + (border / 2),
                (int) (mitad * multiple) - ((fh - multiple) / 2) + (border / 2),
                fw - border,
                fh - border,
                null);
        System.out.println(mitad + " " + tercio);
    }

    public void fill2(SQR qr, Graphics2D g, int width, int multiple) {
        if(this.image == null){
            return;
        }
        int size = ((width / 3) * multiple); // par;
        int xc = (width * multiple) / 2;

        int fw = 0;
        int fh = 0;
        int iw = this.image.getWidth();
        int ih = this.image.getHeight();
        double er = (double) iw / (double) ih;
        if (iw < ih) {
            System.out.println("Es mas ancha que alta");
            int sobrante = ih - size;
            fh = size;
            fw = (int) (iw - (sobrante * er));

        } else {
            System.out.println("Es mas alta que ancha");
            int sobrante = iw - size;
            fw = size;
            fh = (int) (ih - (sobrante / er));
        }

        System.out.println("width:" + width);
        System.out.println("iw:" + iw);
        System.out.println("ih:" + ih);
        System.out.println("x:" + (xc - (fw / 2)));
        System.out.println("y:" + (xc - (fh / 2)));
        System.out.println("fw:" + fw);
        System.out.println("fh:" + fh);

        Utils.setBackgroundColor(qr, g);
        g.fillRect(xc - (fw / 2) - (multiple / 2), xc - (fh / 2) - (multiple / 2), fw + (multiple), fh + (multiple));

        Utils.resetBackgroundColor(g);
        if (qr.getColorImageBackground() != null) {
            g.setColor(Color.decode(qr.getColorImageBackground()));
            RoundRectangle2D r = new RoundRectangle2D.Float(xc - (fw / 2), xc - (fh / 2) - (multiple / 2), fw,
                    fh + multiple, multiple / 2,
                    multiple / 2);
            g.fill(r);
        }

        // g.setColor(Color.decode(qr.getColorHeader()));

        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
                RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_DITHERING,
                RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
        int p = (int) (size * 0.85);
        g.drawImage(this.image, xc - (fw / 2), xc - (fh / 2), fw, fh, null);
        // g.drawImage(this.image, xc - (p / 2), xc - (p / 2), p, p, null);

    }

}
