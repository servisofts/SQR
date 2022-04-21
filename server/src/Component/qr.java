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
            if (data.has("body")) {
                qr.setBody(SQRBodyType.valueOf(data.getString("body")));
            }
            if (data.has("header")) {
                qr.setHeader(SQRHeaderType.valueOf(data.getString("header")));
            }
            if (data.has("framework")) {
                qr.setFramework(SQRFrameworkType.valueOf(data.getString("framework")));
            }
            if (data.has("colorBackground")) {
                qr.setColorBackground(data.getString("colorBackground"));
            }
            if (data.has("colorBody")) {
                qr.setColorBody(data.getString("colorBody"));
            }
            if (data.has("colorHeader")) {
                qr.setColorHeader(data.getString("colorHeader"));
            }
            if (data.has("colorImageBackground")) {
                qr.setColorImageBackground(data.getString("colorImageBackground"));
            }
            if (data.has("image")) {
                qr.setImagen(new SQRImage(data.getString("image")));
            }
            data.put("b64", qr.toB64());
            obj.put("estado", "exito");
        } catch (Exception e) {
            obj.put("estado", "error");
            e.printStackTrace();
        }
    }

}
