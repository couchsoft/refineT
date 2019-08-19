package org.refinet.parser;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.refinet.api.TestItem;
import org.refinet.api.TestStep;
import org.refinet.api.TestCase;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.Node.ParentsVisitor;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.SimpleName;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TestCaseCollector extends VoidVisitorAdapter<List<TestCase>> {

	String file;
	TestCase tc = new TestCase();
	
	public TestCaseCollector(String file) {
		this.file = file;
	}
	
	public void visit(MethodDeclaration md, List<TestCase> collector) {
		super.visit(md, collector);
		TestItem ti= new TestItem();
		ti.id = md.getNameAsString();
		ti.suite = file;
		if (md.getAnnotationByClass(DisplayName.class).isPresent()) {
			ti.name = md.getAnnotationByClass(DisplayName.class).get().toString();
			ti.name = ti.name.substring(14, ti.name.length()-2);
		}
		if(md.getJavadocComment().isPresent()) {
			ti.description = md.getJavadocComment().get().getContent().trim();
			ti.description.replaceAll(" ", "");
		}
		if (md.getBody().isPresent()) {
            NodeList<Statement> statements = md.getBody().get().getStatements();
            statements.forEach(s -> {
                  List<Node> nodes = s.getChildNodes();
                  ti.steps.add(new TestStep("", ti.id, s.toString()));
                  nodes.forEach(n -> {
                      // System.out.println(ti.steps);
                      //  System.out.println(n);
                	  ParentsVisitor pv =  new ParentsVisitor(n);
                      //System.out.println(pv.next().getParentNodeForChildren());
                	  
                  });
            });
       
		}
		if (md.getAnnotationByClass(BeforeAll.class).isPresent()) {
			tc.init.add(ti);
		}
		if (md.getAnnotationByClass(BeforeEach.class).isPresent()) {
			tc.preparation.add(ti);
		}
		if (md.getAnnotationByClass(AfterEach.class).isPresent()) {
			tc.wrapup.add(ti);
		}
		if (md.getAnnotationByClass(AfterAll.class).isPresent()) {
			tc.destroy.add(ti);
		}
		if (md.getAnnotationByClass(Test.class).isPresent()) {
			tc.test.add(ti);
		}
		

		collector.add(tc);	
	}
}