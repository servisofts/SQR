package SQR;

import com.google.zxing.Dimension;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.ByteMatrix;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;

import SQR.SQRBody.SQRBodyFactory;
import SQR.SQRBody.SQRBodyFactory.SQRBodyType;
import SQR.SQRFramework.SQRFramework;
import SQR.SQRFramework.SQRFrameworkFactory;
import SQR.SQRFramework.SQRFrameworkFactory.SQRFrameworkType;
import SQR.SQRHeader.SQRHeader;
import SQR.SQRHeader.SQRHeaderFactory;
import SQR.SQRHeader.SQRHeaderFactory.SQRHeaderType;
import SQR.SQRImage.SQRImage;
import SQR.SQRBody.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;
import java.awt.image.*;
import java.awt.*;
import java.awt.BasicStroke;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SQR {
    final int FINDER_PATTERN_SIZE = 7;
    private String content;
    private QRCode qr;
    private SQRBody body;
    private SQRHeader header;
    private SQRFramework framework;
    private SQRImage imagen;
    private int width;
    private int height;
    private String colorBackground = "#ffffff";
    private String colorBody = "#000000";
    private String colorHeader = "#000000";
    private String colorImageBackground;

    public SQR(String content) {
        this.content = content;
        this.width = 1024;
        this.height = 1024;
        this.body = SQRBodyFactory.create(SQRBodyType.Default);
        this.header = SQRHeaderFactory.create(SQRHeaderType.Default);
        this.framework = SQRFrameworkFactory.create(SQRFrameworkType.Default);
        this.createQr();
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

    public QRCode createQr() {
        Hashtable<EncodeHintType, Object> qrParam = new Hashtable<EncodeHintType, Object>();
        qrParam.put(EncodeHintType.CHARACTER_SET, "utf-8");
        qrParam.put(EncodeHintType.MARGIN, 0);
        try {
            this.qr = Encoder.encode(this.content, ErrorCorrectionLevel.H, qrParam);
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

    private BufferedImage toBufferedImage() {
        QRCode code = this.qr;
        int quietZone = 0;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setBackground(Color.decode(this.colorBackground));
        graphics.clearRect(0, 0, width, height);
        graphics.setColor(Color.decode(this.colorBody));
        // graphics.setStroke(new BasicStroke(0.3f));
        ByteMatrix input = code.getMatrix();
        if (input == null) {
            throw new IllegalStateException();
        }
        int inputWidth = input.getWidth();
        int inputHeight = input.getHeight();
        int qrWidth = inputWidth + (quietZone * 2);
        int qrHeight = inputHeight + (quietZone * 2);
        int outputWidth = Math.max(width, qrWidth);
        int outputHeight = Math.max(height, qrHeight);
        int multiple = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);
        int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
        int topPadding = (outputHeight - (inputHeight * multiple)) / 2;
        for (int inputY = 0, outputY = topPadding; inputY < inputHeight; inputY++, outputY += multiple) {
            for (int inputX = 0, outputX = leftPadding; inputX < inputWidth; inputX++, outputX += multiple) {
                if (input.get(inputX, inputY) == 1) {
                    if (!(inputX <= FINDER_PATTERN_SIZE && inputY <= FINDER_PATTERN_SIZE ||
                            inputX >= inputWidth - FINDER_PATTERN_SIZE && inputY <= FINDER_PATTERN_SIZE ||
                            inputX <= FINDER_PATTERN_SIZE && inputY >= inputHeight - FINDER_PATTERN_SIZE)) {
                        this.body.fill(this, graphics, outputX, outputY, multiple, multiple);
                    }
                }
            }
        }
        int circleDiameter = multiple * FINDER_PATTERN_SIZE;
        this.framework.fill(this, graphics, leftPadding, topPadding, circleDiameter, circleDiameter);
        this.header.fill(this, graphics, leftPadding, topPadding, circleDiameter, circleDiameter);

        this.framework.fill(this, graphics, leftPadding + (inputWidth - FINDER_PATTERN_SIZE) * multiple, topPadding,
                circleDiameter, circleDiameter);
        this.header.fill(this, graphics, leftPadding + (inputWidth - FINDER_PATTERN_SIZE) * multiple, topPadding,
                circleDiameter, circleDiameter);

        this.framework.fill(this, graphics, leftPadding, topPadding + (inputHeight - FINDER_PATTERN_SIZE) * multiple,
                circleDiameter, circleDiameter);
        this.header.fill(this, graphics, leftPadding, topPadding + (inputHeight - FINDER_PATTERN_SIZE) * multiple,
                circleDiameter, circleDiameter);

        if (this.imagen != null) {
            this.imagen.fill(this, graphics, inputWidth, multiple);
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
}