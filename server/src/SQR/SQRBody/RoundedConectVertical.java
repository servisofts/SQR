package SQR.SQRBody;

import java.awt.Graphics2D;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import java.awt.geom.RoundRectangle2D;
import SQR.SQR;

public class RoundedConectVertical implements SQRBody {

    @Override
    public void fill(SQR qr, Graphics2D g, int x, int y, int width, int height) {
        int i = x / width;
        int j = y / height;
        ByteMatrix input = qr.getQr().getMatrix();
        if (j - 1 >= 0 && input.get(i, j - 1) == 1) {
            g.fillRect(x, y, (int) (width * 1), (int) (height * 0.5));
        }
        if (j + 1 < input.getWidth() && input.get(i, j + 1) == 1) {
            g.fillRect(x, (int) (y + (height * 0.5)), (int) (width * 1), (int) (height * 0.5));
        }
        
        RoundRectangle2D r = new RoundRectangle2D.Float(x, y, width, height, width / 2, height / 2);
        g.fill(r);
        return;

    }

}
