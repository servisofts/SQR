package SQR.SQRDetail;

import java.awt.Graphics2D;

import org.json.JSONObject;

public abstract class SQRDetailAbstract implements SQRDetail {
    JSONObject data;

    @Override
    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getParam(String key) {
        if (this.data.has(key)) {
            return this.data.get(key).toString();
        }
        return "{" + key + "}";
    }

    public boolean hasParam(String key) {
        return this.data.has(key);
    }
}
