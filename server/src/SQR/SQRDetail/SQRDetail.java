package SQR.SQRDetail;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import org.json.JSONObject;

import SQR.SQR;

public interface SQRDetail {
    public BufferedImage fill(SQR qr, BufferedImage imageQR);
    public void setData(JSONObject data);
}
