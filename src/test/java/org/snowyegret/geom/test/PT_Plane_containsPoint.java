package org.snowyegret.geom.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Collection;

import javax.vecmath.Point3d;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.snowyegret.geom.surface.InfinitePlane;

@RunWith(Parameterized.class)
public class PT_Plane_containsPoint extends GeomTestParamaterized {

	InfinitePlane infinitePlane;
	Point3d pOnPlane;
	Point3d pNotOnPlane;

	public PT_Plane_containsPoint(InfinitePlane infinitePlane, Point3d pOnPlane, Point3d pNotOnPlane) {
		this.infinitePlane = infinitePlane;
		this.pOnPlane = pOnPlane;
		this.pNotOnPlane = pNotOnPlane;
	}

	@Parameterized.Parameters
	public static Collection randomParams() throws Exception {
		return Arrays.asList(plane_PointOnPlane_PointNotOnPlane());
	}

	@Test
	public void test() {
		assertThat("Point on plane is contained by plane", infinitePlane.contains(pOnPlane), equalTo(true));
		assertThat("Point not on plane is contained by plane", infinitePlane.contains(pNotOnPlane), equalTo(false));
	}
}
