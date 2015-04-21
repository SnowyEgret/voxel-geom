package ds.geom.test;

import javax.vecmath.Point3d;

import org.jzy3d.analysis.AbstractAnalysis;
import org.jzy3d.analysis.AnalysisLauncher;
import org.jzy3d.chart.factories.AWTChartComponentFactory;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.rendering.canvas.Quality;

import ds.geom.IDrawable;
import ds.geom.PointSet;
import ds.geom.solid.Tetrahedron;

public class JZYViewer extends AbstractAnalysis {

	PointSet pointSet;
	static GeomTest g = new GeomTest();

	public JZYViewer(PointSet pointSet) {
		super();
		this.pointSet = pointSet;
		init();
	}

	public static void main(String[] args) throws Exception {
		IDrawable d = new Tetrahedron(g.o(), g.p());
		System.out.println("[JZYViewer.main] d=" + d);
		//Drawable d = new Helix(Factory.newOrigin(), new Point3d(0, 200, 0), new Point3d(0, 0, 200), 5);
		//Drawable d = Factory.randomBall(Factory.newOrigin());
		//Drawable d = new Torus(Factory.newOrigin(), new Point3d(1, 0, 0), new Point3d(.3, 0, 0));
		// GenericSolid d = new GenericSolid(Factory.newOrigin(), Factory.randomPoint(), Factory.randomPoint(),
		// Factory.randomDouble(),
		// Factory.randomDouble(), Factory.randomDouble());
		// Sphere d = Factory.randomSphere();
		AnalysisLauncher.open(new JZYViewer(d.pointSet()));
	}

	@Override
	public void init() {

		Coord3d[] points = new Coord3d[pointSet.size()];
		System.out.println("[JZYViewer.init] pointSet.size()=" + pointSet.size());
		// Color[] colors = new Color[pointSet.size()];
		int i = 0;
		for (Point3d p : pointSet) {
			points[i] = new Coord3d((float) p.x, (float) p.y, (float) p.z);
			// colors[ordinal] = new Color(1f, 0f, 0f);
			i++;
		}

		// int size = 500000;
		// float x;
		// float y;
		// float z;
		// float a;
		//
		// Coord3d[] points = new Coord3d[size];
		// Color[] colors = new Color[size];
		//
		// Random r = new Random();
		// r.setSeed(0);
		//
		// for (int ordinal = 0; ordinal < size; ordinal++) {
		// x = r.nextFloat() - 0.5f;
		// y = r.nextFloat() - 0.5f;
		// z = r.nextFloat() - 0.5f;
		// points[ordinal] = new Coord3d(x, y, z);
		// a = 0.25f;
		// colors[ordinal] = new Color(x, y, z, a);
		// }

		Scatter scatter = new Scatter(points);
		// Scatter scatter = new Scatter(points, colors);
		chart = AWTChartComponentFactory.chart(Quality.Advanced, "newt");
		chart.getScene().add(scatter);
	}
}