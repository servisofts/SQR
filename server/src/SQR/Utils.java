package SQR;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

import com.google.zxing.qrcode.encoder.ByteMatrix;

public class Utils {
    public static byte getFromMatrix(ByteMatrix matix, int x, int y) {
        if (x < 0)
            return 0;
        if (y < 0)
            return 0;
        if (x > matix.getWidth() - 1)
            return 0;
        if (y > matix.getHeight() - 1)
            return 0;
        return matix.get(x, y);
    }

    public static void setBackgroundColor(SQR qr, Graphics2D g) {
        if (qr.getColorBackground().length() > 0) {
            g.setColor(Color.decode(qr.getColorBackground()));
        } else {
            g.setComposite(AlphaComposite.Clear);
        }
    }

    public static void setHeaderColor(SQR qr, Graphics2D graphics) {
        if (qr.colorHeader.length() > 0) {
            Utils.resetBackgroundColor(graphics);
            graphics.setColor(Color.decode(qr.colorHeader));
        } else {
            setBodyColor(qr, graphics);
        }
    }

    public static void setBodyColor(SQR qr, Graphics2D graphics) {
        Utils.resetBackgroundColor(graphics);

        Color[] colors = { Color.decode(qr.colorBody), Color.decode(qr.colorBody2) };
        if (qr.type_color.equals("linear")) {
            GradientPaint gradient = new GradientPaint(0, 0, colors[0], 0, qr.fw, colors[1]);
            graphics.setPaint(gradient);

        } else if (qr.type_color.equals("radial")) {
            float[] stops = { 0.0f, 1.0f };
            Point2D center = new Point2D.Float(qr.fw / 2, qr.fw / 2);
            RadialGradientPaint gradient = new RadialGradientPaint(center, qr.fw, stops, colors);
            graphics.setPaint(gradient);
        } else {
            graphics.setColor(colors[0]);
        }
    }

    public static void resetBackgroundColor(Graphics2D g) {
        g.setComposite(AlphaComposite.SrcOver);
    }
}
