package org.snowyegret.geom;

import javax.vecmath.Point3d;

public interface IDrawable {

	public VoxelSet voxelize();

	public PointSet pointSet();
	
	public Point3d getOrigin();
}
