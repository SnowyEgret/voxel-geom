package org.snowyegret.geom.surface;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.vecmath.Point3d;

import org.junit.Test;
import org.snowyegret.geom.PointSet;
import org.snowyegret.geom.Primitive;
import org.snowyegret.geom.surface.Sphere;
import org.snowyegret.geom.test.GeomTest;

public class SphereTest extends GeomTest {

	@Test
	public void test() {
		Primitive p = new Sphere(o(), p(), false);
		PointSet points = p.pointSet();
		for (Point3d pt : points) {
			assertThat(p.contains(pt), equalTo(true));
		}
	}
}
