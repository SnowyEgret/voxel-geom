package org.snowyegret.geom.surface;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.vecmath.Point3d;

import org.junit.Test;
import org.snowyegret.geom.PointSet;
import org.snowyegret.geom.surface.Torus;
import org.snowyegret.geom.test.GeomTest;

public class TorusTest extends GeomTest {

	@Test
	public void doNothing() {
	}

	//@Test
	public void test() {
		Torus t = new Torus(p(), new Point3d(100, 0, 0), new Point3d(50, 0, 0));
		PointSet points = t.pointSet();
		//new Viewer(points);
		for (Point3d p : points) {
			assertThat(t.contains(p), equalTo(true));
		}
	}

}
