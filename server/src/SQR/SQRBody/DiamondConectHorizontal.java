package SQR.SQRBody;

import java.awt.Graphics2D;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import SQR.SQR;
import SQR.Utils;

import java.awt.Color;

public class DiamondConectHorizontal implements SQRBody {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int xf, int yf) {
        g.setColor(Color.decode(qr.getColorBody()));
        ByteMatrix input = qr.getQr().getMatrix();
        if (Utils.getFromMatrix(input, x - 1, y) == 1) {
            g.fillRect(xf, yf, (int) (width * 0.5), (int) (width * 1));
        }
        if (Utils.getFromMatrix(input, x + 1, y) == 1) {
            g.fillRect((int) (xf + (width * 0.5)), yf, (int) (width * 0.5), (int) (width * 1));
        }
        int[] xP = {
                xf + width / 2,
                xf + width,
                xf + width / 2,
                xf
        };
        int[] yP = {
                yf,
                yf + width / 2,
                yf + width,
                yf + width / 2
        };

        g.fillPolygon(xP, yP, xP.length);
        // g.setColor(Color.decode(qr.getColorBackground()));

        // g.drawPolygon(xP, yP, xP.length);
        return;

    }

}
