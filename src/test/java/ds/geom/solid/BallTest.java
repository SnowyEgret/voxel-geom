package ds.geom.solid;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.vecmath.Point3d;

import org.junit.Test;

import ds.geom.PointSet;
import ds.geom.Primitive;
import ds.geom.test.GeomTest;

public class BallTest extends GeomTest {

	@Test
	public void test() {
		scale = 100;
		Primitive p = ball(p());
		System.out.println("[T_Ball.test] p=" + p);
		PointSet points = p.pointSet();
		System.out.println("[T_Ball.test] points.size=" + points.size());
		for (Point3d pt : points) {
			assertThat(p.contains(pt), equalTo(true));
		}
	}
}
