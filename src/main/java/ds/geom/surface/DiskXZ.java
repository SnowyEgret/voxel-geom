package ds.geom.surface;

import javax.vecmath.Point3d;

import org.apache.commons.lang3.Range;

public class DiskXZ extends Surface {

	private final double radius;

	public DiskXZ(Point3d origin, Point3d pointOnEdge) {
		super(origin);
		radius = Math.abs(origin.distance(pointOnEdge));
		rU = Range.between(0d, 2 * Math.PI);
		rV = Range.between(0d, 2 * Math.PI);
	}

	@Override
	public Point3d pointAtParameters(double u, double v) {
		double x = p0.x + radius * Math.cos(u) * Math.sin(v);
		double y = p0.y;
		double z = p0.z + radius * Math.sin(u) * Math.sin(v);
		return new Point3d(x, y, z);
	}

	@Override
	public boolean contains(Point3d p) {
		return Math.pow((p.x - p0.x), 2) + Math.pow((p.y - p0.y), 2) + Math.pow((p.z - p0.z), 2) - radius * radius < epsilon;
	}
}
