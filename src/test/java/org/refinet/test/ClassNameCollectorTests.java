package org.refinet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.refinet.parser.ClassNameCollector;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class ClassNameCollectorTests {
	
	String test = "@DisplayName(\"Test our calculator app for basic arithmetic operations\")\r\n" + 
			"public class CalculatorTests {\r\n" + 
			"}";
	
	@Test
	  public void testThatClassNameIsExtracted() {
		CompilationUnit cu = StaticJavaParser.parse(test);
		ArrayList<String> className = new ArrayList<>();
		new ClassNameCollector().visit(cu, className);
		assertEquals("CalculatorTests", className.get(0));
	  }
	
	

}
