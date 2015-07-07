package org.snowyegret.geom.matrix;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class TranslationMatrix extends Matrix4d {

	public TranslationMatrix(Vector3d v) {
		setIdentity();
		setTranslation(v);
	}
}
