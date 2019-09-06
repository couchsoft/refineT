package org.refinet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.refinet.api.TestItem;
import org.refinet.parser.TestDestroyCollector;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class DestroyCollectorTests {
	
	 String classBegin = "@DisplayName(\"Test our calculator app for basic arithmetic operations\")\n" +
	            "public class CalculatorTests {\n";
	    String classEnd = "}";
	
	   String test = "@AfterAll\r\n" +
	            "  @DisplayName(\"After we finished all the tests, we close our calculator app\")\r\n" +
	            "  public void terminateCalculator() {\r\n" +
	            "    System.out.println(\"Some dummy debug information\");\r\n" +
	            "    CalculatorApp.close();\r\n" +
	            "  }";

	    @Test
	    public void testDestroyIsExtracted() {
	    	String givenTestToParse = classBegin + test + classEnd;
	        CompilationUnit cu = StaticJavaParser.parse(givenTestToParse);
	        List<TestItem> destroy = new ArrayList<>();
	        new TestDestroyCollector().visit(cu, destroy);
	        assertEquals("After we finished all the tests, we close our calculator app", destroy.get(0).name);
	    }

}
