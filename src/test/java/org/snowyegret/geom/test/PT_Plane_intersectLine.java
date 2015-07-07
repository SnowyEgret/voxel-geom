package org.snowyegret.geom.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;

import javax.vecmath.Point3d;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.snowyegret.geom.curve.InfiniteLine;
import org.snowyegret.geom.surface.InfinitePlane;

@RunWith(Parameterized.class)
public class PT_Plane_intersectLine extends GeomTestParamaterized {

	InfiniteLine infiniteLine;
	InfinitePlane infinitePlane;

	public PT_Plane_intersectLine(InfiniteLine infiniteLine, InfinitePlane infinitePlane) {
		this.infiniteLine = infiniteLine;
		this.infinitePlane = infinitePlane;
	}

	@Parameterized.Parameters
	public static Collection randomParams() {
		return Arrays.asList(linePlane());
	}

	@Test
	public void doNothing() {
	}

	//@Test
	public void test() {
		Point3d p = infinitePlane.intersectLine(infiniteLine);
		double distanceToPlane = infinitePlane.distancePerp(p);
		double distanceToLine = infiniteLine.distancePerp(p);
		// System.out.println("[PT_Plane_intersectLine.test] distanceToPlane=" + distanceToPlane);
		// System.out.println("[PT_Plane_intersectLine.test] distanceToLine=" + distanceToLine);
		assertThat("Line contains intersection", infiniteLine.contains(p), is(true));
		assertThat("Plane contains intersection", infinitePlane.contains(p), is(true));
	}

}
