package ds.geom.matrix;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.junit.Test;

import ds.geom.test.GeomTest;

public class ScaleMatrixTest extends GeomTest {

	@Test
	public void test() {
		for (int i = 0; i < 100; i++) {
			// Two identical random points
			Point3d p0 = p();
			Point3d p1 = p(p0);
			// A random vector
			Vector3d v = v();
			Matrix4d m = new ScaleMatrix(v);
			m.transform(p1);
			assertThat(p1.x, closeTo(p0.x * v.x, epsilon));
			assertThat(p1.y, closeTo(p0.y * v.y, epsilon));
			assertThat(p1.z, closeTo(p0.z * v.z, epsilon));
		}
	}

}
