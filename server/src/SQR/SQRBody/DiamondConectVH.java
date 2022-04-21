package SQR.SQRBody;

import java.awt.Graphics2D;
import com.google.zxing.qrcode.encoder.ByteMatrix;

import SQR.SQR;

public class DiamondConectVH implements SQRBody {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int height) {
        int i = x / width;
        int j = y / height;
        ByteMatrix input = qr.getQr().getMatrix();
        if (i - 1 >= 0 && input.get(i - 1, j) == 1) {
            g.fillRect(x, y, (int) (width * 0.5), (int) (height * 1));
        }
        if (i + 1 < input.getWidth() && input.get(i + 1, j) == 1) {
            g.fillRect((int) (x + (width * 0.5)), y, (int) (width * 0.5), (int) (height * 1));
        }
        if (j - 1 >= 0 && input.get(i, j - 1) == 1) {
            g.fillRect(x, y, (int) (width * 1), (int) (height * 0.5));
        }
        if (j + 1 < input.getWidth() && input.get(i, j + 1) == 1) {
            g.fillRect(x, (int) (y + (height * 0.5)), (int) (width * 1), (int) (height * 0.5));
        }
        int[] xP = {
                x + width / 2,
                x + width,
                x + width / 2,
                x
        };
        int[] yP = {
                y,
                y + height / 2,
                y + height,
                y + height / 2
        };
        g.fillPolygon(xP, yP, xP.length);
        return;

    }

}
