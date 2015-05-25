package ds.geom.matrix;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.junit.Test;

import ds.geom.GeomUtil;
import ds.geom.test.GeomTest;

public class ReflectionMatrixTest extends GeomTest {

	@Test
	public void constructor() {
		new ReflectionMatrix(p(), p(), p());
	}

	@Test
	public void test() {
		for (int i = 0; i < 100; i++) {
			//System.out.println(i);
			Matrix4d m = new ReflectionMatrix(p(), p(), p());
			Point3d p1 = p();
			Point3d p2 = new Point3d(p1);
			m.transform(p2);
			m.transform(p2);
			assertTrue(p1.epsilonEquals(p2, epsilon));
		}
	}

	// @Test
	public void trivialWithReflectionMatrix() {
		Point3d p = p(2, 0, 0);
		// Point3d p0 = p(p);

		Point3d p1 = p(1, 0, 0);
		Point3d p2 = p(1, 0, 1);
		Point3d p3 = p(1, 1, 0);

		Matrix4d mr = new ReflectionMatrix(p1, p2, p3);
		System.out.println();
		System.out.println(p3i(p));
		mr.transform(p);
		System.out.println(p3i(p));
	}

}
