package ds.geom;

import javax.vecmath.AxisAngle4d;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Point3i;
import javax.vecmath.Vector3d;

public class GeomUtil {

	public static double angleBetweenPoints(Point3d center, Point3d from, Point3d to) {
		Vector3d vFrom = new Vector3d(from);
		Vector3d vTo = new Vector3d(to);
		vFrom.sub(center);
		vTo.sub(center);
		return vFrom.angle(vTo);
	}

	public static Vector3d normalToPlane(Point3d center, Point3d from, Point3d to) {
		Vector3d vFrom = new Vector3d(from);
		Vector3d vTo = new Vector3d(to);
		vFrom.sub(center);
		vTo.sub(center);
		Vector3d normal = new Vector3d();
		normal.cross(vFrom, vTo);
		normal.normalize();
		return normal;
	}

	public static Point3d pointOnLineClosestToPoint(Point3d p, Point3d l1, Point3d l2) {
		Vector3d vp = new Vector3d(p);
		Vector3d vl = new Vector3d(l2);
		vp.sub(l1);
		vl.sub(l1);
		vl.normalize();
		double d = vp.dot(vl);
		vl.scale(d);
		l1.add(new Point3d(vl.x, vl.y, vl.z));
		return l1;
	}

	public static double distancePerpendicularFromPointToLine(Point3d p, Point3d l1, Point3d l2) {
		Point3d P = GeomUtil.pointOnLineClosestToPoint(p, l1, l2);
		Vector3d vP = new Vector3d(P);
		vP.sub(p);
		return vP.length();
	}

	@Deprecated
	public static Matrix4d newTranslationMatrix(Point3d from, Point3d to) {
		Vector3d v = new Vector3d();
		v.sub(to, from);
		return newTranslationMatrix(v);
	}

	@Deprecated
	public static Matrix4d newTranslationMatrix(Vector3d v) {
		Matrix4d m = new Matrix4d();
		m.setIdentity();
		m.setTranslation(v);
		return m;
	}

	// Creates a matrix which reflects in either the XY or YZ plane depending largest component
	@Deprecated
	public static Matrix4d newReflectionMatrix(Point3d centroid, Point3d p) {
		Vector3d v = new Vector3d();
		v.sub(centroid, p);
		System.out.println("[GeomUtil.newReflectionMatrix] v.x=" + v.x);
		System.out.println("[GeomUtil.newReflectionMatrix] v.z=" + v.z);
		// Ground plane in Minecraft is XZ
		Point3d p1 = null, p2 = null, p3 = null;
		if (Math.abs(v.x) > Math.abs(v.z)) {
			p1 = new Point3d(v.x, 0, 0);
			p2 = new Point3d(v.x, 1, 0);
			p3 = new Point3d(v.x, 0, 1);
		} else {
			p1 = new Point3d(0, 0, v.z);
			p2 = new Point3d(1, 0, v.z);
			p3 = new Point3d(0, 1, v.z);
		}
		p1.add(centroid);
		p2.add(centroid);
		p3.add(centroid);			
		return newReflectionMatrix(p1, p2, p3);
	}

	// http://www.moreprocess.com/computer-graphics/reflection-of-a-point-with-respect-to-an-arbitrary-plane-in-3d
	// http://onyx.boisestate.edu/~tcole/cs496/java3d/demo/ch7/composition/Mirror.java
	@Deprecated
	public static Matrix4d newReflectionMatrix(Point3d p1, Point3d p2, Point3d p3) {

		Matrix4d mT = newTranslationMatrix(new Vector3d(p1));
		Matrix4d mR = newRotationMatrix(new Vector3d(0, 0, 1), normalToPlane(p1, p2, p3));

		Matrix4d m = new Matrix4d();
		m.setIdentity();
		m.mul(mT);
		m.mul(mR);
		m.mul(newScaleMatrix(new Vector3d(0, 0, -1)));
		mR.invert();
		m.mul(mR);
		mT.invert();
		m.mul(mT);

		return m;
	}

	@Deprecated
	public static Matrix4d newRotationMatrix(Vector3d v1, Vector3d v2) {
		Matrix4d m = new Matrix4d();
		m.setIdentity();
		double angle = v1.angle(v2);
		Vector3d axis = new Vector3d();
		axis.cross(v1, v2);
		axis.normalize(); //
		m.setRotation(new AxisAngle4d(axis, angle));
		return m;
	}

	@Deprecated
	public static Matrix4d newScaleMatrix(Point3d origin, Point3d from, Point3d to) {
		throw new UnsupportedOperationException("Method 'GeomUtil.newScaleMatrix' not yet implemented");
	}

	@Deprecated
	public static Matrix4d newScaleMatrix(Vector3d scale) {
		Matrix4d m = new Matrix4d();
		m.setIdentity();
		m.setElement(0, 0, scale.x);
		m.setElement(1, 1, scale.y);
		m.setElement(2, 2, scale.z);
		return m;
	}

	@Deprecated
	public static Matrix4d newRotX90Matrix(Point3d origin) {
		Point3d from = new Point3d(origin);
		Point3d to = new Point3d(origin);
		from.y += 1;
		to.z += 1;
		return newRotationMatrix(origin, from, to);
	}

	@Deprecated
	public static Matrix4d newRotX180Matrix(Point3d origin) {
		Point3d from = new Point3d(origin);
		Point3d to = new Point3d(origin);
		from.y += 1;
		to.y += -1;
		return newRotationMatrix(origin, from, to);
	}

	@Deprecated
	public static Matrix4d newRotX270Matrix(Point3d origin) {
		Point3d from = new Point3d(origin);
		Point3d to = new Point3d(origin);
		from.y += -1;
		to.z += 1;
		return newRotationMatrix(origin, from, to);
	}

	@Deprecated
	public static Matrix4d newRotZ90Matrix(Point3d center) {
		Point3d from = new Point3d(center);
		Point3d to = new Point3d(center);
		from.x += 1;
		to.y += 1;
		return newRotationMatrix(center, from, to);
	}

	@Deprecated
	public static Matrix4d newRotY90Matrix(Point3d center) {
		Point3d from = new Point3d(center);
		Point3d to = new Point3d(center);
		from.x += 1;
		to.z += 1;
		return newRotationMatrix(center, from, to);
	}

	@Deprecated
	public static Matrix4d newRotationMatrix(Point3d center, Point3d from, Point3d to) {

		Matrix4d mR = new Matrix4d();
		mR.setIdentity();
		double angle = angleBetweenPoints(center, from, to);
		Vector3d axis = normalToPlane(center, from, to);
		axis.normalize();
		mR.setRotation(new AxisAngle4d(axis, angle));

		Matrix4d mT = new Matrix4d();
		mT.setIdentity();
		mT.setTranslation(new Vector3d(center));

		Matrix4d m = new Matrix4d();
		m.setIdentity();
		m.mul(mT);
		m.mul(mR);
		mT.invert();
		m.mul(mT);

		return m;
	}

	public static Point3i toPoint3i(Point3d p) {
		return new Point3i((int) Math.round(p.x), (int) Math.round(p.y), (int) Math.round(p.z));
	}

	public static Point3d toPoint3d(Point3i p) {
		return new Point3d(p.x, p.y, p.z);
	}

}
