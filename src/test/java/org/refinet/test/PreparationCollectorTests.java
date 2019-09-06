package org.refinet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.refinet.api.TestItem;
import org.refinet.parser.TestPreparationCollector;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class PreparationCollectorTests {
	
	 String classBegin = "@DisplayName(\"Test our calculator app for basic arithmetic operations\")\n" +
	            "public class CalculatorTests {\n";
	    String classEnd = "}";

	 String test = "@BeforeEach\r\n" +
	            "  public void resetCalculator() {}";

	    @Test
	    public void testPreparationIsExtracted() {
	    	String givenTestToParse = classBegin + test + classEnd;
	        CompilationUnit cu = StaticJavaParser.parse(givenTestToParse);
	        List<TestItem> preparation = new ArrayList<>();
	        new TestPreparationCollector().visit(cu, preparation);
	        assertEquals("resetCalculator", preparation.get(0).id);
	    }

}
