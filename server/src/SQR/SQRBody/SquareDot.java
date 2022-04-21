package SQR.SQRBody;

import java.awt.Graphics2D;
import SQR.SQR;

public class SquareDot implements SQRBody {

    @Override
    public void fill(SQR qr,Graphics2D g, int x, int y, int width, int height) {
        g.fillRect(x, y, (int) (width * 0.7), (int) (height * 0.7));
      //  g.drawRect(x, y, (int) (width * 0.7), (int) (height * 0.7));

    }

}
