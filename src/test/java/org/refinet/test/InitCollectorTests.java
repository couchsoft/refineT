package org.refinet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.refinet.api.TestItem;
import org.refinet.parser.TestInitCollector;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class InitCollectorTests {


    String classBegin = "@DisplayName(\"Test our calculator app for basic arithmetic operations\")\n" +
            "public class CalculatorTests {\n";
    String classEnd = "}";

    String test = " @BeforeAll\r\n" +
            "  public void loadCalculator() {}";

    @Test
    public void testInitIsExtracted() {
    	String givenTestToParse = classBegin + test + classEnd;
        CompilationUnit cu = StaticJavaParser.parse(givenTestToParse);
        List<TestItem> init = new ArrayList<>();
        new TestInitCollector().visit(cu, init);
        assertEquals("loadCalculator", init.get(0).id);
    }
}
