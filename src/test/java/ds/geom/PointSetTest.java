package ds.geom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

import javax.vecmath.Point3d;
import javax.vecmath.Point3i;
import javax.vecmath.Vector3d;

import org.junit.Test;

import ds.geom.curve.Line;
import ds.geom.test.GeomTest;

public class PointSetTest extends GeomTest {
	
	@Test
	public void doNothing() {
	}



	//@Test
	public void voxelize() {
		PointSet points = pointSet(10000);
		VoxelSet voxels = points.voxelize();
		int numVoxels = voxels.size();
		int numPoints = points.size();
		System.out.println("[T_PointSet.voxelize] numVoxels=" + numVoxels);
		System.out.println("[T_PointSet.voxelize] numPoints=" + numPoints);
		assertThat(numVoxels, lessThan(numPoints));
	}

}
