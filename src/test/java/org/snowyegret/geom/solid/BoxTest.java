package org.snowyegret.geom.solid;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.vecmath.Point3d;

import org.junit.Test;
import org.snowyegret.geom.PointSet;
import org.snowyegret.geom.Primitive;
import org.snowyegret.geom.solid.Box;
import org.snowyegret.geom.test.GeomTest;

public class BoxTest extends GeomTest {

	@Test
	public void test() {
		Primitive p = new Box(p(), p());
		System.out.println("[T_Box.test] p=" + p);
		PointSet points = p.pointSet();
		System.out.println("[T_Box.test] points.size()=" + points.size());
		//new Viewer(points);
		for (Point3d pt : points) {
			assertThat(p.contains(pt), equalTo(true));
		}
	}
}
