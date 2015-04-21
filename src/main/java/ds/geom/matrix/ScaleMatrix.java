package ds.geom.matrix;

import javax.vecmath.Matrix4d;
import javax.vecmath.Vector3d;

public class ScaleMatrix extends Matrix4d {

	public ScaleMatrix(Vector3d scale) {
		setIdentity();
		setElement(0, 0, scale.x);
		setElement(1, 1, scale.y);
		setElement(2, 2, scale.z);
	}

}
