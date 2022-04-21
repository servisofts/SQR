package SQR.SQRHeader;

import java.awt.Graphics2D;

import SQR.SQR;

import java.awt.Color;

public class Diamond implements SQRHeader {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int height) {
        final int DIAMETER = width * 3 / 7;
        final int OFFSET = width * 2 / 7;

        g.setColor(Color.decode(qr.getColorHeader()));
      //  g.fillRect(x + OFFSET, y + OFFSET, DIAMETER, DIAMETER);
        int x1 = x + OFFSET;
        int y1 = y + OFFSET;

        int[] xP = {
                x1 + DIAMETER / 2,
                x1 + DIAMETER,
                x1 + DIAMETER / 2,
                x1
        };
        int[] yP = {
                y1,
                y1 + DIAMETER / 2,
                y1 + DIAMETER,
                y1 + DIAMETER / 2
        };
        g.fillPolygon(xP, yP, xP.length);

    }

}
