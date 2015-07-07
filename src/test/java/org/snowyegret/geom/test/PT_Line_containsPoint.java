package org.snowyegret.geom.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Collection;

import javax.vecmath.Point3d;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.snowyegret.geom.curve.InfiniteLine;

@RunWith(Parameterized.class)
public class PT_Line_containsPoint extends GeomTestParamaterized {

	InfiniteLine infiniteLine;
	Point3d pOnLine;
	Point3d pNotOnLine;

	public PT_Line_containsPoint(InfiniteLine infiniteLine, Point3d pOnLine, Point3d pNotOnLine) {
		this.infiniteLine = infiniteLine;
		this.pOnLine = pOnLine;
		this.pNotOnLine = pNotOnLine;
	}

	@Parameterized.Parameters
	public static Collection randomParams() throws Exception {
		return Arrays.asList(line_PointOnLine_PointNotOnLine());
	}

	
	@Test
	public void doNothing() {
	}

	//@Test
	public void test() {
		assertThat("Point on line is contained by line", infiniteLine.contains(pOnLine), equalTo(true));
		assertThat("Point not on line is not contained by line", infiniteLine.contains(pNotOnLine), equalTo(false));
	}
}
