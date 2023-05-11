package SQR.SQRBody;

import java.awt.Graphics2D;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import SQR.SQR;
import SQR.Utils;

public class CircleConectHorizontal implements SQRBody {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width,int xf, int yf) {
        // int i = x / width;
        // int j = y / height;
        ByteMatrix input = qr.getQr().getMatrix();
        if (Utils.getFromMatrix(input, x - 1, y) == 1) {
            g.fillRect(xf, yf, (int) (width * 0.5), (int) (width * 1));
        }
        if (Utils.getFromMatrix(input, x + 1, y) == 1) {
            g.fillRect((int) (xf + (width * 0.5)), yf, (int) (width * 0.5), (int) (width * 1));
        }
        g.fillOval(xf, yf, (int) (width * 1), (int) (width * 1));
        return;

    }

}
