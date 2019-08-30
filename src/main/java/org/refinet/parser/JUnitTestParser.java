package org.refinet.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import org.refinet.api.TestCase;
import org.refinet.api.TestItem;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class JUnitTestParser<JavaSymbolSolver>  {
	
	public static List<TestCase> parse(Path path) {
		
		List<TestCase> testcase = new ArrayList<>();
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*Tests.{java,class,jar}")) {
		    for (Path entry: stream) {
		    	File f =  new File(path.toString() +"/"+ entry.getFileName());
		    	testcase.addAll(parse(f));
		    }
		} catch (IOException x) {
		    System.err.println(x);
		}
		return testcase;
	}

	public static List<TestCase> parse(File file) {
			String junitFile = "";
			// file in String umwandeln
			try (Stream<String> lines = Files.lines(Paths.get(file.getPath()))){
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
		
		ArrayList<String> className = new ArrayList<>();
		
		new ClassNameCollector().visit(cu, className);
		
		ArrayList<Hashtable<String,String>> tags = new ArrayList<>();
		
		new TagNameCollector().visit(cu, tags);
		
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
			t.suite = className.get(0);
			t.suiteDescription = className.get(1);
			t.tag = tags.get(j);
		}
		
		
		return tests;
		
	}
	


}


