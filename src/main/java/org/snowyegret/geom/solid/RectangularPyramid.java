package org.snowyegret.geom.solid;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import org.apache.commons.lang3.Range;
import org.snowyegret.geom.PointSet;
import org.snowyegret.geom.curve.Rectangle;

public class RectangularPyramid extends Solid {

	public RectangularPyramid(Point3d origin, Point3d diagonalCorner, boolean isSquare) {
		super(origin);
		// Assure the pyramid lies flat
		diagonalCorner.y = origin.y;
		
		if (isSquare) {
			diagonalCorner = Rectangle.squareOf(origin, diagonalCorner);
		}
		
		Vector3d d = new Vector3d();
		d.sub(diagonalCorner, origin);
		rT = Range.between(0d, d.x);
		rU = Range.between(0d, d.z);
		rV = Range.between(0d, Math.abs(diagonalCorner.x - origin.x)); //height
	}

	@Override
	public PointSet pointSet() {
		double inc = 1d;
		PointSet points = new PointSet();
		int d = 0;
		for (double v = rangeV().getMinimum(); v <= rangeV().getMaximum(); v += inc) {
			for (double u = rangeU().getMinimum() + d; u <= rangeU().getMaximum() - d; u += inc) {
				for (double t = rangeT().getMinimum() + d; t <= rangeT().getMaximum() - d; t += inc) {
					Point3d p = new Point3d(t, v, u);
					p.add(p0);
					points.addPoint(p);
				}
			}
			d++;
		}
		return points;
	}

	@Override
	public boolean contains(Point3d p) {
		throw new UnsupportedOperationException("Method 'SquarePyramid.contains' not yet implemented");
	}

	@Override
	public String toString() {
		return "SquarePyramid [rangeT=" + rT + ", rangeU=" + rU + ", rangeV=" + rV + ", p0=" + p0 + "]";
	}

}
