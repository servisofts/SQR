package SQR.SQRBody;

import java.awt.Graphics2D;
import SQR.SQR;

public class SquareDot implements SQRBody {

    @Override
    public void fill(SQR qr,Graphics2D g, int x, int y, int width, int xf, int yf) {
        g.fillRect(xf, yf, (int) (width * 0.7), (int) (width * 0.7));
      //  g.drawRect(x, y, (int) (width * 0.7), (int) (height * 0.7));

    }

}
