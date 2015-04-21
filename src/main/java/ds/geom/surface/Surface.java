package ds.geom.surface;

import javax.vecmath.Point3d;

import org.apache.commons.lang3.Range;

import ds.geom.PointSet;
import ds.geom.Primitive;

public abstract class Surface extends Primitive {

	protected Range<Double> rU, rV;
	protected double inc = .01;

	public Surface(Point3d origin) {
		super(origin);
	}

	public abstract Point3d pointAtParameters(double u, double v);

	@Override
	public PointSet pointSet() {
		// double inc = .01;
		PointSet points = new PointSet();
		// for (double u = rU.getMinimum(); u <= rU.getMaximum(); u += inc) {
		for (double u = rU.getMinimum(); rU.getMaximum() - u > epsilon; u += inc) {
			// for (double v = rV.getMinimum(); v <= rV.getMaximum(); v += inc) {
			for (double v = rV.getMinimum(); rV.getMaximum() - v > epsilon; v += inc) {
				//System.out.println("[Surface.pointSet] u=" + u);
				Point3d p = pointAtParameters(u, v);
				points.addPoint(p);
			}
		}
		return points;
	}

}
