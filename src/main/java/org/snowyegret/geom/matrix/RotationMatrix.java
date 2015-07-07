package org.snowyegret.geom.matrix;

import javax.vecmath.AxisAngle4d;
import javax.vecmath.Matrix4d;
import javax.vecmath.Vector3d;

import org.snowyegret.geom.GeomUtil;

public class RotationMatrix extends Matrix4d {

	public RotationMatrix(Vector3d v1, Vector3d v2) {
		// System.out.println("v1=" + v1);
		setIdentity();
		double angle = v1.angle(v2);
		Vector3d axis = new Vector3d();
		if (Math.abs(angle - Math.PI) < .0000001 || Math.abs(angle - 0) < .0000001) {
			//System.out.println("[RotationMatrix] Vectors are parallel. angle=" + angle);
			axis = GeomUtil.anyOrthogonalVector(v1);
		} else {
			axis.cross(v1, v2);
		}
		axis.normalize();
		// System.out.println(axis);
		setRotation(new AxisAngle4d(axis, angle));
	}
	
	public RotationMatrix(Vector3d axis, double angle) {
		axis.normalize();
		setRotation(new AxisAngle4d(axis, angle));
	}
}
