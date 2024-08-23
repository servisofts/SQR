package Component;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import SQR.SQR;
import SQR.SQR.SQRContentType;
import SQR.SQRBody.SQRBodyFactory.SQRBodyType;
import SQR.SQRDetail.SQRDetail;
import SQR.SQRDetail.SQRDetailFactory;
import SQR.SQRDetail.SQRDetailFactory.SQRDetailType;
import SQR.SQRFramework.SQRFrameworkFactory.SQRFrameworkType;
import SQR.SQRHeader.SQRHeaderFactory.SQRHeaderType;
import SQR.SQRImage.SQRImage;
import Server.SSSAbstract.SSSessionAbstract;
import Servisofts.SConfig;
import Servisofts.SUtil;

public class qr {
    public static final String COMPONENT = "qr";

    public static void onMessage(JSONObject obj, SSSessionAbstract session) {
        switch (obj.getString("type")) {
            case "registro":
                registro(obj, session);
                break;
            case "getTypes":
                getTypes(obj, session);
                break;
        }
    }

    /*
     * {
     * content: "http://www.google.com",
     * width: 512,
     * height: 512,
     * body: "Default",
     * header: "Default"
     * framework: "Default"
     * colorBackground: "#ffffff",
     * colorBody: "#000000",
     * colorHeader: "#000000"
     * }
     */

    public static void getTypes(JSONObject obj, SSSessionAbstract session) {
        JSONObject response = new JSONObject();
        response.put("body", Arrays.asList(SQRBodyType.values()));
        response.put("header", Arrays.asList(SQRHeaderType.values()));
        response.put("framework", Arrays.asList(SQRFrameworkType.values()));
        response.put("detail", Arrays.asList(SQRDetailType.values()));
        response.put("content_type", Arrays.asList(SQRContentType.values()));
        response.put("errorCorrectionLevel", Arrays.asList(ErrorCorrectionLevel.values()));
        obj.put("data", response);
        obj.put("estado", "exito");
    }

    public static void registro(JSONObject obj, SSSessionAbstract session) {
        try {
            System.out.println("Se solicito crear un qr");
            JSONObject data = obj.getJSONObject("data");
            SQRContentType content_type;
            if (data.has("content_type") && !data.getString("content_type").isEmpty()) {
                content_type = SQRContentType.valueOf(data.getString("content_type"));
            } else {
                content_type = SQRContentType.text;
            }

            SQR qr = new SQR(data.getString("content"), content_type);
            if (data.has("errorCorrectionLevel") && !data.getString("errorCorrectionLevel").isEmpty()) {
                qr.setErrorCorrectionLevel(ErrorCorrectionLevel.valueOf(data.getString("errorCorrectionLevel")));
            }
            qr.createQr();

            if (data.has("width")) {
                qr.setWidth(data.getInt("width"));
            }
            if (data.has("height")) {
                qr.setHeight(data.getInt("height"));
            }
            if (data.has("body") && !data.getString("body").isEmpty()) {
                qr.setBody(SQRBodyType.valueOf(data.getString("body")));
            }
            if (data.has("header") && !data.getString("header").isEmpty()) {
                qr.setHeader(SQRHeaderType.valueOf(data.getString("header")));
            }
            if (data.has("framework") && !data.getString("framework").isEmpty()) {
                qr.setFramework(SQRFrameworkType.valueOf(data.getString("framework")));
            }
            if (data.has("detail") && !data.getString("detail").isEmpty()) {
                JSONObject data_detail;
                if (data.has("detail_data")) {
                    data_detail = data.getJSONObject("detail_data");
                } else {
                    data_detail = new JSONObject();
                }
                qr.setDetail(SQRDetailFactory.create(SQRDetailType.valueOf(data.getString("detail")), data_detail));
            }
            if (data.has("colorBackground") && !data.getString("colorBackground").isEmpty()) {
                qr.setColorBackground(data.getString("colorBackground"));
            }
            if (data.has("colorBody") && !data.getString("colorBody").isEmpty()) {
                qr.setColorBody(data.getString("colorBody"));
            }
            if (data.has("colorBody2") && !data.getString("colorBody2").isEmpty()) {
                qr.setColorBody2(data.getString("colorBody2"));
            }
            if (data.has("type_color") && !data.getString("type_color").isEmpty()) {
                qr.setType_color(data.getString("type_color"));
            }
            if (data.has("colorHeader") && !data.getString("colorHeader").isEmpty()) {
                qr.setColorHeader(data.getString("colorHeader"));
            }
            if (data.has("colorImageBackground") && !data.getString("colorImageBackground").isEmpty()) {
                qr.setColorImageBackground(data.getString("colorImageBackground"));
            }
            if (data.has("image") && !data.getString("image").isEmpty()) {
                qr.setImagen(new SQRImage(data.getString("image")));
            }
            if (data.has("image_src") && !data.getString("image_src").isEmpty()) {
                qr.setImagen(new SQRImage(data.getString("image_src"), true));
            }

            String reponse_type = "b64";
            if (data.has("reponse_type")) {
                reponse_type = data.getString("reponse_type");
            }
            String key = SUtil.uuid();
            if (data.has("key")) {
                key = data.getString("key");
            }
            data = new JSONObject();

            switch (reponse_type) {
                case "b64":
                    data.put("b64", qr.toB64());
                    break;
                case "image":
                    qr.saveImage(SConfig.getJSON("files").getString("url") + "/" + key);
                    data.put("key", key);
                    data.put("src", "https://qr.servisofts.com/images/" + key);
                    break;
            }
            obj.put("data", data);

            obj.put("estado", "exito");
        } catch (Exception e) {
            obj.put("estado", "error");
            obj.put("error", e.getMessage());
            e.printStackTrace();
        }
    }

}
