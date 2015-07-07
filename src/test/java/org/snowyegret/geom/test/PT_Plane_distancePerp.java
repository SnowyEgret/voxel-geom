package org.snowyegret.geom.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

import java.util.Arrays;
import java.util.Collection;

import javax.vecmath.Point3d;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.snowyegret.geom.surface.InfinitePlane;

@RunWith(Parameterized.class)
public class PT_Plane_distancePerp extends GeomTestParamaterized {

	InfinitePlane infinitePlane;
	Point3d point;
	static double distanceAbovePlane = 5;
	static double epsilon = .000001;

	public PT_Plane_distancePerp(InfinitePlane infinitePlane, Point3d point) {
		this.infinitePlane = infinitePlane;
		this.point = point;
	}

	@Parameterized.Parameters
	public static  Collection randomParams() throws Exception {
		return Arrays.asList(plane_PointAbovePlane(distanceAbovePlane));
	}

	@Test
	public void test() {
		System.out.println("plane=" + infinitePlane);
		System.out.println("point=" + point);
		assertThat("Known distance of point above plane is equal to perpendicular distance of plane to point",
				infinitePlane.distancePerp(point), closeTo(distanceAbovePlane, epsilon));
	}

}
