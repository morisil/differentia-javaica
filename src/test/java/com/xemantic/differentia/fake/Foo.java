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
package com.xemantic.differentia.fake;

/**
 * Fake Foo class.
 * <p>
 * Created on Jan 19, 2009
 */
public class Foo {

	private final String id;

	private String name;

	/**
	 *  Creates Foo.
	 *
	 * @param id the id.
	 */
	public Foo(String id) {
		if (id == null) {
			throw new IllegalArgumentException("id cannot be null");
		}
		this.id = id;
	}

	/**
	 * Return id.
	 *
	 * @return the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns name.
	 *
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name to set.
	 */
	public void setName(String name) {
		name = name;
	}

}
