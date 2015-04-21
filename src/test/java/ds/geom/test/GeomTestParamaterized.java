package ds.geom.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Callable;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import ds.geom.curve.InfiniteLine;
import ds.geom.surface.InfinitePlane;

public class GeomTestParamaterized {
	
	static int rows = 20;
	static GeomTest g = new GeomTest();

	public static Collection params(int rows, int columns, Callable<InfinitePlane> callable) throws Exception {
		Object[][] params = new Object[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				params[i][j] = callable.call();
			}
		}
		return Arrays.asList(params);
	}

	public static Object[][] vectorPoint(int rows) {
		Object[][] params = new Object[rows][2];
		for (int i = 0; i < rows; i++) {
			params[i][0] = g.vector();
			params[i][1] = g.p();
		}
		return params;
	}

	public static Object[][] linePlane() {
		Object[][] params = new Object[rows][2];
		for (int i = 0; i < rows; i++) {
			params[i][0] = g.line();
			params[i][1] = g.plane();
		}
		return params;
	}

	public static Object[][] planePoint() {
		Object[][] params = new Object[rows][2];
		for (int i = 0; i < rows; i++) {
			params[i][0] = g.plane();
			params[i][1] = g.p();
		}
		return params;
	}

	public static Object[][] plane_PointOnPlane_PointNotOnPlane() {
		Object[][] params = new Object[rows][3];
		for (int i = 0; i < rows; i++) {
			InfinitePlane infinitePlane = g.plane();
			params[i][0] = infinitePlane;
			params[i][1] = infinitePlane.p1;
			params[i][2] = g.p();
		}
		return params;
	}

	public static Object[][] plane_PointAbovePlane(double distanceAbovePlane) {
		Object[][] params = new Object[rows][2];
		for (int i = 0; i < rows; i++) {
			InfinitePlane infinitePlane = g.plane();
			params[i][0] = infinitePlane;

			Vector3d n = infinitePlane.normal();
			n.scale(distanceAbovePlane);
			Point3d p = infinitePlane.getOrigin();
			p.add(n);
			params[i][1] = p;
		}
		return params;
	}

	public static Object[][] line_PointOnLine_PointNotOnLine() {
		Object[][] params = new Object[rows][3];
		for (int i = 0; i < rows; i++) {
			InfiniteLine infiniteLine = g.line();
			params[i][0] = infiniteLine;
			params[i][1] = infiniteLine.p1;
			params[i][2] = g.p();
		}
		return params;
	}

	public static Object[][] points(int columns) {
		Object[][] params = new Object[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				params[i][j] = g.p();
			}
		}
		return params;
	}

	public static Object[][] vectors(int rows, int columns) {
		Object[][] params = new Object[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				params[i][j] = g.vector();
			}
		}
		return params;
	}

	public static Object[][] planes(int rows, int columns) {
		Object[][] params = new Object[rows][columns];
		for (int i = 0; i < rows; i++) {
			params[i][0] = g.plane();
		}
		return params;
	}
}
