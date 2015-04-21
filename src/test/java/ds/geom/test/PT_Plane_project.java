package ds.geom.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Collection;

import javax.vecmath.Point3d;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ds.geom.surface.InfinitePlane;

@RunWith(Parameterized.class)
public class PT_Plane_project extends GeomTestParamaterized {

	InfinitePlane infinitePlane;
	Point3d point;

	public PT_Plane_project(InfinitePlane infinitePlane, Point3d point) {
		this.infinitePlane = infinitePlane;
		this.point = point;
	}

	@Parameterized.Parameters
	public static Collection randomParams() throws Exception {
		return Arrays.asList(planePoint());
	}

	@Test
	public void test() {
		Point3d p = infinitePlane.project(point);
		assertThat("Point projected on plane is contained by plane", infinitePlane.contains(p), equalTo(true));
	}

}
