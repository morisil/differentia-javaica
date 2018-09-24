lexer grammar Java;

@members {

/** 
 *  If <code>true</code> whitespaces will be preserved within the hidden channel, 
 *  otherwise the appropriate tokens will be thrown away.
 *  <p>
 *  The default value of this flag is <code>false</code>.
 */
public boolean mPreserveWhitespaces = false;

/** 
 *  If <code>true</code> javadoc comments will be preserved within the hidden 
 *  channel, otherwise the appropriate tokens will be thrown away.
 *  <p>
 *  The default value of this flag is <code>false</code>.
 */
public boolean mPreserveJavaDocComments = false;

/** 
 *  If <code>true</code> (non javadoc) block comments will be preserved within the 
 *  hidden channel, otherwise the appropriate tokens will be thrown away.
 *  <p>
 *  The default value of this flag is <code>false</code>.
 */
public boolean mPreserveBlockComments = false;

/** 
 *  If <code>true</code> single line comments will be preserved within the hidden 
 *  channel, otherwise the appropriate tokens will be thrown away.
 *  <p>
 *  The default value of this flag is <code>false</code>.
 */
public boolean mPreserveLineComments = false;

}
@header {
package com.xemantic.differentia.antlr;
}

AND : '&' ;
AND_ASSIGN : '&=' ;
ASSIGN : '=' ;
AT : '@' ;
BIT_SHIFT_RIGHT : '>>>' ;
BIT_SHIFT_RIGHT_ASSIGN : '>>>=' ;
COLON : ':' ;
COMMA : ',' ;
DEC : '--' ;
DIV : '/' ;
DIV_ASSIGN : '/=' ;
DOT : '.' ;
DOTSTAR : '.*' ;
ELLIPSIS : '...' ;
EQUAL : '==' ;
GREATER_OR_EQUAL : '>=' ;
GREATER_THAN : '>' ;
INC : '++' ;
LBRACK : '[' ;
LCURLY : '{' ;
LESS_OR_EQUAL : '<=' ;
LESS_THAN : '<' ;
LOGICAL_AND : '&&' ;
LOGICAL_NOT : '!' ;
LOGICAL_OR : '||' ;
LPAREN : '(' ;
MINUS : '-' ;
MINUS_ASSIGN : '-=' ;
MOD : '%' ;
MOD_ASSIGN : '%=' ;
NOT : '~' ;
NOT_EQUAL : '!=' ;
OR : '|' ;
OR_ASSIGN : '|=' ;
PLUS : '+' ;
PLUS_ASSIGN : '+=' ;
QUESTION : '?' ;
RBRACK : ']' ;
RCURLY : '}' ;
RPAREN : ')' ;
SEMI : ';' ;
SHIFT_LEFT : '<<' ;
SHIFT_LEFT_ASSIGN : '<<=' ;
SHIFT_RIGHT : '>>' ;
SHIFT_RIGHT_ASSIGN : '>>=' ;
STAR : '*' ;
STAR_ASSIGN : '*=' ;
XOR : '^' ;
XOR_ASSIGN : '^=' ;
ABSTRACT : 'abstract' ;
ASSERT : 'assert' ;
BOOLEAN : 'boolean' ;
BREAK : 'break' ;
BYTE : 'byte' ;
CASE : 'case' ;
CATCH : 'catch' ;
CHAR : 'char' ;
CLASS : 'class' ;
CONTINUE : 'continue' ;
DEFAULT : 'default' ;
DO : 'do' ;
DOUBLE : 'double' ;
ELSE : 'else' ;
ENUM : 'enum' ;
EXTENDS : 'extends' ;
FALSE : 'false' ;
FINAL : 'final' ;
FINALLY : 'finally' ;
FLOAT : 'float' ;
FOR : 'for' ;
IF : 'if' ;
IMPLEMENTS : 'implements' ;
INSTANCEOF : 'instanceof' ;
INTERFACE : 'interface' ;
IMPORT : 'import' ;
INT : 'int' ;
LONG : 'long' ;
NATIVE : 'native' ;
NEW : 'new' ;
NULL : 'null' ;
PACKAGE : 'package' ;
PRIVATE : 'private' ;
PROTECTED : 'protected' ;
PUBLIC : 'public' ;
RETURN : 'return' ;
SHORT : 'short' ;
STATIC : 'static' ;
STRICTFP : 'strictfp' ;
SUPER : 'super' ;
SWITCH : 'switch' ;
SYNCHRONIZED : 'synchronized' ;
THIS : 'this' ;
THROW : 'throw' ;
THROWS : 'throws' ;
TRANSIENT : 'transient' ;
TRUE : 'true' ;
TRY : 'try' ;
VOID : 'void' ;
VOLATILE : 'volatile' ;
WHILE : 'while' ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1169
HEX_LITERAL : '0' ('x'|'X') HEX_DIGIT+ INTEGER_TYPE_SUFFIX? ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1171
DECIMAL_LITERAL : ('0' | '1'..'9' '0'..'9'*) INTEGER_TYPE_SUFFIX? ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1173
OCTAL_LITERAL : '0' ('0'..'7')+ INTEGER_TYPE_SUFFIX? ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1175
fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1178
fragment
INTEGER_TYPE_SUFFIX : ('l'|'L') ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1181
FLOATING_POINT_LITERAL
    :   ('0'..'9')+ 
        (
            DOT ('0'..'9')* EXPONENT? FLOAT_TYPE_SUFFIX?
        |   EXPONENT FLOAT_TYPE_SUFFIX?
        |   FLOAT_TYPE_SUFFIX
        )
    |   DOT ('0'..'9')+ EXPONENT? FLOAT_TYPE_SUFFIX?
    ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1191
fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1194
fragment
FLOAT_TYPE_SUFFIX : ('f'|'F'|'d'|'D') ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1197
CHARACTER_LITERAL
    :   '\'' ( ESCAPE_SEQUENCE | ~('\''|'\\') ) '\''
    ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1201
STRING_LITERAL
    :  '"' ( ESCAPE_SEQUENCE | ~('\\'|'"') )* '"'
    ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1205
fragment
ESCAPE_SEQUENCE
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESCAPE
    |   OCTAL_ESCAPE
    ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1212
fragment
OCTAL_ESCAPE
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1219
fragment
UNICODE_ESCAPE
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1224
IDENT
    :   JAVA_ID_START (JAVA_ID_PART)*
    ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1228
fragment
JAVA_ID_START
    :  '\u0024'
    |  '\u0041'..'\u005a'
    |  '\u005f'
    |  '\u0061'..'\u007a'
    |  '\u00c0'..'\u00d6'
    |  '\u00d8'..'\u00f6'
    |  '\u00f8'..'\u00ff'
    |  '\u0100'..'\u1fff'
    |  '\u3040'..'\u318f'
    |  '\u3300'..'\u337f'
    |  '\u3400'..'\u3d2d'
    |  '\u4e00'..'\u9fff'
    |  '\uf900'..'\ufaff'
    ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1245
fragment
JAVA_ID_PART
    :  JAVA_ID_START
    |  '\u0030'..'\u0039'
    ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1251
WS  :  (' '|'\r'|'\t'|'\u000C'|'\n') 
    {   
        if (!mPreserveWhitespaces) {
            skip();
        } else {
            $channel = HIDDEN;
        }
    }
    ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1261
BLOCK_COMMENT // excluding javadoc comments
    :   (   '/*' ~'*' ( options {greedy=false;} : . )* '*/'
        |   '/**/'
        )
    {   
        if (!mPreserveBlockComments) {
            skip();
        } else {
            $channel = HIDDEN;
        }
    }
    ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1274
JAVADOC_COMMENT
    :   '/**' ( options {greedy=false;} : . )* '*/'
    {   
        if (!mPreserveJavaDocComments) {
            skip();
        } else {
            $channel = HIDDEN;
        }
    }
    ;

// $ANTLR src "src/main/java/com/xemantic/differentia/antlr/Java.g" 1285
LINE_COMMENT
    : '//' ~('\n'|'\r')* '\r'? '\n'
    {   
        if (!mPreserveLineComments) {
            skip();
        } else {
            $channel = HIDDEN;
        }
    }
    ;
