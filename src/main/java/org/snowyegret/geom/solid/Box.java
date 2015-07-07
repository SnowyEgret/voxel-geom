package org.snowyegret.geom.solid;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.apache.commons.lang3.Range;
import org.snowyegret.geom.curve.Rectangle;

public class Box extends Solid {

	@Deprecated
	public Box(Point3d origin, Point3d diagonalCorner) {
		super(origin);
		Vector3d d = new Vector3d();
		d.sub(diagonalCorner, origin);
		rT = Range.between(0d, d.x);
		rU = Range.between(0d, d.y);
		rV = Range.between(0d, d.z);
	}

	public Box(Point3d origin, Point3d diagonalCorner, boolean isCube) {
		super(origin);
		if (isCube) {
			diagonalCorner.y = origin.y;
			diagonalCorner = Rectangle.squareOf(p0, diagonalCorner);
			diagonalCorner.y += Math.abs(diagonalCorner.x - origin.x);
		}
		Vector3d d = new Vector3d();
		d.sub(diagonalCorner, origin);
		rT = Range.between(0d, d.x);
		rU = Range.between(0d, d.y);
		rV = Range.between(0d, d.z);
	}

	// See Rectangle.squareOf()
	// FIXME Not always correct
//	private Point3d cubeOf(Point3d p0, Point3d p1) {
//		Point3d p = new Point3d();
//		p.sub(p1, p0);
//		double d = greatestComponentOf(p);
//		// p.x = (p1.x >= p0.x) ? d : -d;
//		// p.y = (p1.y >= p0.y) ? d : -d;
//		// p.z = (p1.z >= p0.z) ? d : -d;
//		p.x = (p1.x >= p0.x) ? d : -d;
//		p.y = (p1.y >= p0.y) ? d : -d;
//		p.z = (p1.z >= p0.z) ? d : -d;
//		p.add(p0);
//		return p;
//	}
//
//	private double greatestComponentOf(Point3d p) {
//		double max = (Math.abs(p.x));
//		if (Math.abs(p.y) > max)
//			max = p.y;
//		if (Math.abs(p.z) > max)
//			max = p.z;
//		return max;
//	}

	@Override
	public boolean contains(Point3d point) {
		// Point3d p = new Point3d(point);
		// p.sub(p0);
		return true;
		// return rangeT().contains(p.x) && rangeU().contains(p.y) && rangeV().contains(p.z);
	}

	@Override
	public String toString() {
		return "Box [rangeT=" + rT + ", rangeU=" + rU + ", rangeV=" + rV + ", p0=" + p0 + "]";
	}

}
