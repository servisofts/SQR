package SQR.SQRBody;

import java.awt.Graphics2D;
import SQR.SQR;

public class Diamond implements SQRBody {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int xf, int yf) {

        int[] xP = {
                xf + width / 2,
                xf + width,
                xf + width / 2,
                xf
        };
        int[] yP = {
                yf,
                yf + width / 2,
                yf + width,
                yf + width / 2
        };
        g.fillPolygon(xP, yP, xP.length);

    }

}
