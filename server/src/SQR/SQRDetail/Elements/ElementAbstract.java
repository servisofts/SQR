package SQR.SQRDetail.Elements;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class ElementAbstract {
    abstract void paint(BufferedImage image, Graphics2D g, int x, int y);
}
