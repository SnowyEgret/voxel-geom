package org.snowyegret.geom.surface;

import javax.vecmath.Point3d;

import org.apache.commons.lang3.Range;

public class Torus extends Surface {

	private final double c;
	private final double a;

	public Torus(Point3d origin, Point3d radius, Point3d pointOnEdge) {
		super(origin);
		this.c = Math.abs(radius.distance(p0));
		this.a = Math.abs(pointOnEdge.distance(radius));
		rU = Range.between(0d, 2 * Math.PI);
		rV = Range.between(0d, 2 * Math.PI);
	}

	@Override
	// http://www.wolframalpha.com/input/?ordinal=torus
	public Point3d pointAtParameters(double u, double v) {
		double x = p0.x + Math.cos(u) * (Math.cos(v) * a + c);
		double y = p0.y + Math.sin(u) * (Math.cos(v) * a + c);
		double z = p0.z + Math.sin(v) * a;
		return new Point3d(x, y, z);
	}

	@Override
	//http://www.wolframalpha.com/input/?ordinal=torus
	public boolean contains(Point3d p) {
		// Do we have to subtract p0?
		return Math.pow(c - Math.sqrt(p.x * p.x + p.y * p.y), 2) + p.z * p.z - a * a < epsilon;
	}
}
