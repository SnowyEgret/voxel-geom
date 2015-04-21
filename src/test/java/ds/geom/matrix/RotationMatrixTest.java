package ds.geom.matrix;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.assertTrue;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.junit.Test;

import ds.geom.test.GeomTest;

public class RotationMatrixTest extends GeomTest {

	@Test
	public void test() {
		for (int i = 0; i < 100; i++) {
			Vector3d v0 = vector();
			Vector3d v1 = vector();
			Point3d p0 = p();
			Point3d p1 = new Point3d(p0);
			Matrix4d m = new RotationMatrix(v0, v1);
			m.transform(p1);
			m.invert();
			m.transform(p1);
			//assertTrue(p0.epsilonEquals(p1, epsilon));
			assertThat(p1.x, closeTo(p0.x, epsilon));
			assertThat(p1.y, closeTo(p0.y, epsilon));
			assertThat(p1.z, closeTo(p0.z, epsilon));
		}
	}

}
