package org.snowyegret.geom.solid;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.apache.commons.lang3.Range;

public class Ball extends Solid {

	private final double radius;

	public Ball(Point3d origin, Point3d pointOnEdge) {
		super(origin);

		Vector3d d = new Vector3d();
		d.sub(origin, pointOnEdge);

		radius = Math.abs(d.length());
		rT = Range.between(-radius, radius);
		rU = Range.between(-radius, radius);
		rV = Range.between(-radius, radius);
	}

	@Override
	public boolean contains(Point3d p) {
		// Does not work with a random origin
		// p.sub(p0);
		// p0.sub(p);
		 //return p.x*p.x + p.y*p.y + p.z*p.z <= radius * radius;
		return Math.pow((p.x - p0.x), 2) + Math.pow((p.y - p0.y), 2) + Math.pow((p.z - p0.z), 2) <= radius * radius;
	}

	@Override
	public String toString() {
		return "Ball [radius=" + radius + ", rangeT=" + rT + ", rangeU=" + rU + ", rangeV=" + rV + ", p0="
				+ p0 + "]";
	}



}
