/*
 * differentia-javaica - compare java source files as logical structure
 *
 * Copyright (C) 2018  Kazimierz Pogoda
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.xemantic.differentia;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Tests for {@link DifferentiaAssert}.
 * <p>
 * Created on Jan 19, 2009
 */
public class DifferentiaAssertTest {

	/**
	 * Test method for {@link DifferentiaAssert#assertSourcesEqual(String, String)}: equal sources.
	 */
	@Test
	public final void assertSourcesEqual() {
		DifferentiaAssert.assertSourcesEqual(getPath("Foo"), getPath("Foo"));
	}

	/**
	 * Test method for {@link DifferentiaAssert#assertSourcesEqual(String, String)}: equal sources, different comments.
	 */
	@Test
	public final void assertSourcesEqualDifferentComments() {
		DifferentiaAssert.assertSourcesEqual(getPath("Bar"), getFakePath("Bar"));
	}

	/**
	 * Test method for {@link DifferentiaAssert#assertSourcesEqual(String, String)}: different sources.
	 */
	@Test
	public final void assertSourcesEqualDifference() {
		boolean passed = false;
		String msg = "";
		try {
			DifferentiaAssert.assertSourcesEqual(getPath("Foo"), getPath("Bar"));
			passed = true;
		} catch (AssertionError e) {
			msg = e.getMessage();
		}
		if (passed) {
			fail("No AssertionError was throw on comparison of different sources");
		}
		assertTrue("Incorrect message: " + msg, msg.matches("Java source codes differ: Difference\\[src/test/java/com/xemantic/differentia/fake/Foo\\.java\\[[0-9]+:[0-9]+\\]<->src/test/java/com/xemantic/differentia/fake/Bar\\.java\\[[0-9]+:[0-9]+\\]\\]"));
	}

	/**
	 * Test method for {@link DifferentiaAssert#assertSourcesEqual(String, String)}: different sources, small difference at the end.
	 */
	@Test
	public final void assertSourcesEqualSmallDifferenceAtTheEnd() {
		boolean passed = false;
		String msg = "";
		try {
			DifferentiaAssert.assertSourcesEqual(getPath("Buzz"), getFakePath("Buzz"));
			passed = true;
		} catch (AssertionError e) {
			msg = e.getMessage();
		}
		if (passed) {
			fail("No AssertionError was throw on comparison of different sources");
		}
		assertTrue("Incorrent message: " + msg, msg.matches("Java source codes differ: Difference\\[src/test/java/com/xemantic/differentia/fake/Buzz\\.java\\[[0-9]+:[0-9]+\\]<->src/test/fake/com/xemantic/differentia/fake/Buzz\\.java\\[[0-9]+:[0-9]+\\]\\]"));
	}

	/**
	 * Test method for
	 * {@link DifferentiaAssert#assertSourcesEqual(String, String)}: almost
	 * equal sources, unequal time stamp in @Generated.
	 *
	 * @throws Exception
	 */
	@Test
	public final void assertSourcesAsStringEqualRelaxedWithDifferentTimeStampInGeneratedAnnotation() throws Exception {
		String expectedPath = getPath("FooBar");

		String actualPath = getFakePath("FooBar");

		DifferentiaAssert.assertSourcesEqual(expectedPath, actualPath, false, true);
	}

	@Test
	public final void assertSourcesAsStringEqualRelaxedWithDifferentTimeStampInGeneratedAnnotation2() throws Exception {
		String expectedPath = getPath("FooBar2");

		String actualPath = getFakePath("FooBar2");

		DifferentiaAssert.assertSourcesEqual(expectedPath, actualPath, false, true);
	}

	@Test
	public final void assertSourcesAsStringEqualRelaxedWithDifferentPackageName() throws Exception {
		String expectedPath = getPath("FooBar");

		String actualPath = getPath("another/FooBar");

		DifferentiaAssert.assertSourcesEqual(expectedPath, actualPath, false, true);
	}

	@Test
	public final void assertSourcesAsStringEqualRelaxedWithDifferentPackageNameAndTimeStampInGeneratedAnnotation() throws Exception {
		String expectedPath = getPath("FooBar2");

		String actualPath = getPath("another/FooBar2");

		DifferentiaAssert.assertSourcesEqual(expectedPath, actualPath, false, true);
	}

	private String getPath(String klass) {
		return "src/test/java/com/xemantic/differentia/fake/" + klass + ".java";
	}

	private String getFakePath(String klass) {
		return "src/test/fake/com/xemantic/differentia/fake/" + klass + ".java";
	}

}
