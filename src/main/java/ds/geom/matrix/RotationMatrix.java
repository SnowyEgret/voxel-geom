package ds.geom.matrix;

import javax.vecmath.AxisAngle4d;
import javax.vecmath.Matrix4d;
import javax.vecmath.Vector3d;

public class RotationMatrix extends Matrix4d {

	public RotationMatrix(Vector3d v1, Vector3d v2) {
		setIdentity();
		double angle = v1.angle(v2);
		Vector3d axis = new Vector3d();
		axis.cross(v1, v2);
		axis.normalize();
		setRotation(new AxisAngle4d(axis, angle));
	}

}
