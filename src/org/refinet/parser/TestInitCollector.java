package org.refinet.parser;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;

import org.refinet.api.TestItem;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TestInitCollector extends VoidVisitorAdapter<List<TestItem>> {

	String file;
	TestItem tc = new TestItem();
	
	public TestInitCollector(String file) {
		this.file = file;
	}
	public void visit(MethodDeclaration md, List<TestItem> collector) {
		super.visit(md, collector);
		if (md.getAnnotationByClass(BeforeAll.class).isPresent()) {
		
		TestItemCollector tc= new TestItemCollector();
		TestItem ti = tc.TestItemCollector(file, md);
			
		collector.add(ti);	
		}
		
		

		
	}
}