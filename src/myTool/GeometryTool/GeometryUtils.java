package myTool.GeometryTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 12:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeometryUtils {
    public static double epsilon = 1e-8;

    public static double fastHypot(double...x) {
        if (x.length == 0)
            return 0;
        else if (x.length == 1)
            return Math.abs(x[0]);
        else {
            double sumSquares = 0;
            for (double value : x)
                sumSquares += value * value;
            return Math.sqrt(sumSquares);
        }
    }

    public static double calculateArea(Point[] points) {
        double answer = 0.0;
        int count = points.length;
        if (count <= 2)
            return 0.0;
        for (int i = 1; i < count; i++)
            answer += (points[i].x - points[i - 1].x) * (points[i].y + points[i].y);
        answer += (points[0].x - points[count - 1].x) * (points[0].y + points[count - 1].y);
        answer /= 2.0;
        if (answer < 0)
            answer = -answer;
        return answer;
    }
}
