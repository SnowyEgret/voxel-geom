package org.snowyegret.geom.curve;

import javax.vecmath.Point3d;

import org.apache.commons.lang3.Range;
import org.snowyegret.geom.PointSet;
import org.snowyegret.geom.Primitive;

public abstract class Curve extends Primitive {

	protected Range<Double> rT;

	public Curve(Point3d origin) {
		super(origin);
	}

	public abstract Point3d pointAtParameter(double t);

	@Override
	public PointSet pointSet() {
		PointSet points = new PointSet();
		//double r = rT.getMaximum() - rT.getMinimum();
		double inc = .00001;
		//System.out.println("[Curve.pointSet] inc=" + inc);
		for (double u = rT.getMinimum(); u <= rT.getMaximum(); u += inc) {
			Point3d p = pointAtParameter(u);
			points.addPoint(p);
		}
		return points;
	}

}
