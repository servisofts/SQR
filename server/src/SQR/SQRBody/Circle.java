package SQR.SQRBody;

import java.awt.Graphics2D;

import SQR.SQR;

public class Circle implements SQRBody {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int xf, int yf) {
        g.fillOval(xf, yf, (int) (width * 1), (int) (width * 1));

    }

}
