package org.snowyegret.geom;

import javax.vecmath.Point3d;

public abstract class Primitive implements IDrawable {

	protected Point3d p0;
	protected double epsilon = .00000001d;
	private final VoxelSet voxels = null;

	public Primitive(Point3d origin) {
		p0 = origin;
	}

	@Override
	public Point3d getOrigin() {
		return new Point3d(p0);
	}

	public abstract boolean contains(Point3d p);

	@Override
	public VoxelSet voxelize() {
		if (voxels == null) {
			return pointSet().voxelize();
		} else {
			return voxels;
		}		
	}

	@Override
	public abstract PointSet pointSet();
}
