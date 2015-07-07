package org.snowyegret.geom.test;

import java.util.Random;

import javax.vecmath.Point3d;
import javax.vecmath.Point3i;
import javax.vecmath.Vector3d;

import org.snowyegret.geom.PointSet;
import org.snowyegret.geom.Primitive;
import org.snowyegret.geom.curve.CircleXZ;
import org.snowyegret.geom.curve.InfiniteLine;
import org.snowyegret.geom.curve.Line;
import org.snowyegret.geom.solid.Ball;
import org.snowyegret.geom.solid.Box;
import org.snowyegret.geom.surface.EllipticParaboloid;
import org.snowyegret.geom.surface.InfinitePlane;

public class GeomTest {

	Random rand = new Random();
	protected double scale = 200;
	protected double epsilon = .00001;

	public Point3d p0() {
		return new Point3d(0, 0, 0);
	}

	public Point3d p1() {
		return new Point3d(1, 1, 1);
	}

	public Point3d p2() {
		return new Point3d(2, 2, 2);
	}

	public Point3d p3() {
		return new Point3d(3, 3, 3);
	}

	public Point3d p() {
		return new Point3d(d(), d(), d());
	}

	public Point3i p3i() {
		return new Point3i(i(), i(), i());
	}

	public Point3i p3i(int n) {
		return new Point3i(n, n, n);
	}

	public Point3i p3i(Point3d p) {
		return new Point3i((int)p.x, (int)p.y, (int)p.z);
	}

	public Point3d p(int x, int y, int z) {
		return new Point3d(x, y, z);
	}

	public Point3d p(Point3d p) {
		return new Point3d(p);
	}

	public Vector3d v() {
		return new Vector3d(d(), d(), d());
	}

	public Vector3d v(Point3d p) {
		return new Vector3d(p);
	}

	public Vector3d v(double x, double y, double z) {
		return new Vector3d(x, y, z);
	}

	public InfiniteLine line() {
		return new InfiniteLine(p(), p());
	}

	public InfinitePlane plane() {
		return new InfinitePlane(p(), p(), p());
	}

	public Line lineSegment() {
		return new Line(p(), p());
	}

	public PointSet pointSet(int size) {
		PointSet s = new PointSet();
		for (int i = 0; i < size; i++) {
			s.addPoint(p());
		}
		return s;
	}

	public CircleXZ circleXY(Point3d origin) {
		return new CircleXZ(origin, p());
	}

	protected double d() {
		return Math.round((rand.nextDouble() * scale) - scale / 2);
	}

	int i() {
		return (int) d();
	}

	public Ball ball(Point3d origin) {
		return new Ball(origin, p());
	}

	public Primitive box(Point3d o) {
		return new Box(o, p());
	}

	public Primitive box() {
		return new Box(o(), p());
	}

	public Point3d o() {
		return new Point3d(0, 0, 0);
	}

	public int randInt(Random rand, int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}

	public Primitive elipticParaboloid(Point3d origin) {
		return new EllipticParaboloid(origin, p(), p());
	}

	public Point3d p(double d) {
		return new Point3d(d, d, d);
	}

}
