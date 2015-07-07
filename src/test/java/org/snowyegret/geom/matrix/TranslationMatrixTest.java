package org.snowyegret.geom.matrix;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.junit.Test;
import org.snowyegret.geom.matrix.TranslationMatrix;
import org.snowyegret.geom.test.GeomTest;

public class TranslationMatrixTest extends GeomTest {

	@Test
	public void test() {
		for (int i = 0; i < 100; i++) {
			Point3d p0 = p();
			Point3d p1 = p();
			Point3d p2 = p();
			Point3d p3 = new Point3d(p2);
			Vector3d v = new Vector3d();
			v.sub(p0, p1);
			Matrix4d m = new TranslationMatrix(v);
			m.transform(p3);
			assertThat(p2.distance(p3), closeTo(p0.distance(p1), epsilon));			
		}
	}

}
