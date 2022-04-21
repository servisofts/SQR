package SQR.SQRBody;

import java.awt.Graphics2D;

import SQR.SQR;

public class Circle implements SQRBody {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int height) {
        g.fillOval(x, y, (int) (width * 1), (int) (height * 1));

    }

}
