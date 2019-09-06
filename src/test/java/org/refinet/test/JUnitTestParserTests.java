package org.refinet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.refinet.api.TestCase;
import org.refinet.api.TestItem;
import org.refinet.parser.ClassNameCollector;
import org.refinet.parser.JUnitTestParser;
import org.refinet.parser.TagNameCollector;
import org.refinet.parser.TestDestroyCollector;
import org.refinet.parser.TestInitCollector;
import org.refinet.parser.TestPreparationCollector;
import org.refinet.parser.TestTestCollector;
import org.refinet.parser.TestWrapupCollector;
import org.refinet.steps.CalculatorApp;
import org.refinet.steps.CalculatorUsage;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class JUnitTestParserTests {
	
	String classBegin = "@DisplayName(\"Test our calculator app for basic arithmetic operations\")\n" +
            "public class CalculatorTests {\n";
    String classEnd = "}";
    
    String test =" /**\r\n" + 
    		"   * Open the calculator app for testing.\r\n" + 
    		"   */\r\n" + 
    		"  @BeforeAll\r\n" + 
    		"  public void loadCalculator() {\r\n" + 
    		"    System.out.println(\"Some dummy debug information\");\r\n" + 
    		"    CalculatorApp.open();\r\n" + 
    		"  }\r\n" + 
    		"\r\n" + 
    		"@Test\r\n" + 
    		"  @DisplayName(\"Our calculator should be able to add two numbers\")\r\n" + 
    		"  @Tag(\"regression\")\r\n" + 
    		"  @Tag(\"dashcalc\")\r\n" + 
    		"  public void testThatCalculatorCanAddTwoNumbers() {\r\n" + 
    		"    System.out.println(\"Some dummy debug information\");\r\n" + 
    		"    CalculatorUsage.enterNumber(4);\r\n" + 
    		"    CalculatorUsage.enterOperation(\"+\");\r\n" + 
    		"    CalculatorUsage.enterNumber(5);\r\n" + 
    		"    CalculatorUsage.enterOperation(\"=\");\r\n" + 
    		"    CalculatorUsage.assertThatResultIs(9);\r\n" + 
    		"    System.out.println(\"Some dummy debug information\");\r\n" + 
    		"  }\r\n" + 
    		"\r\n" + 
    		"\r\n" + 
    		"  @Test\r\n" + 
    		"  @DisplayName(\"Our funny developers implemented an easter egg\")\r\n" + 
    		"  @Disabled\r\n" + 
    		"  public void testThatCalculatorHasImplementedAnEasterEgg() {\r\n" + 
    		"    CalculatorUsage.enterOperation(\"+\");\r\n" + 
    		"    CalculatorUsage.enterOperation(\"-\");\r\n" + 
    		"    CalculatorUsage.enterOperation(\"*\");\r\n" + 
    		"    CalculatorUsage.enterOperation(\"/\");\r\n" + 
    		"    CalculatorUsage.enterOperation(\"=\");\r\n" + 
    		"    CalculatorUsage.assertThatCalculatorShowsAnError(\"Its not easter yet :-)\");\r\n" + 
    		"  }\r\n" + 
    		"\r\n" + 
    		"\r\n" + 
    		"  @Test\r\n" + 
    		"  @DisplayName(\"Our calculator should fail if we try to divide by zero\")\r\n" + 
    		"  @Tag(\"regression\")\r\n" + 
    		"  @Tag(\"negative\")\r\n" + 
    		"  @Tag(\"runtime:slow\")\r\n" + 
    		"  public void testThatCalculatorFailsIfDivisorIsNull() {\r\n" + 
    		"    CalculatorUsage.enterNumber(5);\r\n" + 
    		"    CalculatorUsage.enterOperation(\"/\");\r\n" + 
    		"    CalculatorUsage.enterNumber(0);\r\n" + 
    		"    CalculatorUsage.enterOperation(\"=\");\r\n" + 
    		"    CalculatorUsage.assertThatCalculatorShowsAnError(\"Div0\");\r\n" + 
    		"  }\r\n" + 
    		"\r\n" + 
    		" @AfterEach\r\n" + 
    		"  public void checkThatCalculatorDidNotLogAnyErrors() {\r\n" + 
    		"    CalculatorUsage.assertThatCalculatorDidNotLogAnyError();\r\n" + 
    		"  }";
    
    
    @Test
    public void testTestCaseIsExtracted() {
    	    	String givenTestToParse = classBegin + test + classEnd;
    	      
    	    	 List<TestCase> tests = JUnitTestParser.parse(givenTestToParse);
    	        
    	        List<TestCase> erwarteteWerte = new ArrayList<>();
    	        
    	        // Beispiel TestCase 1 
    	        TestCase tc= new TestCase();
    	        TestItem ti = new TestItem();
    	        tc.suite = "CalculatorTests";
    	        tc.suiteDescription = "Test our calculator app for basic arithmetic operations";  		
    	        ti.id = "loadCalculator";
    	      
    	        tc.init.add(ti);
    	        
    	        ti = new TestItem();
    	        ti.id = "testThatCalculatorCanAddTwoNumbers";
    	        ti.name= "Our calculator should be able to add two numbers";
    	        tc.test.add(ti);
    	
    	        ti = new TestItem();
    	        ti.id= "checkThatCalculatorDidNotLogAnyErrors";
    	        
    	        tc.wrapup.add(ti);
    	        
    	        ti = new TestItem();
    	        tc.tag.put("regression", "");
    	        tc.tag.put("dashcalc", "");
    	        erwarteteWerte.add(tc);
   
    	        // Beispiel TestCase 2
    	        tc= new TestCase();
    	        ti = new TestItem();
    	        tc.suite = "CalculatorTests";
    	        tc.suiteDescription = "Test our calculator app for basic arithmetic operations";
    	        ti.id = "loadCalculator";
    	     
    	        tc.init.add(ti);
    	        
    	        ti = new TestItem();
    	        ti.id = "testThatCalculatorHasImplementedAnEasterEgg";
    	        ti.name= "Our funny developers implemented an easter egg";
    	        tc.test.add(ti);
    	        
    	        ti = new TestItem();
    	        ti.id= "checkThatCalculatorDidNotLogAnyErrors";
    	    
    	        tc.wrapup.add(ti);	
    	        erwarteteWerte.add(tc);
    	        
    	        // Beispiel TestCase 3 
    	        tc= new TestCase();
    	        ti = new TestItem();
    	        tc.suite = "CalculatorTests";
    	        tc.suiteDescription = "Test our calculator app for basic arithmetic operations";
    	        ti.id = "loadCalculator";
    	       
    	        tc.init.add(ti);
    	        
    	        ti = new TestItem();
    	        ti.id = "testThatCalculatorFailsIfDivisorIsNull";
    	        ti.name= "Our calculator should fail if we try to divide by zero";
    	        tc.test.add(ti);
    	        
    	        ti = new TestItem();
    	        ti.id= "checkThatCalculatorDidNotLogAnyErrors";
    	      
    	        tc.wrapup.add(ti);
    	        ti = new TestItem();
    	        tc.tag.put("regression", "");
    	        tc.tag.put("negative", "");
    	        tc.tag.put("runtime", "slow");
    	        
    	        erwarteteWerte.add(tc);
    	        
    	        assertEquals(erwarteteWerte.toString(), tests.toString());  
    	    }
    	    
}

