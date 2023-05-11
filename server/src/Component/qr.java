package Component;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;

import SQR.SQR;
import SQR.SQRBody.SQRBodyFactory.SQRBodyType;
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
        obj.put("data", response);
        obj.put("estado", "exito");
    }

    public static void registro(JSONObject obj, SSSessionAbstract session) {
        try {
            JSONObject data = obj.getJSONObject("data");
            SQR qr = new SQR(data.getString("content"));
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
            e.printStackTrace();
        }
    }

}
