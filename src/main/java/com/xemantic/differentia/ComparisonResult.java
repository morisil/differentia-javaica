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

/**
 * Result of comparison between to java source code files.
 * <p>
 * Created on Jan 19, 2009
 */
public class ComparisonResult {

	/**
	 * Indicates no difference result.
	 */
	public static final ComparisonResult NO_DIFFERENCE = new ComparisonResult(null, null);

	private final Position differencePositionInExpected;

	private final Position differencePositionInActual;

	/**
	 * Creates comparison result.
	 *
	 * @param differencePositionInExpected the position of encountered difference in expected file.
	 * @param differencePositionInActual the position of encountered difference in actual file.
	 *
	 * @throws IllegalArgumentException if one of the parameters is <code>null</code> and the other is not.
	 */
	public ComparisonResult(
			Position differencePositionInExpected,
			Position differencePositionInActual
	) {
		if ((differencePositionInExpected == null) && (differencePositionInActual != null)) {
			throw new IllegalArgumentException("Difference positions should either have values or both be null");
		}
		this.differencePositionInExpected = differencePositionInExpected;
		this.differencePositionInActual = differencePositionInActual;
	}

	/**
	 * Returns difference flag.
	 *
	 * @return <code>true</code> if code differs, <code>false</code> otherwise.
	 */
	public boolean isDifferent() {
		return (differencePositionInExpected != null);
	}

	/**
	 * Returns difference position in expected file.
	 *
	 * @return the differencePositionInExpected
	 */
	public Position getDifferencePositionInExpected() {
		return differencePositionInExpected;
	}

	/**
	 * Returns difference position in actual file.
	 *
	 * @return the differencePositionInActual
	 */
	public Position getDifferencePositionInActual() {
		return differencePositionInActual;
	}

}
