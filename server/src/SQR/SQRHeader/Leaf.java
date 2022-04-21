package SQR.SQRHeader;

import java.awt.Graphics2D;

import SQR.SQR;

import java.awt.Color;

public class Leaf implements SQRHeader {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int height) {
        final int DIAMETER = width * 3 / 7;
        final int OFFSET = width * 2 / 7;

        g.setColor(Color.decode(qr.getColorHeader()));
        int x1 = x + OFFSET;
        int y1 = y + OFFSET;

        int[] xP = {
                x1,
                x1 + DIAMETER,
                x1 + DIAMETER - (DIAMETER / 4),
                x1 + DIAMETER,
                x1 + DIAMETER,
                x1 + (DIAMETER / 4),
                x1
        };
        int[] yP = {
                y1,
                y1,
                y1,
                y1 + (DIAMETER / 4),
                y1 + DIAMETER,
                y1 + DIAMETER,
                y1 + (DIAMETER - (DIAMETER / 4)),
        };
        g.fillPolygon(xP, yP, xP.length);

    }

}
