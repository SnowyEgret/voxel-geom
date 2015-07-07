package org.snowyegret.geom.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

import org.junit.Test;

public class GeomTestTest {
	
	GeomTest g = new GeomTest();

	@Test
	public void d() {
		g.scale = 100;
		double sum = 0;
		int n = 1000;
		for (int i = 0; i < n; i++) {
			double d = g.d();
			System.out.println("[T_Factory.d] d=" + d);
			sum += d;
		}
		assertThat(sum/n, closeTo(0, g.scale/g.scale*2));
	}

}
