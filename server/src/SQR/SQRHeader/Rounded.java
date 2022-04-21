package SQR.SQRHeader;

import java.awt.Graphics2D;

import SQR.SQR;

import java.awt.Color;
import java.awt.geom.RoundRectangle2D;

public class Rounded implements SQRHeader {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int height) {

        final int DIAMETER = width * 3 / 7;
        final int OFFSET = width * 2 / 7;

        g.setColor(Color.decode(qr.getColorHeader()));
        RoundRectangle2D r = new RoundRectangle2D.Float(x + OFFSET, y + OFFSET, DIAMETER, DIAMETER, DIAMETER / 2,
                DIAMETER / 2);
        g.fill(r);

    }

}
