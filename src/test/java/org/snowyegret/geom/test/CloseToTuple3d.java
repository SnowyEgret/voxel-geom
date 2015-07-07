package org.snowyegret.geom.test;

import java.text.DecimalFormat;

import javax.vecmath.Tuple3d;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

// https://code.google.com/p/hamcrest/wiki/Tutorial
public class CloseToTuple3d extends TypeSafeMatcher<Tuple3d> {

	Tuple3d expected;
	double epsilon = .0000001;

	public CloseToTuple3d(Tuple3d tuple) {
		expected = tuple;
	}

	@Override
	public void describeTo(final Description description) {
		description.appendText("Tuple should be ").appendValue(toString(expected));
	}

	@Override
	protected void describeMismatchSafely(final Tuple3d actual, final Description mismatchDescription) {
		mismatchDescription.appendText(" was ").appendValue(toString(actual));
	}

	private String toString(Tuple3d t) {
		DecimalFormat df = new DecimalFormat("#.###");
		String s = "("+
				 df.format(t.x)+", "+
				 df.format(t.y)+", "+
				 df.format(t.z)+")";
		return s;
	}

	@Override
	protected boolean matchesSafely(final Tuple3d actual) {
		if (Math.abs(actual.x - expected.x) < epsilon && Math.abs(actual.y - expected.y) < epsilon
				&& Math.abs(actual.z - expected.z) < epsilon)
			return true;
		else
			return false;
	}

	@Factory
	public static <T> Matcher<Tuple3d> closeToTuple3d(Tuple3d tuple) {
		return new CloseToTuple3d(tuple);
	}

}