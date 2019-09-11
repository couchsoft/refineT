package org.refinet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Hashtable;
import org.junit.jupiter.api.Test;
import org.refinet.parser.TagNameCollector;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class TagCollectorTests {

    String classBegin = "@DisplayName(\"Test our calculator app for basic arithmetic operations\")\n" +
            "public class CalculatorTests {\n";
    String classEnd = "}";
    String test = "@Test\r\n" +
            "@DisplayName(\"Our calculator should be able to add two numbers\")\r\n" +
            "@Tag(\"regression\")\r\n" +
            "@Tag(\"dashcalc\")\r\n" +
            "public void testThatCalculatorCanAddTwoNumbers() {\r\n"
            + "}";

    @Test
    public void testThatTagIsExtracted() {
        String givenTestToParse = classBegin + test + classEnd;
        CompilationUnit cu = StaticJavaParser.parse(givenTestToParse);
        ArrayList<Hashtable<String, String>> tags = new ArrayList<>();
        new TagNameCollector().visit(cu, tags);
        Hashtable<String, String> testwert = new Hashtable<>();
        testwert.put("dashcalc", "");
        testwert.put("regression", "");
        assertEquals(testwert, tags.get(0));
    }

    String test2 = " @Test\r\n" +
            "  @DisplayName(\"Our calculator should be able to divide two numbers\")\r\n" +
            "  @Tag(\"regression\")\r\n" +
            "  @Tag(\"runtime:fast\")\r\n" +
            "  public void testThatCalculatorCanDivideTwoNumbers() {\r\n" +
            "  }";

    @Test
    public void testThatTagWithValueIsExtracted() {
        String givenTestToParse = classBegin + test2 + classEnd;
        CompilationUnit cu = StaticJavaParser.parse(givenTestToParse);
        ArrayList<Hashtable<String, String>> tags = new ArrayList<>();
        new TagNameCollector().visit(cu, tags);
        Hashtable<String, String> testwert = new Hashtable<>();
        testwert.put("regression", "");
        testwert.put("runtime", "fast");
        assertEquals(testwert, tags.get(0));

    }
}
