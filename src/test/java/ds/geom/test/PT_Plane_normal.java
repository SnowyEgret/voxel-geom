package ds.geom.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

import java.util.Collection;
import java.util.concurrent.Callable;

import javax.vecmath.Vector3d;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import ds.geom.surface.InfinitePlane;

@RunWith(Parameterized.class)
public class PT_Plane_normal extends GeomTestParamaterized {

	InfinitePlane infinitePlane;

	public PT_Plane_normal(InfinitePlane infinitePlane) {
		this.infinitePlane = infinitePlane;
	}

	@Parameterized.Parameters
	public static Collection randomParams() throws Exception {
		return params(10, 1, new Callable<InfinitePlane>() {
			@Override
			public InfinitePlane call() throws Exception {
				return g.plane();
			}
		});
	}

	@Test
	public void test() {
		System.out.println("plane=" + infinitePlane);
		Vector3d normal = infinitePlane.normal();
		Vector3d vectorInPlane = new Vector3d();
		vectorInPlane.sub(infinitePlane.p1, infinitePlane.getOrigin());
		assertThat(normal.dot(vectorInPlane), closeTo(0, .000001));
	}

}
