package SQR.SQRBody;

import java.awt.Graphics2D;
import com.google.zxing.qrcode.encoder.ByteMatrix;

import SQR.SQR;
import SQR.Utils;

public class DiamondConectVertical implements SQRBody {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int xf, int yf) {
        ByteMatrix input = qr.getQr().getMatrix();
        // if (j - 1 >= 0 && input.get(i, j - 1) == 1) {
        if (Utils.getFromMatrix(input, x, y - 1) == 1) {
            g.fillRect(xf, yf, (int) (width * 1), (int) (width * 0.5));
        }
        if (Utils.getFromMatrix(input, x, y + 1) == 1) {
            // if (j + 1 < input.getWidth() && input.get(i, j + 1) == 1) {
            g.fillRect(xf, (int) (yf + (width * 0.5)), (int) (width * 1), (int) (width * 0.5));
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
        return;

    }

}
