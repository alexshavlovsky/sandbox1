package voronoi;

import geometry.Line2D;
import geometry.LineCommon;
import geometry.Point;

import static geometry.Utils.*;


public class Edge extends LineCommon {
    private Point p1, p2;
    Point o1, o2;
    Point i1, i2;

    Edge(Point p1, Point p2) {
        super(p1.x - p2.x, p1.y - p2.y);
        this.p1 = p1;
        this.p2 = p2;
        Point mp = getMidPoint(p1, p2);
        double n = Math.sqrt(A * A + B * B);
        A = A / n;
        B = B / n;
        C = -(mp.x * A + mp.y * B);
        o1 = getInfiniteOrigin(mp, this);
        o2 = getNegInfiniteOrigin(mp, this);
        i1 = o1;
        i2 = o2;
    }

    Edge(Point p1, Point p2, Edge e0) {
        this(p1, p2);
        if (e0 != null) this.o1 = e0.o2;
    }

    double getDistToOrigin(Point p) {
        if (p == null) return Double.POSITIVE_INFINITY;
        double dx = p.x - o1.x;
        double dy = p.y - o1.y;
        return -B * dx + A * dy;
    }

    Point getOpposite(Point p) {
        return p1 == p ? p2 : p1;
    }

    Line2D toShiftedLine(Point p0, double k) {
        return new Line2D(shiftPointToPoint(o1, p0, k), shiftPointToPoint(o2, p0, k));
    }

    Point getIntersection(Edge edge) {
        return edge == null ? (o2) : getLinesIntersectionPoint(this, edge);
    }

    double getCrossDenWithEdge(Edge e) {
        return crossDen(-B, A, -e.B, e.A);
    }

}
