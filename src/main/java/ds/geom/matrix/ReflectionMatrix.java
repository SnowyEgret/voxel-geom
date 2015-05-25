package ds.geom.matrix;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import ds.geom.GeomUtil;

public class ReflectionMatrix extends Matrix4d {

	//FIXME
	//Points p1, p2, p3 represent a plane around which geometry will be reflected
	//Algorithm:
	//Rotate the reflection plane parallel to the xy plane
	//Translate the reflection plane to the origin
	//Reverse the sign of the point's z coordinate by scaling it by -1
	//Translate the reflection plane back
	//Rotate the reflection plane back
	public ReflectionMatrix(Point3d p1, Point3d p2, Point3d p3) {
		
		Vector3d v = new Vector3d(p1);
		v.negate();
		Matrix4d mt = new TranslationMatrix(v);
		
		Matrix4d mr = new RotationMatrix(new Vector3d(0, 0, 1), GeomUtil.normalToPlane(p1, p2, p3));

		setIdentity();
		mul(mt);
		mul(mr);
		mul(new ScaleMatrix(new Vector3d(1, 1, -1)));
		mr.invert();
		mul(mr);
		mt.invert();
		mul(mt);
	}
}
