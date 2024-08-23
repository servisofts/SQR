package SQR.SQRDetail;

import org.json.JSONObject;

public class SQRDetailFactory {
    public enum SQRDetailType {
        Default, Glosa, Banco1
    }

    public static SQRDetail create(SQRDetailType type, JSONObject data) {
        SQRDetail sqrd;
        switch (type) {
            case Default:
                sqrd = new Default();
                break;
            case Glosa:
                sqrd = new Glosa();
                break;
            case Banco1:
                sqrd = new Banco1();
                break;
            default:
                sqrd = new Default();
                break;
        }
        sqrd.setData(data);
        return sqrd;
    }
}
