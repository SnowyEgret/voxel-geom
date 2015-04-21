package ds.geom.solid;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.vecmath.Point3d;

import org.junit.Test;

import ds.geom.PointSet;
import ds.geom.Primitive;
import ds.geom.test.GeomTest;

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
