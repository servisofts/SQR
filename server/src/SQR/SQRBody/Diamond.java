package SQR.SQRBody;

import java.awt.Graphics2D;
import SQR.SQR;

public class Diamond implements SQRBody {

    @Override
    public void fill(SQR qr,Graphics2D g, int x, int y, int width, int height) {

        int[] xP = {
                x + width / 2,
                x + width,
                x + width / 2,
                x
        };
        int[] yP = {
                y,
                y + height / 2,
                y + height,
                y + height / 2
        };
        g.fillPolygon(xP, yP, xP.length);

    }

}
