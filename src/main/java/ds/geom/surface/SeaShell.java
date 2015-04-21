package ds.geom.surface;

import javax.vecmath.Point3d;

import org.apache.commons.lang3.Range;

public class SeaShell extends Surface {

	// double a = 0.2;
	// double b = 1.0;
	// double c = 0.1;
	// double n = 2.0;

	double k = 40;

	public SeaShell(Point3d origin) {
		super(origin);
		rU = Range.between(0d, 4 * Math.PI);
		rV = Range.between(0d, 4 * Math.PI);
	}

	// //http://mathworld.wolfram.com/Seashell.html
	// @Override
	// public Point3d pointAtParameters(double u, double v) {
	// Point3d p = new Point3d();
	// p.x = ((1 - v / 2 * Math.PI) * (1 + Math.cos(u)) + c) * Math.cos(n * v);
	// p.y = ((1 - v / 2 * Math.PI) * (1 + Math.cos(u)) + c) * Math.cos(n * v);
	// p.z = (b * v) / (2 * Math.PI) + a * Math.sin(u) * (1 - v / (2 * Math.PI));
	// p.add(p0);
	// return p;
	// }

	// http://mathworld.wolfram.com/Seashell.html
	@Override
	public Point3d pointAtParameters(double u, double v) {
		Point3d p = new Point3d();
		p.z = k * (2 * (1 - Math.pow(Math.E, u / (8 * Math.PI))) * Math.cos(u) * Math.cos(v / 2) * Math.cos(v / 2));
		p.x = k * (2 * (-1 + Math.pow(Math.E, u / (8 * Math.PI))) * Math.sin(u) * Math.cos(v / 2) * Math.cos(v / 2));
		p.y = -k
				* (1 - Math.pow(Math.E, u / (4 * Math.PI)) - Math.sin(v) + Math.pow(Math.E, u / (6 * Math.PI))
						* Math.sin(v));
		p.add(p0);
		return p;
	}

	@Override
	public boolean contains(Point3d p) {
		throw new UnsupportedOperationException("Method 'Surface.contains' not yet implemented");
	}

}
