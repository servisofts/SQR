package SQR.SQRBody;

import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import SQR.SQR;

public class Rounded implements SQRBody {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int xf, int yf) {
        RoundRectangle2D r = new RoundRectangle2D.Float(xf, yf, width, width, width / 2, width / 2);
        g.fill(r);

    }

}
