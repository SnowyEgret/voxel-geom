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

	// @Test
	public void test() {
		Matrix4d m = new ReflectionMatrix(p(), p(), p());
		Point3d p1 = p();
		Point3d p2 = new Point3d(p1);
		m.transform(p2);
		m.transform(p2);
		// assertTrue(p1.epsilonEquals(p2, epsilon));
		assertThat(p1.x, closeTo(p2.x, epsilon));
		assertThat(p1.y, closeTo(p2.y, epsilon));
		assertThat(p1.z, closeTo(p2.z, epsilon));
	}

	@Test
	public void separateMatrices() {
		Point3d p = p(2, 0, 0);

		Point3d p1 = p(1, 0, 0);
		Point3d p2 = p(1, 0, 1);
		Point3d p3 = p(1, 1, 0);

		Matrix4d mr = new RotationMatrix(new Vector3d(0, 0, 1),
				GeomUtil.normalToPlane(p1, p2, p3));

		Vector3d v = v(p1);
		v.negate();
		Matrix4d mt = new TranslationMatrix(v);

		Matrix4d ms = new ScaleMatrix(new Vector3d(0, 0, -1));

		System.out.println();
		System.out.println(p3i(p));
		mt.transform(p);
		System.out.println(p3i(p));
		mr.transform(p);
		System.out.println(p3i(p));
		ms.transform(p);
		System.out.println(p3i(p));
		mr.invert();
		mr.transform(p);
		System.out.println(p3i(p));
		mt.invert();
		mt.transform(p);
		System.out.println(p3i(p));
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
