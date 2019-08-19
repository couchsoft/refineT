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

		//ArrayList<TestCase> tests = new ArrayList<>();
		List<TestCase> testcasetests = new ArrayList<>();
		try {
			CompilationUnit cu = StaticJavaParser.parse(testFile);
			
			VoidVisitor<List<TestCase>> tcc = new TestCaseCollector(testFile.getName().substring(0, testFile.getName().length()-5));
			tcc.visit(cu, testcasetests);
			testcasetests.forEach(n -> {
				//System.out.println(n);
				//tests.add(n);
			});

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < testcasetests .size(); i++) {
			System.out.println(testcasetests .get(i));
		}
		
	}
	


}


