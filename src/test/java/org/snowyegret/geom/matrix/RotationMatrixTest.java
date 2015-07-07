package org.snowyegret.geom.matrix;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.Assert.*;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.junit.Test;
import org.snowyegret.geom.matrix.RotationMatrix;
import org.snowyegret.geom.test.GeomTest;

public class RotationMatrixTest extends GeomTest {

	@Test
	public void randomPointsRotatedOnSameLine() {
		// for (int i = 0; i < 100; i++) {
		Point3d p1 = p();
		Point3d p2 = p();
		Matrix4d m = new RotationMatrix(v(p1), v(p2));
		m.transform(p1);
		Vector3d v1 = v(p1);
		Vector3d v2 = v(p2);
		v1.normalize();
		v2.normalize();
		assertTrue(v1.epsilonEquals(v2, epsilon));
		// }
	}

	@Test
	public void negate() {
		// for (int i = 0; i < 100; i++) {
		Vector3d v1 = v();
		Vector3d v2 = new Vector3d(v1);
		v1.negate();
		v1.negate();
		// System.out.println(v1);
		// System.out.println(v2);
		assertTrue(v1.epsilonEquals(v2, epsilon));
		// }
	}

	@Test
	public void oppositeVectors() {

		Point3d p1 = p();
		Point3d p2 = p(p1);
		p1.negate();

		Matrix4d m = new RotationMatrix(v(p1), v(p2));
		// System.out.println(m);
		m.transform(p1);

		// Fails even though passes
		// assertTrue(p1.epsilonEquals(p2, epsilon));
		assertThat(p1.x, closeTo(p2.x, epsilon));
		assertThat(p1.y, closeTo(p2.y, epsilon));
		assertThat(p1.z, closeTo(p2.z, epsilon));
	}

	@Test
	public void orthogonalVectors() {

		Point3d p1 = p(1, 0, 0);
		Point3d p2 = p(0, 1, 0);

		Vector3d v1 = new Vector3d(p1);
		Vector3d v2 = new Vector3d(p2);
		Matrix4d m = new RotationMatrix(v1, v2);
		m.transform(p1);
		// System.out.println(m);

		assertTrue(p1.epsilonEquals(p2, epsilon));
	}

}
