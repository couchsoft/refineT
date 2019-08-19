package org.refinet.parser;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.refinet.api.TestItem;
import org.refinet.api.TestStep;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.JavadocComment;
import com.github.javaparser.ast.Node.ParentsVisitor;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.javadoc.description.JavadocDescription;

public class TestItemCollector {
	
	public TestItem TestItemCollector(String file, MethodDeclaration md) {
	TestItem ti= new TestItem();
	ti.id = md.getNameAsString();
	ti.suite = file;
	if (md.getAnnotationByClass(DisplayName.class).isPresent()) {
		ti.name = md.getAnnotationByClass(DisplayName.class).get().toString();
		ti.name = ti.name.substring(14, ti.name.length()-2);
	}
	if(md.getJavadocComment().isPresent()) {
		String comment = md.getJavadoc().get().getDescription().toText();
		ti.description = comment;
		//comment = comment.replaceAll("\\*", "");
		/*comment = comment.replaceAll("  ", " ");*/
		/*String[] csplit = comment.split("\\.");
		ti.description = "";
		for (int i = 0; i < csplit.length; i++) {
			ti.description = ti.description + csplit[i].trim() + ".";
		}*/
	}
	if (md.getBody().isPresent()) {
        NodeList<Statement> statements = md.getBody().get().getStatements();
        statements.forEach(s -> {
              List<Node> nodes = s.getChildNodes();
              ti.steps.add(new TestStep("", ti.id, s.toString()));
              nodes.forEach(n -> {
                  // System.out.println(ti.steps);
                  //  System.out.println(n);
            	 // ParentsVisitor pv =  new ParentsVisitor(n);
                  //System.out.println(pv.next().getParentNodeForChildren());
            	  
              });
        });
	}
	return ti;
}
}
