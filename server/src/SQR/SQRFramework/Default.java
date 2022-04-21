package SQR.SQRFramework;

import java.awt.Graphics2D;

import SQR.SQR;

import java.awt.Color;

public class Default implements SQRFramework {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int height) {
        final int DIAMETER = width * 5 / 7;
        final int OFFSET = width / 7;
        g.setColor(Color.decode(qr.getColorHeader()));
        g.fillRect(x, y, width, width);
        g.setColor(Color.decode(qr.getColorBackground()));
        g.fillRect(x + OFFSET, y + OFFSET, DIAMETER, DIAMETER);

    }

}
