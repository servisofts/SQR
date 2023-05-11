package SQR.SQRFramework;

import java.awt.Graphics2D;

import SQR.SQR;
import SQR.Utils;

import java.awt.AlphaComposite;
import java.awt.Color;

public class Square implements SQRFramework {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int height) {
        Utils.setBackgroundColor(qr, g);
        g.fillRect(x, y, width, width);

        final int DIAMETER = width * 5 / 7;
        final int OFFSET = width / 7;
        // g.setColor(Color.decode(qr.getColorHeader()));
        Utils.setHeaderColor(qr, g);
        g.fillRect(x, y, width, width);
        Utils.setBackgroundColor(qr, g);
        g.fillRect(x + OFFSET, y + OFFSET, DIAMETER, DIAMETER);

    }

}
