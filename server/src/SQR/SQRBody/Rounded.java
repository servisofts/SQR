package SQR.SQRBody;

import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import SQR.SQR;

public class Rounded implements SQRBody {

    @Override
    public void fill(SQR qr,Graphics2D g, int x, int y, int width, int height) {
        RoundRectangle2D r = new RoundRectangle2D.Float(x, y, width, height, width / 2, height / 2);
        g.fill(r);

    }

}
