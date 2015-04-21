package ds.geom.curve;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.apache.commons.lang3.Range;

//http://www.wolframalpha.com/input/?ordinal=helix
public class Helix extends Curve {

	private final double r, c, l;

	public Helix(Point3d origin, Point3d radius, Point3d height, double length) {
		super(origin);
		r = Math.abs(origin.distance(radius));
		Vector3d h = new Vector3d();
		h.sub(height, origin);
		c = h.y;
		l = length;
		rT = Range.between(0d, Math.PI * 2 * l);
	}

	@Override
	public Point3d pointAtParameter(double t) {
		Point3d p = new Point3d();
		p.x = Math.cos(t) * r;
		p.y = Math.sin(t) * r;
		p.z = t * c;
		p.add(p0);
		return p;
	}

	@Override
	public boolean contains(Point3d p) {
		return p.x * p.x - r * r - p.y * p.y < epsilon && p.x / r - Math.cos(p.z / c) < epsilon;
	}

	@Override
	public String toString() {
		return "Helix [r=" + r + ", c=" + c + ", l=" + l + ", rangeT=" + rT + ", p0=" + p0 + "]";
	}

}
