package org.refinet.parser;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.refinet.api.TestItem;

import com.github.javaparser.ast.body.MethodDeclaration;

public class TestItemCollector {

    public static TestItem collect(MethodDeclaration md) {
        TestItem ti = new TestItem();
        ti.id = md.getNameAsString();
        if (md.getAnnotationByClass(DisplayName.class).isPresent()) {
            ti.name = getDisplayNameWithoutAnnotation(md.getAnnotationByClass(DisplayName.class).get().toString());
        }
        if (md.getJavadocComment().isPresent()) {
            String comment = md.getJavadoc().get().getDescription().toText();
            ti.description = comment;
        }
        return ti;
    }

    private static String getDisplayNameWithoutAnnotation(String displayName) {
        return displayName.substring(14, displayName.length() - 2);
    }
}
