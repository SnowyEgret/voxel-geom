package org.snowyegret.geom.matrix;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ReflectionMatrixTest.class, RotationMatrixTest.class, ScaleMatrixTest.class,
		TranslationMatrixTest.class })
public class AllTests {

}
