package ds.geom.matrix;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import ds.geom.GeomUtil;

public class ReflectionMatrix extends Matrix4d {

	//FIXME
	public ReflectionMatrix(Point3d p1, Point3d p2, Point3d p3) {
		Vector3d v = new Vector3d();
		v.sub(p1, new Point3d(0, 0, 0));
		Matrix4d mT = new TranslationMatrix(v);
		Matrix4d mR = new RotationMatrix(new Vector3d(0, 0, 1), GeomUtil.normalToPlane(p1, p2, p3));
		setIdentity();
		mul(mT);
		mul(mR);
		mul(new ScaleMatrix(new Vector3d(0, 0, -1)));
		mR.invert();
		mul(mR);
		mT.invert();
		mul(mT);
	}
}
