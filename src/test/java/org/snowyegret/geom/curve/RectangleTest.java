package org.snowyegret.geom.curve;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import javax.vecmath.Point3d;
import javax.vecmath.Point3i;

import org.junit.Test;
import org.snowyegret.geom.GeomUtil;
import org.snowyegret.geom.PointSet;
import org.snowyegret.geom.Primitive;
import org.snowyegret.geom.VoxelSet;
import org.snowyegret.geom.curve.Rectangle;
import org.snowyegret.geom.surface.Sphere;
import org.snowyegret.geom.test.GeomTest;

public class RectangleTest extends GeomTest {

	@Test
	public void firstQuadrant() {
		Point3d p0 = new Point3d(10, 0, 10);
		Point3d p2 = new Point3d(20, 0, 30);
		boolean isSquare = true;
		Primitive p = new Rectangle(p0, p2, isSquare);
		VoxelSet voxels = p.voxelize();
		assertThat(voxels.contains(GeomUtil.toPoint3i(p0)), equalTo(true));
		assertThat(voxels.contains(new Point3i(30, 0, 30)), equalTo(true));
	}

	@Test
	public void firstQuadrantReversed() {
		Point3d p0 = new Point3d(10, 0, 10);
		Point3d p2 = new Point3d(20, 0, 30);
		boolean isSquare = true;
		Primitive p = new Rectangle(p2, p0, isSquare);
		VoxelSet voxels = p.voxelize();
		assertThat(voxels.contains(GeomUtil.toPoint3i(p2)), equalTo(true));
		assertThat(voxels.contains(new Point3i(0, 0, 10)), equalTo(true));
	}

}
