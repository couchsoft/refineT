package org.refinet.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.refinet.api.TestCase;
import org.refinet.api.TestItem;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class JUnitTestParser<JavaSymbolSolver>  {

	public static void parse(File testFile) {

		try {
			CompilationUnit cu = StaticJavaParser.parse(testFile);
			
			List<TestCase> tests  = (List<TestCase>) new TestCase();
			
			List<TestItem> init = (List<TestItem>) new TestItem();;
		
			VoidVisitor<List<TestItem>> tic = new TestInitCollector(testFile.getName().substring(0, testFile.getName().length()-5));
			tic.visit(cu, init );
			
			List<TestItem> preparation = (List<TestItem>) new TestItem();
			
			VoidVisitor<List<TestItem>> tpc = new TestPreparationCollector(testFile.getName().substring(0, testFile.getName().length()-5));
			tpc.visit(cu, preparation);
			
			List<TestItem> wrapup = (List<TestItem>) new TestItem();
			
			VoidVisitor<List<TestItem>> twc = new TestWrapupCollector(testFile.getName().substring(0, testFile.getName().length()-5));
			twc.visit(cu, wrapup);
			
			List<TestItem> destroy = (List<TestItem>) new TestItem();
			
			VoidVisitor<List<TestItem>> tdc = new TestDestroyCollector(testFile.getName().substring(0, testFile.getName().length()-5));
			tdc.visit(cu, destroy);
			
			List<TestItem> test = (List<TestItem>) new TestItem();
			
			VoidVisitor<List<TestItem>> ttc = new TestTestCollector(testFile.getName().substring(0, testFile.getName().length()-5));
			ttc.visit(cu,test);
			
			
		for (int i = 0; i < test.size(); i++) {
			System.out.println(test.get(i));
		}
			
			
			
	
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		
		
		
	}
	


}


