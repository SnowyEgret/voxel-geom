package org.snowyegret.geom.surface;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.snowyegret.geom.test.CloseToTuple3d.closeToTuple3d;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.junit.Test;
import org.snowyegret.geom.surface.InfinitePlane;
import org.snowyegret.geom.test.GeomTest;

public class InfinitePlaneTest extends GeomTest {

	@Test
	public void XY() {
		InfinitePlane p = InfinitePlane.XY(o());
		assertThat(p.normal(), closeToTuple3d(new Vector3d(0, 0, 1)));
	}

	@Test
	public void XZ() {
		InfinitePlane p = InfinitePlane.XZ(o());
		assertThat(p.normal(), closeToTuple3d(new Vector3d(0, 1, 0)));
	}

	@Test
	public void YZ() {
		InfinitePlane p = InfinitePlane.YZ(o());
		assertThat(p.normal(), closeToTuple3d(new Vector3d(1, 0, 0)));
	}
	
	//@Test
	public void pointAtParameters() {
		InfinitePlane infinitePlane = plane();
		Point3d p = infinitePlane.pointAtParameters(d(), d());
		assertThat("Point from plane equation is contained by plane", infinitePlane.contains(p), is(true));
	}
	

}
