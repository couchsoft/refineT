package org.refinet.parser;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassNameCollector  extends VoidVisitorAdapter<String> {
	
	public void visit(ClassOrInterfaceDeclaration c, String collector) {
		super.visit(c, collector);
		String className = c.getNameAsString();
		collector =  className;
		}

}
