package org.snowyegret.geom.curve;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.vecmath.Point3d;

import org.junit.Test;
import org.snowyegret.geom.PointSet;
import org.snowyegret.geom.Primitive;
import org.snowyegret.geom.test.GeomTest;

public class CircleXZTest extends GeomTest {
	
	@Test
	public void test() {
		Primitive p = circleXY(p());
		PointSet points = p.pointSet();
		//new Viewer(points);
		for (Point3d pt : points) {
			assertThat(p.contains(pt), equalTo(true));
		}
	}
}
