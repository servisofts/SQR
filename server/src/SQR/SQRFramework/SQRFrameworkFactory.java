package SQR.SQRFramework;

public class SQRFrameworkFactory {
    public enum SQRFrameworkType {
        Default,
        Rounded,
        Diamond,
        Circle,
        Square
    }

    public static SQRFramework create(SQRFrameworkType type) {
        switch (type) {
            case Default:
                return new Default();
            case Rounded:
                return new Rounded();
            case Circle:
                return new Circle();
            case Diamond:
                return new Diamond();
            case Square:
                return new Square();
            default:
                return null;
        }
    }
}
