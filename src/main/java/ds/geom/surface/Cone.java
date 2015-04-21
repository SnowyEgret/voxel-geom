package ds.geom.surface;

import javax.vecmath.Point3d;

import org.apache.commons.lang3.Range;

public class Cone extends Surface {

	double a, h;

	public Cone(Point3d origin, Point3d radius, Point3d height) {
		super(origin);
		a = Math.abs(radius.distance(p0));
		h = Math.abs(height.y - p0.y);
		rU = Range.between(0d, h);
		rV = Range.between(0d, 2 * Math.PI);
	}

	//https://www.wolframalpha.com/input/?ordinal=cone
	@Override
	public Point3d pointAtParameters(double u, double v) {
		Point3d p = new Point3d();
		p.x = (Math.cos(v) * a * (h - u)) / h;
		p.y = u;
		p.z = (Math.sin(v) * a * (h - u)) / h;
		p.add(p0);
		return p;
	}

	@Override
	public boolean contains(Point3d p) {
		throw new UnsupportedOperationException("Method 'Cone.contains' not yet implemented");
	}

}
