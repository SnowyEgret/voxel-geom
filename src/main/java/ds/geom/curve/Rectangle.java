package ds.geom.curve;

import javax.vecmath.Point3d;

public class Rectangle extends Polyline {

	public Rectangle(Point3d p0, Point3d p2, boolean isSquare) {
		super(p0);
		// Assure the polygon lies flat
		p2.y = p0.y;
		if (isSquare) {
			p2 = squareOf(p0, p2);
		}
		Point3d p1 = new Point3d(p0.x, p2.y, p2.z);
		Point3d p3 = new Point3d(p2.x, p2.y, p0.z);
		lines.add(new Line(p0, p1));
		lines.add(new Line(p1, p2));
		lines.add(new Line(p2, p3));
		lines.add(new Line(p3, p0));
	}

	public static Point3d squareOf(Point3d p0, Point3d p2) {
		double dx = Math.abs(p2.x - p0.x);
		double dz = Math.abs(p2.z - p0.z);
		double d = Math.abs(dz - dx);
		if (dx > dz) {
			p2.z += (p2.z > p0.z) ? d : -d;
		} else {
			p2.x += (p2.x > p0.x) ? d : -d;
		}
		return new Point3d(p2);
	}
}
