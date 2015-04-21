package ds.geom.surface;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.vecmath.Point3d;

import org.junit.Test;

import ds.geom.PointSet;
import ds.geom.Primitive;
import ds.geom.test.GeomTest;

public class DiskXZTest extends GeomTest {

	@Test
	public void constructor() {
		Point3d p0 = p();
		Point3d pEdge = p();
		Primitive p = new DiskXZ(p0, pEdge);
		PointSet points = p.pointSet();
		//new Viewer(points);
		for (Point3d pt : points) {
			assertThat(p.contains(pt), equalTo(true));
		}
	}

}
