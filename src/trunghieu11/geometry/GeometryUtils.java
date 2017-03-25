package trunghieu11.geometry;

/**
 * @author Egor Kulikov (kulikov@devexperts.com)
 */
public class GeometryUtils {
    public static double epsilon = 1e-8;

    public static double fastHypot(double... x) {
        if (x.length == 0) {
            return 0;
        } else if (x.length == 1) {
            return Math.abs(x[0]);
        } else {
            double sumSquares = 0;
            for (double value : x) {
                sumSquares += value * value;
            }
            return Math.sqrt(sumSquares);
        }
    }

    public static double fastHypot(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }

    public static double fastHypot(double[] x, double[] y) {
        if (x.length == 0) {
            return 0;
        } else if (x.length == 1) {
            return Math.abs(x[0] - y[0]);
        } else {
            double sumSquares = 0;
            for (int i = 0; i < x.length; i++) {
                double diff = x[i] - y[i];
                sumSquares += diff * diff;
            }
            return Math.sqrt(sumSquares);
        }
    }

    public static double fastHypot(int[] x, int[] y) {
        if (x.length == 0) {
            return 0;
        } else if (x.length == 1) {
            return Math.abs(x[0] - y[0]);
        } else {
            double sumSquares = 0;
            for (int i = 0; i < x.length; i++) {
                double diff = x[i] - y[i];
                sumSquares += diff * diff;
            }
            return Math.sqrt(sumSquares);
        }
    }

    public static double missileTrajectoryLength(double v, double angle, double g) {
        return (v * v * Math.sin(2 * angle)) / g;
    }

    public static double sphereVolume(double radius) {
        return 4 * Math.PI * radius * radius * radius / 3;
    }

    public static double triangleArea(double first, double second, double third) {
        double p = (first + second + third) / 2;
        return Math.sqrt(p * (p - first) * (p - second) * (p - third));
    }

    public static double canonicalAngle(double angle) {
        while (angle > Math.PI) {
            angle -= 2 * Math.PI;
        }
        while (angle < -Math.PI) {
            angle += 2 * Math.PI;
        }
        return angle;
    }

    public static double positiveAngle(double angle) {
        while (angle > 2 * Math.PI - GeometryUtils.epsilon) {
            angle -= 2 * Math.PI;
        }
        while (angle < -GeometryUtils.epsilon) {
            angle += 2 * Math.PI;
        }
        return angle;
    }

    public static double calculateArea(Point[] points) {
        double answer = 0.0;
        int count = points.length;
        if (count <= 2) {
            return 0.0;
        }
        for (int i = 1; i < count; i++) {
            answer += points[i - 1].x * points[i].y - points[i - 1].y * points[i].x;
        }
        answer += points[count - 1].x * points[0].y - points[count - 1].y * points[0].x;
        answer /= 2.0;
        return Math.abs(answer);
    }
}
