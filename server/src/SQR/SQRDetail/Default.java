package SQR.SQRDetail;

import java.awt.image.BufferedImage;

import org.json.JSONObject;

import SQR.SQR;

public class Default extends SQRDetailAbstract {


    @Override
    public BufferedImage fill(SQR qr, BufferedImage imageQR) {
        return imageQR;
    }

  
}
