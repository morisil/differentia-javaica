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

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.antlr.runtime.RecognitionException;
import org.junit.Assert;

/**
 * JUnit specific assert.
 * <p>
 * Created on Jan 19, 2009
 * 
 * @see Assert
 */
public class DifferentiaAssert {

	/**
	 * Asserts that two java source codes are equal.
	 *
	 * @param actual the path to expected java source code.
	 * @param expected the path to actual java source code.
	 */
	public static void assertSourcesEqual(String expected, String actual) {
		assertSourcesEqual(expected, actual, false, false);
	}

	/**
	 * Asserts that two java source codes are equal but package names and
	 * content of <code>@Generated</code> annotation may differ.
	 * 
	 * @param actual the path to expected java source code.
	 * @param expected the path to actual java source code.
	 * @param debug print syntax tree to standard output if true
	 * @param relaxed true if package names and <code>@Generated</code> annotation
	 * 				  may differ, false otherwise
	 */
	public static void assertSourcesEqual(String expected, String actual, boolean debug, boolean relaxed) {
		final Differentia differentia = new Differentia();
		differentia.setDebug(debug);
		differentia.setRelaxed(relaxed);
		final ComparisonResult result;
		try {
			result = differentia.compare(expected, actual);
		} catch (IOException e) {
			throw new RuntimeException("IO Exception while comparing java sources", e);
		} catch (RecognitionException e) {
			throw new RuntimeException("Parsing error while comparing java sources", e);
		}
		if (result.isDifferent()) {
			Assert.fail("Java source codes differ: " + DifferentiaUtils.getDifferenceMessage(expected, actual, result));
		}
	}

	public static void assertSourcesEqual(Reader expected, Reader actual) {
		assertSourcesEqual(expected, actual, false, false);
	}
	
	public static void assertSourcesEqual(Reader expected, Reader actual, boolean debug, boolean relaxed) {
		final Differentia differentia = new Differentia();
		differentia.setDebug(debug);
		differentia.setRelaxed(relaxed);
		final ComparisonResult result;
		try {
			result = differentia.compare(expected, actual);
		} catch (IOException e) {
			throw new RuntimeException("IO Exception while comparing java sources", e);
		} catch (RecognitionException e) {
			throw new RuntimeException("Parsing error while comparing java sources", e);
		}
		if (result.isDifferent()) {
			Assert.fail("Java source codes differ: " + DifferentiaUtils.getDifferenceMessage("expected", "actual", result));
		}
	}

	public static void assertSourcesAsStringsEqual(String expected, String actual) {
		assertSourcesAsStringsEqual(expected, actual, false, false);
	}

	/**
	 * Asserts that two java source codes are equal.
	 * 
	 * @param actual
	 *            the path to expected java source code.
	 * @param expected
	 *            the path to actual java source code.
	 * @param relaxed
	 *            if <code>true</code> comparison will omit values of parameter
	 *            <code>date</code> and parameter <code>comment</code> from
	 *            annotation <code>Generated</code>.
	 */
	public static void assertSourcesAsStringsEqual(String expected, String actual, final boolean debug, final boolean relaxed) {
		assertSourcesEqual(new StringReader(expected), new StringReader(actual), debug, relaxed);
	}

}
