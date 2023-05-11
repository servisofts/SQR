package SQR.SQRBody;

import java.awt.Graphics2D;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import java.awt.geom.RoundRectangle2D;
import SQR.SQR;
import SQR.Utils;

public class RoundedConectVertical implements SQRBody {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int xf, int yf) {
        ByteMatrix input = qr.getQr().getMatrix();
        // if (j - 1 >= 0 && input.get(i, j - 1) == 1) {
        if (Utils.getFromMatrix(input, x, y - 1) == 1) {
            g.fillRect(xf, yf, (int) (width * 1), (int) (width * 0.5));
        }
        if (Utils.getFromMatrix(input, x, y + 1) == 1) {
            g.fillRect(xf, (int) (yf + (width * 0.5)), (int) (width * 1), (int) (width * 0.5));
        }

        RoundRectangle2D r = new RoundRectangle2D.Float(xf, yf, width, width, width / 2, width / 2);
        g.fill(r);
        return;

    }

}
