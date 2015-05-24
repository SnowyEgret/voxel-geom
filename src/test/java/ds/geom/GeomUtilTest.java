package ds.geom;

import static ds.geom.test.CloseToTuple3d.closeToTuple3d;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.equalTo;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Point3i;
import javax.vecmath.Vector3d;

import org.junit.Test;

import ds.geom.surface.Sphere;
import ds.geom.test.GeomTest;

public class GeomUtilTest extends GeomTest {
	
	double epsilon = .000001;

	@Test
	public void angleBetweenPoints() {
		Point3d center = new Point3d(1, 0, 0);
		Point3d from = new Point3d(9, 0, 0);
		Point3d to = new Point3d(1, 6, 0);
		assertThat(GeomUtil.angleBetweenPoints(center, from, to), closeTo(Math.PI / 2, epsilon));
	}

	@Test
	public void normalToPlane() {
		Point3d center = new Point3d(1, 0, 0);
		Point3d from = new Point3d(9, 0, 0);
		Point3d to = new Point3d(1, 6, 0);
		assertThat(GeomUtil.normalToPlane(center, from, to), equalTo(new Vector3d(0, 0, 1)));
	}

	@Test
	public void pointOnLineClosestToPoint() {
		Point3d p = new Point3d(3, 2, 0);
		Point3d l1 = new Point3d(1, 0, 0);
		Point3d l2 = new Point3d(3, 0, 0);
		assertThat(GeomUtil.pointOnLineClosestToPoint(p, l1, l2), closeToTuple3d(new Point3d(3, 0, 0)));
	}

	@Test
	public void distancePerpendicularFromPointToLine() {
		Point3d p = new Point3d(3, 2, 0);
		Point3d l1 = new Point3d(1, 0, 0);
		Point3d l2 = new Point3d(3, 0, 0);
		assertThat(GeomUtil.distancePerpendicularFromPointToLine(p, l1, l2), equalTo(2d));
	}

	@Test
	public void newTranslationMatrix() {
		Point3d from = new Point3d(1, 1, 1);
		Point3d to = new Point3d(2, 2, 2);
		Point3d p = new Point3d(0, 0, 0);
		Matrix4d m = GeomUtil.newTranslationMatrix(from, to);
		m.transform(p);
		assertThat(p, closeToTuple3d(new Point3d(1, 1, 1)));
	}

	//@Test
	public void newReflectionMatrix_PointPointPoint() {

		Point3d p1 = new Point3d(0, 2, 0);
		Point3d p2 = new Point3d(1, 2, 0);
		Point3d p3 = new Point3d(0, 2, 1);
		Matrix4d m = GeomUtil.newReflectionMatrix(p1, p2, p3);

		Point3d p = new Point3d(0, 4, 0);
		m.transform(p);
		assertThat(p, closeToTuple3d(new Point3d(0, 0, 0)));
		
		p = new Point3d(0, 4, 0);
		Primitive sphere = new Sphere(o(), p, false);
		VoxelSet voxels = sphere.voxelize();
		voxels = voxels.transform(m);
		Point3d centroid = voxels.centroid();
		assertThat(centroid, equalTo(new Point3d(0, 0, 0)));
	}

	//@Test
	public void newReflectionMatrix_PointPoint() {
		Point3d o = new Point3d(4, 0, 0);
		Primitive sphere = new Sphere(o, p(), false);
		VoxelSet voxels = sphere.voxelize();

		Matrix4d m = GeomUtil.newReflectionMatrix(o, new Point3d(-1, 0, 1));
		voxels = voxels.transform(m);
		Point3d centroid = voxels.centroid();
		centroid.add(new Point3d(-10, 0, 0));

		assertThat("Centroid translated equals centroid", centroid, equalTo(o));
	}

	//@Test
	public void newRotationMatrixCenterFromTo() {

		Point3d center = new Point3d(0, 0, 0);
		Point3d from = new Point3d(1, 0, 0);
		Point3d to = new Point3d(0, 1, 0);
		Matrix4d mR = GeomUtil.newRotationMatrix(center, from, to);

		Matrix4d mr = new Matrix4d();
		mr.rotZ(Math.PI / 2);

		assertThat(mR, equalTo(mr));
	}

	@Test
	public void newRotationMatrixVectorVector() {

		Vector3d v1 = new Vector3d(1, 0, 0);
		Vector3d v2 = new Vector3d(0, 1, 0);
		Matrix4d mR = GeomUtil.newRotationMatrix(v1, v2);

		mR.transform(v1);

		assertThat(v1, closeToTuple3d(v2));
	}

	@Test
	public void newScaleMatrix() {

		Vector3d sf = new Vector3d(2, -1, 0);
		Matrix4d mF = GeomUtil.newScaleMatrix(sf);

		Point3d p = new Point3d(3, 9, 0);
		mF.transform(p);

		assertThat(p, closeToTuple3d(new Point3d(6, -9, 0)));
	}

	@Test
	public void toPoint3i() {
		for (int i = 0; i <100; i++) {
			Point3d p = p();
			assertThat(GeomUtil.toPoint3d(GeomUtil.toPoint3i(p)), equalTo(p));
		}
	}

}
