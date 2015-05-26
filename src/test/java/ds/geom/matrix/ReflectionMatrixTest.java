package ds.geom.matrix;

import static org.junit.Assert.assertTrue;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;

import org.junit.Test;

import ds.geom.test.GeomTest;

public class ReflectionMatrixTest extends GeomTest {

	@Test
	public void constructorPointPointPoint() {
		new ReflectionMatrix(p(), p(), p());
	}

	@Test
	public void constructorPointDirection() {
		Matrix4d mr = new ReflectionMatrix(p0(), v(1,0,0));
		Point3d p = p(2, 0, 0);
		mr.transform(p);
		assertTrue(p.epsilonEquals(p(-2,0,0), epsilon));
	}

	@Test
	public void twiceBackToStart() {
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

	@Test
	public void trivial() {
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
