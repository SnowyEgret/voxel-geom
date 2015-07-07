package org.snowyegret.geom.curve;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.vecmath.Point3d;

import org.junit.Test;
import org.snowyegret.geom.PointSet;
import org.snowyegret.geom.Primitive;
import org.snowyegret.geom.curve.Line;
import org.snowyegret.geom.test.GeomTest;

public class LineTest extends GeomTest {
	
	@Test
	public void test() {
		Primitive line = new Line(p(), p());
		PointSet points = line.pointSet();
		System.out.println("[T_Line.test] points.size=" + points.size());
		for (Point3d pt : points) {
			assertThat(line.contains(pt), equalTo(true));
		}
	}
}
