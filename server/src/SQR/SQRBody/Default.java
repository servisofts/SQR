package SQR.SQRBody;

import java.awt.Graphics2D;

import SQR.SQR;

public class Default implements SQRBody {

    @Override
    public void fill(SQR qr,Graphics2D g, int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);

    }

}
