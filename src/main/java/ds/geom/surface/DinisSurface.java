package ds.geom.surface;

import javax.vecmath.Point3d;

import org.apache.commons.lang3.Range;

public class DinisSurface extends Surface {

	double a, b;

	// http://www.wolframalpha.com/input/?ordinal=Dini%27s+surface&lk=3
	public DinisSurface(Point3d origin, Point3d a, Point3d b) {
		super(origin);
		this.a = Math.abs(a.distance(p0));
		this.b = Math.abs(b.distance(p0));
		p0.add(new Point3d(0, 0, 30));
		// this.a = 10d;
		// this.b = 2d;
		// rangeU = Range.between(0d, 4 * Math.PI);
		// rangeV = Range.between(.001, 2d);
		rU = Range.between(0d, 4 * Math.PI);
		rV = Range.between(0d, Math.PI);
	}

	@Override
	public Point3d pointAtParameters(double u, double v) {
		Point3d p = new Point3d();
		p.x = a * Math.cos(u) * Math.sin(v);
		p.y = a * Math.sin(u) * Math.sin(v);
		p.z = a * (Math.cos(v) + Math.log(Math.tan(v / 2))) + b * u;
		p.add(p0);
		return p;
	}

	@Override
	public boolean contains(Point3d p) {
		throw new UnsupportedOperationException("Method 'DinisSurface.contains' not yet implemented");
	}

}
