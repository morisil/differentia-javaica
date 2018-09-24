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

import com.xemantic.differentia.antlr.JavaParser;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.Tree;

import com.xemantic.differentia.antlr.JavaLexer;

/**
 * Supporting code of Differentia Javaica.
 * <p>
 * Created on Jan 19, 2009
 */
public class DifferentiaUtils {

  private DifferentiaUtils() {
    /* util class, non-instantiable */
  }

  /**
   * Prepares comparison result according to text {@link Position} associated with given trees nodes.
   *
   * @param expected the expected source tree node.
   * @param actual   the actual source tree node.
   * @return the comparison result.
   */
  public static ComparisonResult getDifferenceResult(Tree expected, Tree actual) {
    return new ComparisonResult(getTextPosition(expected), getTextPosition(actual));
  }

  /**
   * Returns text position of the given tree node.
   *
   * @param tree the source tree node.
   * @return the text position.
   */
  public static Position getTextPosition(Tree tree) {
    return new Position(tree.getLine(), tree.getCharPositionInLine());
  }

  /**
   * Creates parser from given stream.
   *
   * @param stream the char stream.
   * @return the java parser.
   */
  public static JavaParser getParser(CharStream stream) {
    final JavaLexer lexer = new JavaLexer(stream);
    final CommonTokenStream in = new CommonTokenStream(lexer);
    return new JavaParser(in);
  }

  /**
   * Prepares message which describes difference between compared Java sources.
   *
   * @param expectedId id of the expected source.
   * @param actualId   id of the actual source.
   * @param result     the comparison result.
   * @return textual representation of difference.
   */
  public static String getDifferenceMessage(String expectedId, final String actualId, final ComparisonResult result) {
    final String msg;
    if (result.isDifferent()) {
      final Position expectedPosition = result.getDifferencePositionInExpected();
      final Position actualPosition = result.getDifferencePositionInActual();
      msg = "Difference[" + expectedId + expectedPosition + "<->" + actualId + actualPosition + "]";
    } else {
      msg = "No difference[" + expectedId + "<->" + actualId + "]";
    }
    return msg;
  }

}
