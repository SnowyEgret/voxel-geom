package org.snowyegret.geom.surface;

import javax.vecmath.Point3d;

import org.apache.commons.lang3.Range;

public class EllipticParaboloid extends Surface {

	double a, b;

	public EllipticParaboloid(Point3d origin, Point3d radius1, Point3d radius2) {
		super(origin);
		a = Math.abs(radius1.distance(p0));
		b = Math.abs(radius2.distance(p0));
		rU = Range.between(0d, 2d);
		rV = Range.between(0d, 2 * Math.PI);
	}

	@Override
	public Point3d pointAtParameters(double u, double v) {
		Point3d p = new Point3d();
		p.x = a * u * Math.cos(v);
		p.y = b * u * Math.sin(v);
		p.z = u * u;
		p.add(p0);
		return p;
	}

	@Override
	public boolean contains(Point3d p) {
		return p.z - Math.pow((p.x - p0.x), 2) / a * a + Math.pow((p.y - p0.y), 2) / b * b < epsilon;
	}

}
