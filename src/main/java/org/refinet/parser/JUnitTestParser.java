package org.refinet.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.refinet.api.TestCase;
import org.refinet.api.TestItem;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;

public class JUnitTestParser<JavaSymbolSolver>  {

	public static void parse(File testFile) {

		try {
			CompilationUnit cu = StaticJavaParser.parse(testFile);
			
			List<TestCase> tests  =  new ArrayList<>();
			
			List<TestItem> init = new ArrayList<>();
		
			VoidVisitor<List<TestItem>> tic = new TestInitCollector(testFile.getName().substring(0, testFile.getName().length()-5));
			tic.visit(cu, init );
			
			List<TestItem> preparation = new ArrayList<>();
			
			VoidVisitor<List<TestItem>> tpc = new TestPreparationCollector(testFile.getName().substring(0, testFile.getName().length()-5));
			tpc.visit(cu, preparation);
			
			List<TestItem> wrapup = new ArrayList<>();
			
			VoidVisitor<List<TestItem>> twc = new TestWrapupCollector(testFile.getName().substring(0, testFile.getName().length()-5));
			twc.visit(cu, wrapup);
			
			List<TestItem> destroy = new ArrayList<>();
			
			VoidVisitor<List<TestItem>> tdc = new TestDestroyCollector(testFile.getName().substring(0, testFile.getName().length()-5));
			tdc.visit(cu, destroy);
			
			List<TestItem> test = new ArrayList<>();
			
			VoidVisitor<List<TestItem>> ttc = new TestTestCollector(testFile.getName().substring(0, testFile.getName().length()-5));
			ttc.visit(cu,test);
			
			
			for (int j = 0; j < test.size(); j++) {
				TestCase t = new TestCase();
				t.init = init;
				t.preparation = preparation;
				t.wrapup = wrapup;
				t.destroy = destroy;
				t.test.add(test.get(j));
				tests.add(t);
			}
		
			
			for (int i = 0; i < tests.size(); i++) {
				System.out.println(tests.get(i));
			}
			
	
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		
		
		
	}
	


}


