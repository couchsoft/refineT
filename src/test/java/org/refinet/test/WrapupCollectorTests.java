package org.refinet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.refinet.api.TestItem;
import org.refinet.parser.TestWrapupCollector;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class WrapupCollectorTests {
	
	String classBegin = "@DisplayName(\"Test our calculator app for basic arithmetic operations\")\n" +
            "public class CalculatorTests {\n";
    String classEnd = "}";

    String test = "@AfterEach\r\n" +
            "  public void checkThatCalculatorDidNotLogAnyErrors() {\r\n" +
            "    CalculatorUsage.assertThatCalculatorDidNotLogAnyError();\r\n" +
            "  }";

    @Test
    public void testWarpupIsExtracted() {
    	String givenTestToParse = classBegin + test + classEnd;
        CompilationUnit cu = StaticJavaParser.parse(givenTestToParse);
        List<TestItem> wrapup = new ArrayList<>();
        new TestWrapupCollector().visit(cu, wrapup);
        assertEquals("checkThatCalculatorDidNotLogAnyErrors", wrapup.get(0).id);
    }

}
