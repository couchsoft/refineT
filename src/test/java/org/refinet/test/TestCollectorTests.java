package org.refinet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.refinet.api.TestItem;
import org.refinet.parser.TestTestCollector;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class TestCollectorTests {

    String classBegin = "@DisplayName(\"Test our calculator app for basic arithmetic operations\")\n" +
            "public class CalculatorTests {\n";
    String classEnd = "}";


    String test = "@Test\r\n" +
            "  @DisplayName(\"Our calculator should be able to add two numbers\")\r\n" +
            "  @Tag(\"regression\")\r\n" +
            "  @Tag(\"dashcalc\")\r\n" +
            "  public void testThatCalculatorCanAddTwoNumbers() {}";

    @Test
    public void testTestIsExtracted() {
        String givenTestToParse = classBegin + test + classEnd;
        CompilationUnit cu = StaticJavaParser.parse(givenTestToParse);
        List<TestItem> test = new ArrayList<>();
        new TestTestCollector().visit(cu, test);
        assertEquals("Our calculator should be able to add two numbers", test.get(0).name);
    }

}
