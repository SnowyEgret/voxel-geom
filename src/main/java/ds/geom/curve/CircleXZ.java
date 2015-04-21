package ds.geom.curve;

import javax.vecmath.Point3d;

import org.apache.commons.lang3.Range;

public class CircleXZ extends Curve {

	private final double radius;

	//http://math.stackexchange.com/questions/73237/parametric-equation-of-a-circle-in-3d-space
	public CircleXZ(Point3d origin, Point3d pointOnEdge) {
		super(origin);
		//this.p1 = pointOnEdge;
		radius = Math.abs(origin.distance(pointOnEdge));
		rT = Range.between(0d, 2 * Math.PI);
	}

	@Override
	public Point3d pointAtParameter(double t) {
		Point3d p = new Point3d();
		p.x = p0.x + radius * Math.cos(t);
		p.y = p0.y;
		p.z = p0.z + radius * Math.sin(t);
		return p;
	}

	@Override
	public boolean contains(Point3d p) {
		return Math.pow((p.x - p0.x), 2) + Math.pow((p.z - p0.z), 2) - radius * radius < epsilon;
	}
}
