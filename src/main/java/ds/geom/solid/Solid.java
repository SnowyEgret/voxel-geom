package ds.geom.solid;

import javax.vecmath.Point3d;

import org.apache.commons.lang3.Range;

import ds.geom.PointSet;
import ds.geom.Primitive;

public abstract class Solid extends Primitive {
	
	protected Range<Double> rT, rU, rV;

	
	public Solid(Point3d origin) {
		super(origin);
	}

	@Override
	public PointSet pointSet() {
		double inc = 1d;
		PointSet points = new PointSet();
		for (double t = rangeT().getMinimum(); t <= rangeT().getMaximum(); t += inc) {
			for (double u = rangeU().getMinimum(); u <= rangeU().getMaximum(); u += inc) {
				for (double v = rangeV().getMinimum(); v <= rangeV().getMaximum(); v += inc) {
					//Point3d p = new Point3d(t, v, u);
					Point3d p = new Point3d(t, u, v);
					p.add(p0);
					if (contains(p))
						points.addPoint(p);
				}
			}
		}
		return points;
	}

	public Range<Double> rangeT() {
		return rT;
	}

	public Range<Double> rangeU() {
		return rU;
	}

	public Range<Double> rangeV() {
		return rV;
	}

}
