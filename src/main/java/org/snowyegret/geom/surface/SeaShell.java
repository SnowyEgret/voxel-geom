package org.snowyegret.geom.surface;

import javax.vecmath.Point3d;

import org.apache.commons.lang3.Range;

public class SeaShell extends Surface {

	// double a = 0.2;
	// double b = 1.0;
	// double c = 0.1;
	// double n = 2.0;

	double k = 40;
	double a = k / 5;
	double b = a / 2;

	// double c = 3 / 4 * a;

	// Must be an origin, other arguments can be null
	public SeaShell(Point3d origin, Point3d numTurnsIn) {
		super(origin);
//		rU = Range.between(0d, 4 * Math.PI);
//		rV = Range.between(0d, 4 * Math.PI);
		double numTurns = Math.abs(origin.distance(numTurnsIn));
		rU = Range.between(0d, numTurns * Math.PI);
		rV = Range.between(0d, numTurns * Math.PI);
		k = 30;
//		a = Math.abs(numTurns.distance(aIn)) * Math.PI * 2;
//		b = Math.abs(aIn.distance(bIn)) * Math.PI * 2;
		a = 11 * Math.PI;
		b = 5 * Math.PI;
		// b = a / 2d * Math.PI;
		// c = 3d * a / 4d;

		System.out.println(this);
	}

	// //http://mathworld.wolfram.com/Seashell.html
	// @Override
	// public Point3d pointAtParameters(double u, double v) {
	// Point3d p = new Point3d();
	// p.x = ((1 - v / 2 * Math.PI) * (1 + Math.cos(u)) + c) * Math.cos(n * v);
	// p.y = ((1 - v / 2 * Math.PI) * (1 + Math.cos(u)) + c) * Math.cos(n * v);
	// p.z = (b * v) / (2 * Math.PI) + a * Math.sin(u) * (1 - v / (2 *
	// Math.PI));
	// p.add(p0);
	// return p;
	// }

	// http://mathworld.wolfram.com/Seashell.html
	@Override
	public Point3d pointAtParameters(double u, double v) {
		double cosVoverTwoSquared = Math.cos(v / 2) * Math.cos(v / 2);
		double eToTheUoverA = Math.pow(Math.E, u / a);
		double sinV = Math.sin(v);
		Point3d p = new Point3d();
		p.z = k * (2 * (1 - eToTheUoverA) * Math.cos(u) * cosVoverTwoSquared);
		p.x = k * (2 * (-1 + eToTheUoverA) * Math.sin(u) * cosVoverTwoSquared);
		p.y = -k * (1 - Math.pow(Math.E, u / b) - sinV + eToTheUoverA * sinV);

		// p.z = k
		// * (2 * (1 - Math.pow(Math.E, u / a)) * Math.cos(u)
		// * Math.cos(v / 2) * Math.cos(v / 2));
		// p.x = k
		// * (2 * (-1 + Math.pow(Math.E, u / a)) * Math.sin(u)
		// * Math.cos(v / 2) * Math.cos(v / 2));
		// p.y = -k
		// * (1 - Math.pow(Math.E, u / b) - Math.sin(v) + Math.pow(Math.E,
		// u / a) * Math.sin(v));
		p.add(p0);
		return p;
	}

	@Override
	public boolean contains(Point3d p) {
		throw new UnsupportedOperationException(
				"Method 'Surface.contains' not yet implemented");
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SeaShell [size=");
		builder.append(k);
		builder.append(", a=");
		builder.append(a);
		builder.append(", b=");
		builder.append(b);
		// builder.append(", c=");
		// builder.append(c);
		builder.append("]");
		return builder.toString();
	}

}
