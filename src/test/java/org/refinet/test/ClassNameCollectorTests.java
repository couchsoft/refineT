package org.refinet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.refinet.parser.ClassNameCollector;


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
