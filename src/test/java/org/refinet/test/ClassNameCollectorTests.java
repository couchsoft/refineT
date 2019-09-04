package org.refinet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.refinet.api.TestItem;
import org.refinet.parser.ClassNameCollector;
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

    String test2 = " @Test\r\n" +
            "  @DisplayName(\"Our calculator should be able to add two numbers\")\r\n" +
            "  @Tag(\"regression\")\r\n" +
            "  @Tag(\"dashcalc\")\r\n" +
            "  public void testThatCalculatorCanAddTwoNumbers() {}";


    @Test
    public void testThatTagIsExtracted() {
        CompilationUnit cu = StaticJavaParser.parse(test2);
        ArrayList<Hashtable<String, String>> tags = new ArrayList<>();
        new TagNameCollector().visit(cu, tags);
        assertEquals("regression", tags.get(0));
    }

    String test3 = "@AfterAll\r\n" +
            "  @DisplayName(\"After we finished all the tests, we close our calculator app\")\r\n" +
            "  public void terminateCalculator() {\r\n" +
            "    System.out.println(\"Some dummy debug information\");\r\n" +
            "    CalculatorApp.close();\r\n" +
            "  }";


    @Test
    public void testDestroyIsExtracted() {
        CompilationUnit cu = StaticJavaParser.parse(test3);
        List<TestItem> destroy = new ArrayList<>();
        new TestDestroyCollector().visit(cu, destroy);
        assertEquals("After we finished all the tests, we close our calculator app", destroy.get(0).name);
    }

    String test4 = " @BeforeAll\r\n" +
            "  public void loadCalculator() {}";


    @Test
    public void testInitIsExtracted() {
        CompilationUnit cu = StaticJavaParser.parse(test4);
        List<TestItem> init = new ArrayList<>();
        new TestInitCollector().visit(cu, init);
        assertEquals("loadCalculator", init.get(0).id);
    }

    String test5 = "@BeforeEach\r\n" +
            "  public void resetCalculator() {}";

    @Test
    public void testPreparationIsExtracted() {
        CompilationUnit cu = StaticJavaParser.parse(test5);
        List<TestItem> preparation = new ArrayList<>();
        new TestPreparationCollector().visit(cu, preparation);
        assertEquals("resetCalculator", preparation.get(0).id);
    }

    String test6 = "@Test\r\n" +
            "  @DisplayName(\"Our calculator should be able to add two numbers\")\r\n" +
            "  @Tag(\"regression\")\r\n" +
            "  @Tag(\"dashcalc\")\r\n" +
            "  public void testThatCalculatorCanAddTwoNumbers() {}";

    @Test
    public void testTestIsExtracted() {
        CompilationUnit cu = StaticJavaParser.parse(test6);
        List<TestItem> test = new ArrayList<>();
        new TestTestCollector().visit(cu, test);
        assertEquals("Our calculator should be able to add two numbers", test.get(0).name);
    }

    String test7 = "@AfterEach\r\n" +
            "  public void checkThatCalculatorDidNotLogAnyErrors() {\r\n" +
            "    CalculatorUsage.assertThatCalculatorDidNotLogAnyError();\r\n" +
            "  }";

    @Test
    public void testWarpupIsExtracted() {
        CompilationUnit cu = StaticJavaParser.parse(test7);
        List<TestItem> wrapup = new ArrayList<>();
        new TestWrapupCollector().visit(cu, wrapup);
        assertEquals("checkThatCalculatorDidNotLogAnyErrors", wrapup.get(0).id);
    }


}
