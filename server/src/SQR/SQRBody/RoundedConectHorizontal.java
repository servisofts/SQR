package SQR.SQRBody;

import java.awt.Graphics2D;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import java.awt.geom.RoundRectangle2D;
import SQR.SQR;

public class RoundedConectHorizontal implements SQRBody {

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
        RoundRectangle2D r = new RoundRectangle2D.Float(x, y, width, height, width / 2, height / 2);
        g.fill(r);
        return;

    }

}
