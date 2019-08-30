package org.refinet.parser;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.refinet.api.TestItem;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.Node.ParentsVisitor;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.javadoc.description.JavadocDescription;

public class TestItemCollector {
	
	public static TestItem collect(MethodDeclaration md) {
		TestItem ti= new TestItem();
		ti.id = md.getNameAsString();
		if (md.getAnnotationByClass(DisplayName.class).isPresent()) {
			ti.name = getDisplayNameWithoutAnnotation(md.getAnnotationByClass(DisplayName.class).get().toString());
		}
		if(md.getJavadocComment().isPresent()) {
			String comment = md.getJavadoc().get().getDescription().toText();
			ti.description = comment;
		}
		
		
		return ti;
	}
	
	private static String getDisplayNameWithoutAnnotation(String displayName) {
		return displayName.substring(14, displayName.length()-2);
	}
}
