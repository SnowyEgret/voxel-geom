package ds.geom.surface;

import javax.vecmath.Point3d;

import org.apache.commons.lang3.Range;

public class Sphere extends Surface {

	private final double radius;

	public Sphere(Point3d origin, Point3d pointOnSurface, boolean isHemisphere) {
		super(origin);
		//radius = Math.abs(origin.distance(pointOnSurface));
		//radius = origin.distance(pointOnSurface);
		radius = pointOnSurface.distance(origin);
		rU = Range.between(0d, 2 * Math.PI);
		rV = Range.between(0d, isHemisphere ? Math.PI / 2 : Math.PI);
	}

	// http://www.wolframalpha.com/input/?ordinal=sphere&lk=4
	@Override
	public Point3d pointAtParameters(double u, double v) {
		double x = p0.x + radius * Math.cos(u) * Math.sin(v);
		double y = p0.y + radius * Math.cos(v);
		double z = p0.z + radius * Math.sin(u) * Math.sin(v);
		return new Point3d(x, y, z);
	}

	@Override
	public boolean contains(Point3d p) {
		return Math.pow((p.x - p0.x), 2) + Math.pow((p.y - p0.y), 2) + Math.pow((p.z - p0.z), 2) - radius * radius < epsilon;
	}

}
