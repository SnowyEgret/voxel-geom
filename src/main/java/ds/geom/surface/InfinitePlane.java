package ds.geom.surface;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import ds.geom.curve.InfiniteLine;

public class InfinitePlane extends Surface {

	private final Vector3d normal;
	public Point3d p1; // Convenient for testing

	public InfinitePlane(Point3d origin, Point3d p1, Point3d p2) {
		super(origin);
		normal = createNormal(p1, p2);
		this.p1 = p1;
	}

	public final static InfinitePlane XY(Point3d o) {
		return new InfinitePlane(o, new Point3d(o.x + 1, o.y, o.z), new Point3d(o.x, o.y + 1, o.z));
	}

	public final static InfinitePlane XZ(Point3d o) {
		return new InfinitePlane(o, new Point3d(o.x, o.y, o.z + 1), new Point3d(o.x + 1, o.y, o.z));
	}

	public final static InfinitePlane YZ(Point3d o) {
		return new InfinitePlane(o, new Point3d(o.x, o.y + 1, o.z), new Point3d(o.x, o.y, o.z + 1));
	}

	public static InfinitePlane XY() {
		return XY(new Point3d(0, 0, 0));
	}

	public static InfinitePlane XZ() {
		return XZ(new Point3d(0, 0, 0));
	}

	public static InfinitePlane YZ() {
		return YZ(new Point3d(0, 0, 0));
	}

	@Override
	public boolean contains(Point3d p) {
		Vector3d vP = new Vector3d();
		vP.sub(p0, p);
		return (Math.abs(vP.dot(normal)) - 0 < epsilon);
	}

	@Override
	public Point3d pointAtParameters(double u, double v) {
		throw new UnsupportedOperationException("Method 'Plane.pointAtParameters' not yet implemented");
		// ax + by + cz = d
	}

	public Vector3d normal() {
		return new Vector3d(normal);
	}

	public double distancePerp(Point3d p) {
		Vector3d vP = new Vector3d();
		vP.sub(p, p0);
		return normal.dot(vP);
	}

	public Point3d project(Point3d p) {
		double d = distancePerp(p);
		Vector3d n = normal();
		n.scale(d);
		p.sub(n);
		return p;
	}

	public Point3d intersectLine(InfiniteLine infiniteLine) {

		// From JavaGeom math.geom3d.plane.Plane3D
		Vector3d dp = new Vector3d();
		dp.sub(p0, infiniteLine.getOrigin());
		double t = normal.dot(dp) / normal.dot(infiniteLine.direction());

		return infiniteLine.pointAtParameter(t);

		// Vector3d u = new Vector3d();
		// Vector3d w = new Vector3d();
		//
		// u.sub(line.p1, line.p0);
		// u.sub(line.p0, p0);
		// double dot = normal.dot(u);
		//
		// if (Math.abs(dot) > .000001) {
		// double fac = -normal.dot(w) / dot;
		// u.scale(fac);
		// line.p0.add(u);
		// return p0;
		// } else {
		// return null;
		// }

		// http://stackoverflow.com/questions/5666222/3d-line-plane-intersection
		// u = sub_v3v3(p1, p0)
		// w = sub_v3v3(p0, p_co)
		// dot = dot_v3v3(p_no, u)
		//
		// if abs(dot) > epsilon:
		// # the factor of the point between p0 -> p1 (0 - 1)
		// # if 'fac' is between (0 - 1) the point intersects with the segment.
		// # otherwise:
		// # < 0.0: behind p0.
		// # > 1.0: infront of p1.
		// fac = -dot_v3v3(p_no, w) / dot
		// mul_v3_fl(u, fac)
		// return add_v3v3(p0, u)
		// else:
		// # The segment is parallel to plane
		// return None

		// http://math.stackexchange.com/questions/83990/line-and-plane-intersection-in-3d

		/**
		 * Compute intersection of a line with this plane. Uses algorithm 1 given in: <a
		 * href="http://local.wasp.uwa.edu.au/~pbourke/geometry/planeline/">
		 * http://local.wasp.uwa.edu.au/~pbourke/geometry/planeline/</a>.
		 * 
		 * @param line
		 *            the line which intersects the plane
		 * @return the intersection point
		 */
		// public Point3D lineIntersection(StraightLine3D line) {
		// // the plane normal
		// Vector3D n = this.normal();
		//
		// // the difference between origin of plane and origin of line
		// Vector3D dp = new Vector3D(line.origin(), this.origin());
		//
		// // compute ratio of dot products,
		// // see http://local.wasp.uwa.edu.au/~pbourke/geometry/planeline/
		// double t = Vector3D.dotProduct(n, dp)
		// /Vector3D.dotProduct(n, line.direction());
		//
		// return line.point(t);
		// }

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Plane [origin=").append(p0).append(", normal=").append(normal).append("]");
		return builder.toString();
	}

	private Vector3d createNormal(Point3d p1, Point3d p2) {
		Vector3d v1 = new Vector3d();
		Vector3d v2 = new Vector3d();
		v1.sub(p0, p1);
		v2.sub(p0, p2);
		Vector3d vn = new Vector3d();
		vn.cross(v1, v2);
		vn.normalize();
		return vn;
	}

}
