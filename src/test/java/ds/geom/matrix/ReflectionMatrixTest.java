package ds.geom.matrix;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.junit.Test;

import ds.geom.test.GeomTest;

public class ReflectionMatrixTest extends GeomTest {

	@Test
	public void constructor() {
		 new ReflectionMatrix(p(), p(), p());
	}

	@Test
	public void negate() {
		Vector3d v1 = new Vector3d(p2);
		v1.negate();
		assertEquals(true, v1.epsilonEquals(new Point3d(-2, -2, -2), epsilon));
	}

	@Test
	public void test() {
		Matrix4d m = new ReflectionMatrix(p(), p(), p());
		Point3d p1 = p();
		Point3d p2 = new Point3d(p1);
		m.transform(p2);
		m.transform(p2);
//		assertEquals(true, p1.epsilonEquals(p2, epsilon));
		assertThat(p1.x, closeTo(p2.x, epsilon));
		assertThat(p1.y, closeTo(p2.y, epsilon));
		assertThat(p1.z, closeTo(p2.z, epsilon));
	}

}
