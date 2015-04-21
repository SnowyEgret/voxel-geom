package ds.geom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.vecmath.Point3d;
import javax.vecmath.Point3i;

public class PointSet implements IDrawable, Iterable<Point3d> {

	private final List<Point3d> points = new ArrayList<>();

	public PointSet(Point3d...points) {
		for (Point3d p : points) {
			this.points.add(p);
		}
	}

	@Override
	public PointSet pointSet() {
		return this;
	}

	public void addPoints(PointSet pointSet) {
		points.addAll(pointSet.points);
	}

	@Override
	public String toString() {
		return "PointSet [points=" + points + "]";
	}

	@Override
	public VoxelSet voxelize() {
		VoxelSet voxels = new VoxelSet();
		for (Point3d p : points) {
			p.sub(new Point3d(.5, .5, .5));
			Point3i v = new Point3i((int) Math.round(p.x), (int) Math.round(p.y), (int) Math.round(p.z));
			voxels.add(v);
		}
		return voxels;
	}

	public void addPoint(Point3d p) {
		points.add(p);
	}

	@Override
	public Iterator<Point3d> iterator() {
		return points.iterator();
	}

	public int size() {
		return points.size();
	}

	@Override
	public Point3d getOrigin() {
		return points.get(0);
	}

	public void addPoints(Point3d...points) {
		for (Point3d p : points) {
			this.points.add(p);
		}
	}

	public boolean isEmpty() {
		return points.isEmpty();
	}
}
