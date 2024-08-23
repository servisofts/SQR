package SQR.SQRDetail;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import SQR.SQRDetail.Elements.*;
import SQR.SQRDetail.Elements.EText.AlignType;

import org.json.JSONObject;

import SQR.SQR;

public class Banco1 extends SQRDetailAbstract {

        @Override
        public BufferedImage fill(SQR qr, BufferedImage imageQR) {
                int width = this.data.getInt("width");
                int height = this.data.getInt("height");

                int grid = width / 12;

                BufferedImage fondoBlanco = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = fondoBlanco.createGraphics();
                g2d.setColor(Color.decode(this.data.getString("background")));
                g2d.fillRect(0, 0, width, height);

                int posY = 0;
                posY += 20;

                int nuevaAnchura = (width / 12) * 11;
                int nuevaAltura = (int) (((double) nuevaAnchura / imageQR.getWidth()) * imageQR.getHeight());
                Image imagenEscalada = imageQR.getScaledInstance(nuevaAnchura, nuevaAltura, imageQR.SCALE_SMOOTH);
                int posX = (width - nuevaAnchura) / 2;
                g2d.drawImage(imagenEscalada, posX, posY, null);

                posY += nuevaAltura;

                posY += 8;
                new EText(getParam("glosa")).setFontSize(40).setAlign(AlignType.center).paint(fondoBlanco, g2d,
                                width / 2,
                                posY);
                posY += 40;
                new EText(getParam("monto")).setFontSize(40).setAlign(AlignType.center).paint(fondoBlanco, g2d,
                                width / 2,
                                posY);

                try {
                        new EImage(grid * 10, grid * 3).load_url(
                                        "https://rolespermisos.servisofts.com/http//page/cb94e548-f4f8-43fb-9cf7-3a862a4c0869")
                                        .paint(fondoBlanco, g2d, grid, height - (grid * 3) - 20);
                } catch (Exception e) {
                        // TODO: handle exception
                }

                return fondoBlanco;
        }

}
