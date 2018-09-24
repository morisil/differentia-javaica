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
import java.io.InputStream;
import java.io.Reader;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.ANTLRReaderStream;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;

import com.xemantic.differentia.antlr.JavaParser;

/**
 * Differentia Javaica main class. It contains main CLI class as well as crucial
 * util methods.
 * <p>
 * Created on Jan 17, 2009
 */
public class Differentia {

  private boolean debug;

  private boolean relaxed;

  public void setDebug(boolean debug) {
    this.debug = debug;
  }

  public boolean isDebug() {
    return debug;
  }

  public void setRelaxed(boolean relaxed) {
    this.relaxed = relaxed;
  }

  public boolean isRelaxed() {
    return relaxed;
  }

  /**
   * Main method called by CLI. It accepts two elements array of arguments
   * denoting Java source code files to compare.
   *
   * @param args CLI arguments - files to compare.
   * @see #compare(String, String)
   */
  public static void main(String[] args) {
    System.exit(mainImpl(args));
  }

  /**
   * Compares two Java sources specified in strings.
   *
   * @param expected the expected source.
   * @param actual   the actual source.
   * @return the comparison result.
   * @throws RecognitionException if parsing error occurs.
   */
  public ComparisonResult compareStrings(String expected, String actual) throws RecognitionException {
    return compare(new ANTLRStringStream(expected), new ANTLRStringStream(actual));
  }

  /**
   * Compares two Java source files denoted by specified paths.
   *
   * @param expected the file containing expected source.
   * @param actual   the file containing actual source.
   * @return the comparison result.
   * @throws IOException          in case of IO errors.
   * @throws RecognitionException if parsing error occurs.
   */
  public ComparisonResult compare(String expected, String actual) throws IOException, RecognitionException {
    return compare(new ANTLRFileStream(expected), new ANTLRFileStream(actual));
  }

  /**
   * Compares two Java sources provided via {@link InputStream}s.
   *
   * @param expected the expected source input stream.
   * @param actual   the actual source input stream.
   * @return the comparison result.
   * @throws IOException          in case of IO errors.
   * @throws RecognitionException if parsing error occurs.
   */
  public ComparisonResult compare(InputStream expected, final InputStream actual) throws IOException, RecognitionException {
    return compare(new ANTLRInputStream(expected), new ANTLRInputStream(actual));
  }

  /**
   * Compares two Java sources provided via input {@link Reader}s.
   *
   * @param expected the expected source reader.
   * @param actual   the actual source reader.
   * @return the comparison result.
   * @throws IOException          in case of IO errors.
   * @throws RecognitionException if parsing error occurs.
   */
  public ComparisonResult compare(Reader expected, final Reader actual) throws RecognitionException, IOException {
    return compare(new ANTLRReaderStream(expected), new ANTLRReaderStream(actual));
  }

  /**
   * Compares two Java sources provided via input {@link CharStream}s.
   *
   * @param expected the expected source stream.
   * @param actual   the actual source stream.
   * @return the comparison result.
   * @throws RecognitionException if parsing error occurs.
   */
  public ComparisonResult compare(CharStream expected, final CharStream actual) throws RecognitionException {
    return compare(DifferentiaUtils.getParser(expected), DifferentiaUtils.getParser(actual));
  }

  /**
   * Compares two Java sources provided via input {@link JavaParser}s.
   *
   * @param expected the parser of expected source.
   * @param actual   the parser of actual source.
   * @return the comparison result.
   * @throws RecognitionException if parsing error occurs.
   */
  public ComparisonResult compare(JavaParser expected, final JavaParser actual) throws RecognitionException {
    return compare((CommonTree) expected.compilationUnit().getTree(), (CommonTree) actual.compilationUnit().getTree());
  }

