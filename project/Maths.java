public class Maths  
{
    public static double predictionFunction(double angle, double startPosition, double predictionPoint) {
        angle = Math.toRadians(angle);
        double a = Math.sin(-angle) / Math.cos(-angle);
        double count = a * predictionPoint + 1 - startPosition;
        double flipper = Math.pow(-1, Math.floor(count));
        double result = trueModulo(count, 1) * flipper + 0.5 + 0.5 * (-1) * flipper;
        return result;
    }
    
    public static double trueModulo(double a, double b) { return a - b * Math.floor(a / b); }
}