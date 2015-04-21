package ds.geom.curve;

import javax.vecmath.Point3d;
import javax.vecmath.Point3i;
import javax.vecmath.Vector3d;

import ds.geom.surface.InfinitePlane;

public class InfiniteLine extends Curve {

	public Point3d p1;
	protected Vector3d direction;

	public InfiniteLine(Point3d origin, Point3d p1) {
		super(origin);
		this.p1 = p1;
		direction = new Vector3d();
		direction.sub(p0, p1);
		direction.normalize();
	}

	@Override
	public boolean contains(Point3d p) {
		Vector3d vP = new Vector3d();
		vP.sub(p0, p);
		vP.normalize();
		double d = Math.abs(vP.dot(direction)) - 1;
		// System.out.println("[Line.contains] d=" + d);
		return (d < epsilon);
	}

	public Vector3d direction() {
		return new Vector3d(direction);
	}

	public double distancePerp(Point3d point) {
		throw new UnsupportedOperationException("Method 'InfiniteLine.distancePerp' not yet implemented");
	}

	public double distancePerp(Point3i p) {
		return distancePerp(new Point3d(p.x, p.y, p.z));
	}

	public Point3d intersectPlane(InfinitePlane infinitePlane) {
		return infinitePlane.intersectLine(this);
	}

	// From JavaGeom math.geom3d.line.StraightLine3D
	// Gets the point from a parametric representation of the curve
	@Override
	public Point3d pointAtParameter(double t) {
		double x = p0.x + t * direction.x;
		double y = p0.y + t * direction.y;
		double z = p0.z + t * direction.z;
		return new Point3d(x, y, z);
	}

	public Point3d project(Point3d point) {
		throw new UnsupportedOperationException("Method 'InfiniteLine.project' not yet implemented");
	}
}
