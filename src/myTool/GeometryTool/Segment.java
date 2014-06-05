package myTool.GeometryTool;

/**
 * Created with IntelliJ IDEA.
 * User: Nguyen Trung Hieu
 * Date: 10/3/12
 * Time: 12:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class Segment {
    public final Point a;
    public final Point b;

    private double distance = Double.NaN;
    private Line line = null;

    public Segment(Point a, Point b) {
        this.a = a;
        this.b = b;
    }

    public boolean contains(Point point, boolean includeEnds) {
        if (a.equals(point) || b.equals(point))
            return includeEnds;
        if (a.equals(b))
            return false;
        Line line = line();
        if (!line.contains(point))
            return false;
        Line perpendicular = line.perpendicular(a);
        double aValue = perpendicular.value(a);
        double bValue = perpendicular.value(b);
        double pointValue = perpendicular.value(point);
        return aValue < pointValue && pointValue < bValue || bValue < pointValue && pointValue < aValue;
    }

    public Point[] intersect(Circle circle) {
        Point[] result = line().intersect(circle);
        if (result.length == 0)
            return result;
        if (result.length == 1) {
            if (contains(result[0], true))
                return result;
            return new Point[0];
        }
        if (contains(result[0], true)) {
            if (contains(result[1], true))
                return result;
            return new Point[]{result[0]};
        }
        if (contains(result[1], true))
            return new Point[]{result[1]};
        return new Point[0];
    }


    public Point middle() {
        return new Point((a.x + b.x) / 2, (a.y + b.y) / 2);
    }

    public double distance(Point point) {
        double length = length();
        double left = point.distance(a);
        if (length < GeometryUtils.epsilon)
            return left;
        double right = point.distance(b);
        if (left * left > right * right + length * length)
            return right;
        if (right * right > left * left + length * length)
            return left;
        return point.distance(line());
    }
    public double length() {
        if (Double.isNaN(distance))
            distance = a.distance(b);
        return distance;
    }

    public Line line() {
        if (line == null)
            line = a.line(b);
        return line;
    }

    public Point[] intersect(Segment other) {
        Point[] answer = new Point[1];
        double x1 = this.a.x; double x2 = this.b.x;
        double y1 = this.a.y; double y2 = this.b.y;
        double x3 = other.a.x; double x4 = other.b.x;
        double y3 = other.a.y; double y4 = other.b.y;
        double d = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);
        if (Math.abs(d) <= GeometryUtils.epsilon) return new Point[0];

        double xi = ((x3-x4)*(x1*y2-y1*x2)-(x1-x2)*(x3*y4-y3*x4))/d;
        double yi = ((y3-y4)*(x1*y2-y1*x2)-(y1-y2)*(x3*y4-y3*x4))/d;

        Point p = new Point(xi,yi);
        if (GeometryUtils.epsilon < Math.min(x1,x2) - xi || xi - Math.max(x1,x2) > GeometryUtils.epsilon) return new Point[0];
        if (GeometryUtils.epsilon < Math.min(x3,x4) - xi || xi - Math.max(x3,x4) > GeometryUtils.epsilon) return new Point[0];
        answer[0] = p;
        return answer;
    }
}
