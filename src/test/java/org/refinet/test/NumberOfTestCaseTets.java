package org.refinet.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.refinet.api.TestCase;
import org.refinet.parser.JUnitTestParser;

public class NumberOfTestCaseTets {

	String test = "src/main/resources/tests";
	
	 @Test
	    public void testNumberOfTestCases() {
		 Path path = Paths.get(test);
	        List<TestCase> tests = JUnitTestParser.parse(path);
	        assertEquals(7,tests.size());
		 
	 }
}
