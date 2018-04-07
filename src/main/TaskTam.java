package main;

import trunghieu11.geometry.GeometryUtils;
import trunghieu11.utils.io.InputReader;
import trunghieu11.utils.io.OutputWriter;

public class TaskTam {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        out.printLine("Case #" + testNumber + ":");
        double x = in.readDouble();

        if (x <= 1.414213) {
            double delta = 8 - 4 * x * x;
            double a1 = 0;
            double a2 = 0;

            if (Math.abs(delta) <= 0.000001) {
                a1 = a2 = x / 2;
            } else {
                a1 = (2 * x + Math.sqrt(delta)) / 4;
                a2 = (2 * x - Math.sqrt(delta)) / 4;
            }

            double b1 = x - a1;
            double b2 = x - a2;

            double angel = Math.atan2(a1, b1);

            double updateX = 0.5 * (Math.sin(angel));
            double updateY = 0.5 * (Math.cos(angel));

            out.printFormat("%.7f %.7f %.7f\n", updateX, updateY, 0.0);
            out.printFormat("%.7f %.7f %.7f\n", -updateY, updateX, 0.0);
            out.printFormat("%.7f %.7f %.7f\n", 0.0, 0.0, 0.5);
        } else {
            double alpha = Math.acos(x / 2);

            double angel = alpha * 180 / Math.PI;

            double bx = 0;
            double by = 0.5 * Math.cos(alpha);
            double bz = 0.5 * Math.sin(alpha);

            double rx = 0.5 * (Math.sqrt(2) / 2) * Math.sin(alpha);
            double ry = 0.5 * (Math.sqrt(2) / 2) * Math.cos(alpha);
            double rz = Math.sqrt(x * x - (0.5 * Math.cos(alpha)) * (0.5 * Math.cos(alpha)));

            double gx = -ry;
            double gy = rx;
            double gz = -rz;

            out.printFormat("%.7f %.7f %.7f\n", rx, ry, rz);
            out.printFormat("%.7f %.7f %.7f\n", gx, gy, gz);
            out.printFormat("%.7f %.7f %.7f\n", bx, by, bz);
        }
    }
}
