package SQR.SQRDetail;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import SQR.SQRDetail.Elements.*;
import SQR.SQRDetail.Elements.EText.AlignType;

import org.json.JSONObject;

import SQR.SQR;

public class Glosa extends SQRDetailAbstract {

    @Override
    public BufferedImage fill(SQR qr, BufferedImage imageQR) {
        int width = this.data.getInt("width");
        int height = this.data.getInt("height");
        BufferedImage fondoBlanco = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = fondoBlanco.createGraphics();
        g2d.setColor(Color.decode(this.data.getString("background")));
        g2d.fillRect(0, 0, width, height);

        int posY = 0;
        posY += 40;
        new EText("Logo").setFontSize(20).setAlign(AlignType.center).paint(fondoBlanco, g2d, width / 2,
                posY);

        posY += 100;

        int nuevaAnchura = (width / 12) * 11;
        int nuevaAltura = (int) (((double) nuevaAnchura / imageQR.getWidth()) * imageQR.getHeight());
        Image imagenEscalada = imageQR.getScaledInstance(nuevaAnchura, nuevaAltura, imageQR.SCALE_SMOOTH);
        int posX = (width - nuevaAnchura) / 2;
        g2d.drawImage(imagenEscalada, posX, posY, null);

        posY += nuevaAltura;

        posY += 8;
        new EText(getParam("glosa")).setFontSize(40).setAlign(AlignType.center).paint(fondoBlanco, g2d, width / 2,
                posY);
        posY += 40;
        new EText(getParam("monto")).setFontSize(40).setAlign(AlignType.center).paint(fondoBlanco, g2d, width / 2,
                posY);

        posY = height - 50;
        new EText("Este QR puede ser pagado desde la app de tu banco.").setColor(Color.LIGHT_GRAY).setFontSize(20)
                .setAlign(AlignType.center).paint(
                        fondoBlanco, g2d, width / 2,
                        posY);
        return fondoBlanco;
    }

}