  /**
   * Implementation of the main method. Unlike {@link #main(String[])} method
   * which calls {@link System#exit(int)} this method can be tested by unit
   * tests.
   *
   * @param args CLI arguments - files to compare.
   * @return CLI exit code.
   * @see #main(String[])
   */
  protected static int mainImpl(String args[]) {
    final int exitCode;
    if (args.length != 2) {
      System.out.println("Usage: differentia-javaica expected_source actual_source");
      exitCode = 2;
    } else {
      final String expected = args[0];
      final String actual = args[1];
      final Differentia differentia = new Differentia();
      final Result result = differentia.compareCheckingExceptions(expected, actual);
      if (result.exception != null) {
        System.out.println("differentia-javaica: input error");
        result.exception.printStackTrace();
        exitCode = 3;
      } else {
        if (result.result.isDifferent()) {
          System.out.println("differentia-javaica: difference: " + DifferentiaUtils.getDifferenceMessage(expected, actual, result.result));
          exitCode = 1;
        } else {
          System.out.println("differentia-javaica: no difference");
          exitCode = 0;
        }
      }
    }
    return exitCode;
  }

  private Result compareCheckingExceptions(String expected, final String actual) {
    final Result result = new Result();
    try {
      result.result = compare(expected, actual);
    } catch (IOException e) {
      result.exception = e;
    } catch (RecognitionException e) {
      result.exception = e;
    }
    return result;
  }

  private ComparisonResult compare(Tree expected, final Tree actual) {
    if (debug) {
      System.out.println(getNodeName(expected));
    }
    final String expectedText = expected.getText();
    final String actualText = actual.getText();

    final ComparisonResult result;
    if (expectedText == null) {
      if (actualText != null) {
        result = DifferentiaUtils.getDifferenceResult(expected, actual);
      } else {
        result = compareChildren(expected, actual);
      }
    } else if (relaxed && (isPackage(expected) || isGeneratedAnnotation(expected))) {
      result = ComparisonResult.NO_DIFFERENCE;
    } else {
      if (expectedText.equals(actualText)) {
        result = compareChildren(expected, actual);
      } else {
        result = DifferentiaUtils.getDifferenceResult(expected, actual);
      }
    }
    return result;
  }

  private boolean isPackage(Tree expected) {
    return getNodeName(expected).matches(":package");
  }

  private boolean isGeneratedAnnotation(Tree expected) {
    final String name = getNodeName(expected);
    return (name.endsWith("ANNOTATION_INIT_BLOCK") && hasGeneratedSibling(expected));
  }

  private boolean hasGeneratedSibling(Tree expected) {
    Tree node = expected;
    while (!node.getText().equals("@")) {
      node = node.getParent();
    }
    int size = node.getChildCount();
    boolean found = false;
    for (int i = 0; i < size; i++) {
      if (node.getChild(i).getText().equals("Generated")) {
        found = true;
        break;
      }
    }

    return found;
  }

  private ComparisonResult compareChildren(Tree expected, Tree actual) {
    final int expectedChildCount = expected.getChildCount();
    final int actualChildCount = actual.getChildCount();
    final int size = Math.min(expectedChildCount, actualChildCount);
    ComparisonResult childResult = null;
    for (int i = 0; i < size; i++) {
      final Tree c1 = expected.getChild(i);
      final Tree c2 = actual.getChild(i);
      final ComparisonResult innerResult = compare(c1, c2);
      if (innerResult.isDifferent()) {
        childResult = innerResult;
        break;
      }
    }
    final ComparisonResult result;
    if (childResult == null) {
      if (expectedChildCount != actualChildCount) {
        final Tree expectedTree;
        final Tree actualTree;
        if (expectedChildCount < actualChildCount) {
          expectedTree = expected;
          actualTree = actual.getChild(size);
        } else {
          expectedTree = expected.getChild(size);
          actualTree = actual;
        }
        result = DifferentiaUtils.getDifferenceResult(expectedTree, actualTree);
      } else {
        result = ComparisonResult.NO_DIFFERENCE;
      }
    } else {
      result = childResult;
    }
    return result;
  }

  private String getNodeName(Tree tree) {
    final String name;
    if (tree.getParent() == null) {
      name = "";
    } else {
      name = getNodeName(tree.getParent()) + ":" + tree.getText();
    }
    return name;
  }

  static class Result {
    ComparisonResult result;
    Exception exception;
  }

}
