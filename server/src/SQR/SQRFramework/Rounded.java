package SQR.SQRFramework;

import java.awt.Graphics2D;

import SQR.SQR;
import SQR.Utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;

public class Rounded implements SQRFramework {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int height) {
        Utils.setBackgroundColor(qr, g);
        g.fillRect(x, y, width, width);

        final int DIAMETER = width * 5 / 7;
        final int OFFSET = width / 7;
        // g.setColor(Color.decode(qr.getColorHeader()));
        // g.fillOval(x, y, width, width);
        RoundRectangle2D r = new RoundRectangle2D.Float(x, y, width, height, width / 2,
                height / 2);
        Utils.setHeaderColor(qr, g);
        g.fill(r);

        RoundRectangle2D r2 = new RoundRectangle2D.Float(x + OFFSET, y + OFFSET, DIAMETER, DIAMETER, DIAMETER / 2,
                DIAMETER / 2);
        Utils.setBackgroundColor(qr, g);
        g.fill(r2);

    }

}
