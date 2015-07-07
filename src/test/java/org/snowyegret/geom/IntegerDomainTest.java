package org.snowyegret.geom;

import java.util.List;

import org.apache.commons.lang3.Range;
import org.junit.Test;
import org.snowyegret.geom.IntegerDomain;
import org.snowyegret.geom.test.GeomTest;

public class IntegerDomainTest extends GeomTest {

	@Test
	public void constuctor() {
		IntegerDomain d = new IntegerDomain(p3i(1), p3i(27));
		System.out.println("[T_Domain.constuctor] d=" + d);
	}

	@Test
	public void divideRange() {
		Range<Integer> r = Range.between(1, 9);
		List<Range<Integer>> subRanges = IntegerDomain.divideRange(r, 3);
		for (Range<Integer> range : subRanges) {
			System.out.println("[T_Domain.divideRange] range=" + range);
		}
	}

	@Test
	public void divide() {
		IntegerDomain d = new IntegerDomain(p3i(1), p3i(27));
		Iterable<IntegerDomain> subDomains = d.divide(3);
		for (IntegerDomain integerDomain : subDomains) {
			System.out.println("[T_Domain.test] domain=" + integerDomain);
		}
	}

}
