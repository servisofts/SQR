package SQR.SQRFramework;

import java.awt.Graphics2D;

import SQR.SQR;
import SQR.Utils;

import java.awt.AlphaComposite;
import java.awt.Color;

public class Diamond implements SQRFramework {

        @Override
        public void fill(SQR qr, Graphics2D g, int x, int y, int width, int height) {
                Utils.setBackgroundColor(qr, g);
                g.fillRect(x, y, width, width);

                final int DIAMETER = width * 5 / 7;
                final int OFFSET = width / 7;

                // g.setColor(Color.decode(qr.getColorHeader()));

                int[] xP = {
                                x + width / 2,
                                x + width,
                                x + width / 2,
                                x
                };
                int[] yP = {
                                y,
                                y + width / 2,
                                y + width,
                                y + width / 2
                };
                Utils.setHeaderColor(qr, g);
                g.fillPolygon(xP, yP, xP.length);

                int x1 = x + OFFSET;
                int y1 = y + OFFSET;

                int[] xP1 = {
                                x1 + DIAMETER / 2,
                                x1 + DIAMETER,
                                x1 + DIAMETER / 2,
                                x1
                };
                int[] yP1 = {
                                y1,
                                y1 + DIAMETER / 2,
                                y1 + DIAMETER,
                                y1 + DIAMETER / 2
                };
                Utils.setBackgroundColor(qr, g);
                g.fillPolygon(xP1, yP1, xP1.length);

        }

}
