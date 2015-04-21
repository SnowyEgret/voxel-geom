package ds.geom.curve;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Point3d;

import ds.geom.PointSet;
import ds.geom.Primitive;

public class Polyline extends Primitive {

	List<Line> lines = new ArrayList<>();
	
	protected Polyline(Point3d origin) {
		super(origin);
	}

	@Override
	public boolean contains(Point3d p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PointSet pointSet() {
		PointSet points = new PointSet();
		for (Line line : lines) {
			points.addPoints(line.pointSet());
		}
		return points;
	}

}
