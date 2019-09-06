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

    String classBegin = "@DisplayName(\"Test our calculator app for basic arithmetic operations\")\n" +
            "public class CalculatorTests {\n";
    String classEnd = "}";
    
   @Test
    public void testThatClassNameIsExtracted() {
        CompilationUnit cu = StaticJavaParser.parse(classBegin + classEnd);
        ArrayList<String> className = new ArrayList<>();
        new ClassNameCollector().visit(cu, className);
        assertEquals("CalculatorTests", className.get(0));
    }


}
