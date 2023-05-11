package SQR.SQRFramework;

import java.awt.Graphics2D;

import SQR.SQR;
import SQR.Utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.geom.RoundRectangle2D;

public class Circle implements SQRFramework {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int height) {
        Utils.setBackgroundColor(qr, g);
        g.fillRect(x, y, width, width);
       
        final int DIAMETER = width * 5 / 7;
        final int OFFSET = width / 7;
        Utils.setHeaderColor(qr, g);
        g.fillOval(x, y, width, width);
        
        Utils.setBackgroundColor(qr, g);
        g.fillOval(x + OFFSET, y + OFFSET, DIAMETER, DIAMETER);
       
    }

}
