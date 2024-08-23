package SQR.SQRDetail.Elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class EText extends ElementAbstract {
    public enum AlignType {
        start,
        center,
        end
    }

    public String text;
    public Color color;
    public int fontSize;

    public AlignType align;

    public EText(String txt) {
        this.text = txt;
        fontSize = 24;
        this.color = Color.BLACK;
    }

    public EText setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public EText setAlign(AlignType type) {
        this.align = type;
        return this;
    }

    public EText setColor(Color color) {
        this.color = color;
        return this;

    }

    public void paint(BufferedImage img, Graphics2D g, int x, int y) {
        Font fuente = new Font("Arial", Font.PLAIN, this.fontSize);
        g.setFont(fuente);
        g.setColor(this.color);

        FontMetrics fm = g.getFontMetrics();
        int textoAncho = fm.stringWidth(this.text);
        int textoAlto = fm.getHeight();
        int textoY = y + 10 + textoAlto;
        int xf = 0;
        switch (this.align) {
            case center:
                xf = x - (textoAncho / 2);
                break;
            case end:
                xf = x + textoAncho;
                break;
            default:
                xf = x;
                break;
        }
        g.drawString(this.text, xf, textoY);
    }
}
