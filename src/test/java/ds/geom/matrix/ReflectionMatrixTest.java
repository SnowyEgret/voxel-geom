package ds.geom.matrix;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;

import org.junit.Test;

import ds.geom.test.GeomTest;

public class ReflectionMatrixTest extends GeomTest {

	@Test
	public void doNothing() {

	}

	// @Test
	public void test() {
		Point3d p1 = p();
		Point3d p2 = p();
		Point3d p3 = p();
		Point3d p4 = p();
		Point3d p5 = new Point3d(p4);
		Matrix4d m = new ReflectionMatrix(p1, p2, p3);
		m.transform(p5);
		m.invert();
		m.transform(p5);
		// assertTrue(p3.epsilonEquals(p4, epsilon));
		assertThat(p4.x, closeTo(p5.x, epsilon));
		assertThat(p4.y, closeTo(p5.y, epsilon));
		assertThat(p4.z, closeTo(p5.z, epsilon));
	}

}
