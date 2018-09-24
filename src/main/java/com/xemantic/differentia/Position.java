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

import org.apache.commons.lang.Validate;

/**
 * Position in the text.
 * <p>
 * Created on Jan 19, 2009
 */
public class Position {

	private final int line;

	private final int column;

	/**
	 * Creates position object with given coordinates.
	 *
	 * @param line the line.
	 * @param column the column.
	 * @throws IllegalArgumentException if the <code>line</code> or the <code>column</code> is negative number.
	 */
	public Position(int line, final int column) {
		Validate.isTrue(line >= 0, "line cannot be negative");
		Validate.isTrue(column >= 0, "column cannot be negative");
		this.line = line;
		this.column = column;
	}

	/**
	 * Returns line.
	 *
	 * @return the line.
	 */
	public int getLine() {
		return line;
	}

	/**
	 * Returns column.
	 *
	 * @return the column.
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Returns textual representation of position in text.
	 * Example: <code>[134:33]</code>
	 *
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		return "[" + line + ":" + column + "]";
	}

}
