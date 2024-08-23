package SQR;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.Dimension;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

import SQR.SQRBody.SQRBodyFactory;
import SQR.SQRBody.SQRBodyFactory.SQRBodyType;
import SQR.SQRDetail.SQRDetail;
import SQR.SQRFramework.SQRFramework;
import SQR.SQRFramework.SQRFrameworkFactory;
import SQR.SQRFramework.SQRFrameworkFactory.SQRFrameworkType;
import SQR.SQRHeader.SQRHeader;
import SQR.SQRHeader.SQRHeaderFactory;
import SQR.SQRHeader.SQRHeaderFactory.SQRHeaderType;
import SQR.SQRImage.SQRImage;
import SQR.SQRBody.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;
import java.awt.image.*;
import java.awt.*;
import java.awt.BasicStroke;
import java.awt.geom.Point2D;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SQR {

    public enum SQRContentType {
        text, qr_base64
    }

    final int FINDER_PATTERN_SIZE = 7;
    private String content;
    private QRCode qr;
    private SQRBody body;
    private SQRHeader header;
    private SQRFramework framework;
    private SQRImage imagen;
    private SQRDetail detail;
    private int width;
    private int height;
    public String type_color = "";
    private String colorBackground = "";
    public String colorBody = "#000000";
    public String colorBody2 = "#000000";
    public String colorHeader = "";
    private String colorImageBackground;
    private ErrorCorrectionLevel errorCorrectionLevel;

    public SQR(String content, SQRContentType type) throws Exception {

        switch (type) {
            case text:
                this.content = content;
                break;
            case qr_base64:
                this.content = readQRCodeFromBase64(content);

                break;
            default:
                this.content = content;
                break;

        }
        System.out.println("Contenido del QR: \n" + this.content);
        if (this.content == null) {
            throw new Exception("error");
        }
        this.width = 1000;
        this.height = 1000;
        this.body = SQRBodyFactory.create(SQRBodyType.Default);
        this.header = SQRHeaderFactory.create(SQRHeaderType.Default);
        this.framework = SQRFrameworkFactory.create(SQRFrameworkType.Default);
        this.errorCorrectionLevel = ErrorCorrectionLevel.H;
    }

    public void setBody(SQRBodyType _body) {
        this.body = SQRBodyFactory.create(_body);
    }

    public void setHeader(SQRHeaderType _header) {
        this.header = SQRHeaderFactory.create(_header);
    }

    public void setFramework(SQRFrameworkType _framework) {
        this.framework = SQRFrameworkFactory.create(_framework);
    }

    public void setImagen(SQRImage imagen) {
        this.imagen = imagen;
    }

    public void setDetail(SQRDetail detail) {
        this.detail = detail;
    }

    public void setErrorCorrectionLevel(ErrorCorrectionLevel errorCorrectionLevel) {
        this.errorCorrectionLevel = errorCorrectionLevel;
    }

    public QRCode createQr() {
        Hashtable<EncodeHintType, Object> qrParam = new Hashtable<EncodeHintType, Object>();
        qrParam.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // qrParam.put(EncodeHintType.MARGIN, 0);
        try {
            this.qr = Encoder.encode(this.content, this.errorCorrectionLevel, qrParam);
            return this.qr;
        } catch (WriterException e) {
            e.printStackTrace();
            this.qr = null;
            return null;
        }
    }

    public void showImage() {
        JFrame frame = new JFrame();
        // frame.setPreferredSize(new java.awt.Dimension(512, 512));
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(toBufferedImage())));
        frame.pack();
        frame.setVisible(true);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void saveImage(String path) {
        try {
            File file = new File(path);
            ImageIO.write(toBufferedImage(), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toB64() {
        String b64 = "";
        try {
            BufferedImage image = toBufferedImage();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            b64 = Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b64;
    }

    public int fw;

    private BufferedImage toBufferedImage() {
        QRCode code = this.qr;
        ByteMatrix input = code.getMatrix();
        if (input == null) {
            throw new IllegalStateException();
        }
        int inputWidth = input.getWidth();
        int inputHeight = input.getHeight();
        int multiple = 50;
        int quietZone = 0;
        int fw = inputWidth * multiple;
        this.fw = fw;
        int fh = inputHeight * multiple;
        BufferedImage image = new BufferedImage(fw, fh,
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (this.colorBackground.length() > 0) {
            graphics.setBackground(Color.decode(this.colorBackground));
            graphics.clearRect(0, 0, fw, fh);

        } else {
            graphics.clearRect(0, 0, fw, fh);
            graphics.setComposite(AlphaComposite.Clear);
            graphics.fillRect(0, 0, fw, fh);
            graphics.setComposite(AlphaComposite.SrcOver);

        }

        int leftPadding = 0;
        int topPadding = 0;

        Utils.setBodyColor(this, graphics);

        for (int i = 0; i < inputWidth; i++) {
            for (int j = 0; j < inputHeight; j++) {
                if (Utils.getFromMatrix(input, i, j) == 1) {
                    this.body.fill(this, graphics, i, j, multiple, i * multiple, j * multiple);
                }
            }
        }
        int circleDiameter = multiple * FINDER_PATTERN_SIZE;
        this.framework.fill(this, graphics, leftPadding, topPadding, circleDiameter, circleDiameter);
        Utils.resetBackgroundColor(graphics);
        this.header.fill(this, graphics, leftPadding, topPadding, circleDiameter, circleDiameter);

        this.framework.fill(this, graphics, leftPadding + (inputWidth - FINDER_PATTERN_SIZE) * multiple, topPadding,
                circleDiameter, circleDiameter);
        Utils.resetBackgroundColor(graphics);
        this.header.fill(this, graphics, leftPadding + (inputWidth - FINDER_PATTERN_SIZE) * multiple, topPadding,
                circleDiameter, circleDiameter);

        this.framework.fill(this, graphics, leftPadding, topPadding + (inputHeight - FINDER_PATTERN_SIZE) * multiple,
                circleDiameter, circleDiameter);
        Utils.resetBackgroundColor(graphics);
        this.header.fill(this, graphics, leftPadding, topPadding + (inputHeight - FINDER_PATTERN_SIZE) * multiple,
                circleDiameter, circleDiameter);

        if (this.imagen != null) {
            this.imagen.fill(this, graphics, inputWidth, multiple);
        }

        if (this.detail != null) {
            return this.detail.fill(this, image);
        }
        return image;

    }

    public String getColorBackground() {
        return colorBackground;
    }

    public String getColorBody() {
        return colorBody;
    }

    public String getColorHeader() {
        return colorHeader;
    }

    public void setColorBackground(String colorBackground) {
        this.colorBackground = colorBackground;
    }

    public void setColorBody(String colorBody) {
        this.colorBody = colorBody;
    }

    public void setColorBody2(String colorBody2) {
        this.colorBody2 = colorBody2;
    }

    public void setColorHeader(String colorHeader) {
        this.colorHeader = colorHeader;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setColorImageBackground(String colorImageBackground) {
        this.colorImageBackground = colorImageBackground;
    }

    public String getColorImageBackground() {
        return colorImageBackground;
    }

    public QRCode getQr() {
        return qr;
    }

    public void setType_color(String type_color) {
        // linear, radial, default
        this.type_color = type_color;
    }

    public String getType_color() {
        return type_color;
    }

    public SQRDetail getDetail() {
        return detail;
    }

    public static String readQRCodeFromBase64(String base64Image) {
        try {
            // Decodifica la imagen base64
            byte[] decodedImageBytes = Base64.getDecoder().decode(base64Image);
            ByteArrayInputStream bis = new ByteArrayInputStream(decodedImageBytes);
            BufferedImage image = ImageIO.read(bis);

            // Lee el contenido del código QR
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Result result = new MultiFormatReader().decode(bitmap);

            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}