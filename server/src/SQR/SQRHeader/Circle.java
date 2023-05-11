package SQR.SQRHeader;

import java.awt.Graphics2D;

import SQR.SQR;
import SQR.Utils;

import java.awt.Color;

public class Circle implements SQRHeader {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int height) {

        final int DIAMETER = width * 3 / 7;
        final int OFFSET = width * 2 / 7;

        // g.setColor(Color.decode(qr.getColorHeader()));
        Utils.setHeaderColor(qr, g);

        g.fillOval(x + OFFSET, y + OFFSET, DIAMETER, DIAMETER);

    }

}
