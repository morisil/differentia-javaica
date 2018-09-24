_Compare java source files as logical structure_

# About differentia-javaica

TL;DR parse Abstract Syntax Tree (AST) of generated Java source and compare the structure
with AST of expected source.

This utility was born some years ago when I was writing huge amount of code generators
for our toolchain at [Nordic Consulting & Development Company](https://www.ncdc.eu/).
It was part of our Model Driven Architecture approach where UML models and product
metadata were used to generate updatable domain model, data access layer, parts of UI, etc.

Each generator was accompanied with test suites verifying generated source code by
comparing it to expected Java files. While the generators were being
develop continuously, I discovered that just comparing textual content is not flexible enough
and instead we have to verify the logical structure of generated code.

**Note:** this project is only supporting Java 1.5. These days I would rather use:

https://github.com/google/compile-testing
  
# Dependencies

## ANTLR

http://www.antlr.org/

ANTLR stands for ANother Tool for Language Recognition, perfect tool for building parsers for any possible languages.
In our case it produces Java AST.

## JSourceObjectizer

This project provides very handy ANTLRv3 AST grammar for Java 1.5.

http://www.habelitz.com/

# Usage

## Command Line

While having `differentia-javaica` jar in your classpath run:
```
$ java pl.ncdc.differentia.Differentia src1 src2
```

## JUnit

```java
import static com.xemantic.differentia.DifferentiaAssert.assertSourcesEqual;

public class CodeGeneratorTest {
  
  @Test
  public void shouldGenerateCode() {
    assertSourcesEqual("expected/com/company/Foo.java", "actual/com/company/Foo.java");      
  }
    
}
```
