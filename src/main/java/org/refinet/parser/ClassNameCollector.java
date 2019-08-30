package org.refinet.parser;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.refinet.api.TestItem;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassNameCollector  extends VoidVisitorAdapter<String>{
	
	public void visit(ClassOrInterfaceType c, String collector) {
		super.visit(c, collector);
		String className = c.getNameAsString();
		collector =  className;
		}

}
