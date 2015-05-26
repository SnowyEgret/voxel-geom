package ds.geom.matrix;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import ds.geom.GeomUtil;

public class ReflectionMatrix extends Matrix4d {
	
	public ReflectionMatrix(Point3d p1, Vector3d direction) {
		direction.normalize();
		Matrix4d mt = new TranslationMatrix(new Vector3d(p1));	
		Matrix4d mr = new RotationMatrix(new Vector3d(0, 0, 1), direction);
		setIdentity();
		
		mul(mt);
		mul(mr);
		mul(new ScaleMatrix(new Vector3d(1, 1, -1)));
		mr.invert();
		mul(mr);
		mt.invert();
		mul(mt);
	}

	public ReflectionMatrix(Point3d p1, Point3d p2, Point3d p3) {
		this(p1, GeomUtil.normalToPlane(p1, p2, p3));
	}
	
//	public ReflectionMatrix(Point3d p1, Point3d p2, Point3d p3) {
//		Matrix4d mt = new TranslationMatrix(new Vector3d(p1));
//		Matrix4d mr = new RotationMatrix(new Vector3d(0, 0, 1), GeomUtil.normalToPlane(p1, p2, p3));
//		setIdentity();
//		
//		mul(mt);
//		mul(mr);
//		mul(new ScaleMatrix(new Vector3d(1, 1, -1)));
//		mr.invert();
//		mul(mr);
//		mt.invert();
//		mul(mt);
//	}
}
