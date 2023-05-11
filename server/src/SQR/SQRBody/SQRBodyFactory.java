package SQR.SQRBody;

public class SQRBodyFactory {
    public enum SQRBodyType {
        Default,
        Dot,
        Rounded,
        RoundedConectHorizontal,
        RoundedConectVertical,
        RoundedConectVH,
        Circle,
        CircleConectHorizontal,
        CircleConectVertical,
        CircleConectVH,
        Square,
        SquareDot,
        Diamond,
        DiamondConectHorizontal,
        DiamondConectVertical,
        DiamondConectVH,
    }

    public static SQRBody create(SQRBodyType type) {
        switch (type) {
            case Default:
                return new Default();
            case Rounded:
                return new Rounded();
            case RoundedConectHorizontal:
                return new RoundedConectHorizontal();
            case RoundedConectVertical:
                return new RoundedConectVertical();
            case RoundedConectVH:
                return new RoundedConectVH();
            case Circle:
                return new Circle();
            case SquareDot:
                return new SquareDot();
            case Square:
                return new Square();
            case Diamond:
                return new Diamond();
            case CircleConectHorizontal:
                return new CircleConectHorizontal();
            case CircleConectVertical:
                return new CircleConectVertical();
            case CircleConectVH:
                return new CircleConectVH();
            case DiamondConectHorizontal:
                return new DiamondConectHorizontal();
            case DiamondConectVertical:
                return new DiamondConectVertical();
            case DiamondConectVH:
                return new DiamondConectVH();
            case Dot:
                return new Dot();
            default:
                return null;
        }
    }
}
