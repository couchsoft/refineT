package org.refinet.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.refinet.api.TestCase;
import org.refinet.api.TestItem;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class JUnitTestParser<JavaSymbolSolver>  {

	public static List<TestCase> parse(File path) {
			String junitFile = "";
			
			// file in String umwandeln
			try (Stream<String> lines = Files.lines(Paths.get(path.getAbsolutePath()))){
					junitFile = lines.collect(Collectors.joining(System.lineSeparator()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// file einlesen
			return parse(junitFile);
			
			
	}
	
	
	public static List<TestCase> parse(String junitTests) {
		
		
		List<TestCase> tests  =  new ArrayList<>();

		CompilationUnit cu = StaticJavaParser.parse(junitTests);
		
		String className = "aa";
		
		new ClassNameCollector().visit(cu, className);
		
		System.out.println(className);
		
		List<TestItem> init = new ArrayList<>();
		
		new TestInitCollector().visit(cu, init);
		
		List<TestItem> preparation = new ArrayList<>();
		
		new TestPreparationCollector().visit(cu, preparation);
		
		List<TestItem> wrapup = new ArrayList<>();
		
		new TestWrapupCollector().visit(cu, wrapup);
		
		List<TestItem> destroy = new ArrayList<>();
		
		new TestDestroyCollector().visit(cu, destroy);
		
		List<TestItem> test = new ArrayList<>();
		
		new TestTestCollector().visit(cu,test);
		
		
		
		for (int j = 0; j < test.size(); j++) {
			TestCase t = new TestCase();
			t.init = init;
			t.preparation = preparation;
			t.wrapup = wrapup;
			t.destroy = destroy;
			t.test.add(test.get(j));
			tests.add(t);
			
		}
		
		return tests;
		
	}
	


}


