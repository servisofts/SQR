package SQR.SQRBody;

import java.awt.Graphics2D;

import SQR.SQR;

public class Dot implements SQRBody {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int xf, int yf) {
        double size = 0.7;
        int s = (int) (width * (1 - size) / 2);
        int f = (int) (width * size);
        g.fillOval(xf + s, yf + s, f, f);

    }

}
