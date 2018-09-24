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
 * Tests for {@link ComparisonResult}.
 * <p>
 * Created on Jan 19, 2009
 */
public class ComparisonResultTest {

	/**
	 * Test method for {@link ComparisonResult#ComparisonResult(Position, Position)} - illegal argument.
	 */
	@Test(expected=IllegalArgumentException.class)
	public final void newComparisonResultIllegalArgument() {
		new ComparisonResult(null, new Position(1, 2));
	}

	/**
	 * Test method for {@link ComparisonResult#isDifferent()}.
	 */
	@Test
	public final void isDifferent() {
		final ComparisonResult r1 = new ComparisonResult(null, null);
		assertFalse(r1.isDifferent());
		final ComparisonResult r2 = new ComparisonResult(new Position(10, 20), new Position(30, 40));
		assertTrue(r2.isDifferent());
	}

	/**
	 * Test method for {@link ComparisonResult#getDifferencePositionInExpected()}.
	 */
	@Test
	public final void getDifferencePositionInExpected() {
		final ComparisonResult r = new ComparisonResult(new Position(1, 2), new Position(3, 4));
		final Position p = r.getDifferencePositionInExpected();
		assertEquals(1, p.getLine());
		assertEquals(2, p.getColumn());
	}

	/**
	 * Test method for {@link ComparisonResult#getDifferencePositionInActual()}.
	 */
	@Test
	public final void getDifferencePositionInActual() {
		final ComparisonResult r = new ComparisonResult(new Position(1, 2), new Position(3, 4));
		final Position p = r.getDifferencePositionInActual();
		assertEquals(3, p.getLine());
		assertEquals(4, p.getColumn());
	}

}
