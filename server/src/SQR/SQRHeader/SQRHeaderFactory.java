package SQR.SQRHeader;

public class SQRHeaderFactory {
    public enum SQRHeaderType {
        Default,
        Rounded, Diamond, Circle,
        Leaf
    }

    public static SQRHeader create(SQRHeaderType type) {
        switch (type) {
            case Default:
                return new Default();
            case Rounded:
                return new Rounded();
            case Circle:
                return new Circle();
            case Diamond:
                return new Diamond();
            case Leaf:
                return new Leaf();
            default:
                return null;
        }
    }
}
