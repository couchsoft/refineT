package org.refinet.parser;

import org.junit.jupiter.api.DisplayName;
import org.refinet.api.TestItem;

import com.github.javaparser.ast.body.MethodDeclaration;

public class TestItemCollector {

	public static TestItem collect(MethodDeclaration md) {
		TestItem ti = new TestItem();
		ti.id = md.getNameAsString();
		if (md.getAnnotationByClass(DisplayName.class).isPresent()) {
			ti.name = getDisplayNameWithoutAnnotation(md);
		}
		if (md.getJavadocComment().isPresent()) {
			String comment = md.getJavadoc().get().getDescription().toText();
			ti.description = comment;
		}
		return ti;
	}

	private static String getDisplayNameWithoutAnnotation(MethodDeclaration md) {
		String displayName = md.getAnnotationByClass(DisplayName.class).get().toString();
		return displayName.substring(14, displayName.length() - 2);
	}
}
