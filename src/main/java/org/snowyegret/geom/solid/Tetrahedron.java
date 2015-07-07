package org.snowyegret.geom.solid;

import javax.vecmath.Point3d;

import org.apache.commons.lang3.Range;

public class Tetrahedron extends Solid {

	private double a;
	private double twoRootA;
	
	private final double fourRoot3 = 4 * Math.sqrt(3);
	private final double threeRoot2 = 3 * Math.sqrt(2);
	private final double eightRoot6 = 8 * Math.sqrt(6);
	private final double root6 = Math.sqrt(6);
	private final double root3 = Math.sqrt(3);

	public Tetrahedron(Point3d origin, Point3d p1) {
		super(origin);
		a = Math.floor(p1.distance(origin) * Math.sqrt(2)); // Floor for shorter toString
		twoRootA = Math.sqrt(2) * a;
		rT = Range.between(0d, a);
		rU = Range.between(0d, a);
		rV = Range.between(0d, a);
	}

	@Override
	public boolean contains(Point3d point) {
		Point3d p = new Point3d(point);
		p.sub(p0);
		if (!(twoRootA + fourRoot3 * p.z >= 0))
			return false;
		if (!(fourRoot3 * p.z <= threeRoot2 * a + eightRoot6 * p.x))
			return false;
		if (!(4 * (root6 * p.x - threeRoot2 * p.y + root3 * p.z) <= threeRoot2 * a))
			return false;
		if (!(4 * (root6 * p.x + threeRoot2 * p.y + root3 * p.z) <= threeRoot2 * a))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tetrahedron [a=" + a + ", rangeT=" + rT + ", rangeU=" + rU + ", rangeV=" + rV + ", p0="
				+ p0 + "]";
	}

}
