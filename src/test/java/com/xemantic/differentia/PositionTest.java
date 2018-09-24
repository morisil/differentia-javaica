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

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.Assume.*;

import org.junit.Test;

/**
 * <p>
 * Created on Jan 19, 2009
 */
public class PositionTest {

  /**
   * Test method for {@link Position#Position(int, int)} - invalid arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public final void newTextPosition00() {
    new Position(-1, -1);
  }

  /**
   * Test method for {@link Position#Position(int, int)} - invalid arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public final void newTextPosition01() {
    new Position(-1, 0);
  }

  /**
   * Test method for {@link Position#Position(int, int)} - invalid arguments.
   */
  @Test(expected = IllegalArgumentException.class)
  public final void newTextPosition10() {
    new Position(0, -1);
  }

  /**
   * Test method for {@link Position#Position(int, int)} - valid arguments.
   */
  public final void newTextPosition11() {
    new Position(0, 0);
    new Position(10, 23);
  }

  /**
   * Test method for {@link Position#getLine()}.
   */
  @Test
  public final void getLine() {
    final Position p = new Position(313, 42);
    assertEquals("Invalid line", 313, p.getLine());
    assumeThat(p.getLine(), is(313));
  }

  /**
   * Test method for {@link Position#getColumn()}.
   */
  @Test
  public final void getColumn() {
    final Position p = new Position(313, 42);
    assumeThat(p.getColumn(), is(42));
  }

  /**
   * Test method for {@link Position#toString()}.
   */
  @Test
  public final void testToString() {
    final Position p = new Position(313, 42);
    assertThat(p.toString(), is("[313:42]"));
  }

}
