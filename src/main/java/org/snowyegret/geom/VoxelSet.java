package org.snowyegret.geom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Point3i;

import org.snowyegret.geom.surface.InfinitePlane;

public class VoxelSet implements Iterable<Point3i> {

	Set<Point3i> voxels = new HashSet<>();

	public VoxelSet() {
	}

	public VoxelSet(Iterable<Point3i> selections) {
		for (Point3i p : selections) {
			voxels.add(p);
		}
	}

	// public VoxelSet(Collection<Selection> selections) {
	// voxels.addAll(selections);
	// }

	@Override
	public Iterator iterator() {
		return voxels.iterator();
	}

	public void add(Point3i voxel) {
		voxels.add(voxel);
	}

	public void remove(Point3i p) {
		voxels.remove(p);
	}

	public PointSet toPointSet() {
		PointSet s = new PointSet();
		for (Point3i v : voxels) {
			s.addPoint(new Point3d(v.x, v.y, v.z));
		}
		return s;
	}

	public int size() {
		return voxels.size();
	}

	public VoxelSet shell() {
		VoxelSet shell = new VoxelSet(this);
		for (Point3i p : core()) {
			shell.remove(p);
		}
		return shell;
	}

	public VoxelSet core() {
		VoxelSet core = new VoxelSet();
		for (Point3i v : voxels) {
			List surroundingPoints = new ArrayList();
			surroundingPoints.add(new Point3i(v.x + 1, v.y, v.z));
			surroundingPoints.add(new Point3i(v.x - 1, v.y, v.z));
			surroundingPoints.add(new Point3i(v.x, v.y + 1, v.z));
			surroundingPoints.add(new Point3i(v.x, v.y - 1, v.z));
			surroundingPoints.add(new Point3i(v.x, v.y, v.z + 1));
			surroundingPoints.add(new Point3i(v.x, v.y, v.z - 1));
			if (voxels.containsAll(surroundingPoints)) {
				core.add(v);
			}
		}
		return core;
	}

	public Point3d centroid() {
		double x = 0, y = 0, z = 0;
		for (Point3i v : voxels) {
			x += v.x + .5;
			y += v.y + .5;
			z += v.z + .5;
		}
		int s = voxels.size();
		return new Point3d(x / s, y / s, z / s);
		// Point3i centroid = new Point3i((int) (x / s), (int) (y / s), (int) (z
		// / s));
		// return GeomUtil.toPoint3i(p);
	}

	// Returns a copy
	public VoxelSet transform(Matrix4d m) {
		VoxelSet copy = new VoxelSet();
		for (Point3i v : this) {
			Point3d p = new Point3d(v.x, v.y, v.z);
			m.transform(p);
			copy.add(new Point3i((int) Math.round(p.x), (int) Math.round(p.y),
					(int) Math.round(p.z)));
		}
		return copy;
	}

	public boolean containsAll(List surroundingPoints) {
		return voxels.containsAll(surroundingPoints);
	}

	@Override
	public String toString() {
		return "VoxelSet [size()=" + size() + "]";
	}

	public IntegerDomain getDomain() {
		Point3i min = new Point3i(Integer.MAX_VALUE, Integer.MAX_VALUE,
				Integer.MAX_VALUE);
		Point3i max = new Point3i(Integer.MIN_VALUE, Integer.MIN_VALUE,
				Integer.MIN_VALUE);
		for (Point3i v : voxels) {
			min.x = (v.x < min.x) ? v.x : min.x;
			min.y = (v.y < min.y) ? v.y : min.y;
			min.z = (v.z < min.z) ? v.z : min.z;
			max.x = (v.x > max.x) ? v.x : max.x;
			max.y = (v.y > max.y) ? v.y : max.y;
			max.z = (v.z > max.z) ? v.z : max.z;
		}
		return new IntegerDomain(min, max);
	}

	public Iterable<VoxelSet> divide(int i) {
		List<VoxelSet> list = new ArrayList();
		for (IntegerDomain d : getDomain().divide(i)) {
			VoxelSet voxelSet = new VoxelSet();
			for (Point3i v : voxels) {
				if (d.contains(v)) {
					voxelSet.add(v);
				}
			}
			list.add(voxelSet);
		}
		return list;
	}

	public Iterable<IntegerDomain> divideDomain(int i) {
		List<IntegerDomain> list = new ArrayList();
		for (IntegerDomain d : getDomain().divide(i)) {
			list.add(d);
		}
		return list;
	}

	public void add(VoxelSet voxelSet) {
		voxels.addAll(voxelSet.voxels);
		// System.out.println("[VoxelSet.add] voxels.size()=" + voxels.size());
	}

	public Point3i randomPoint() {
		return voxels.iterator().next();
	}

	public Boolean contains(Point3i p) {
		return voxels.contains(p);
	}

}
