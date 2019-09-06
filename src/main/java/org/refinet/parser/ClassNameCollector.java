package org.refinet.parser;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class ClassNameCollector extends VoidVisitorAdapter<ArrayList<String>> {

    public void visit(ClassOrInterfaceDeclaration c, ArrayList<String> collector) {
        super.visit(c, collector);
        ArrayList<String> classItem = new ArrayList<>();
        String className = c.getNameAsString();
        classItem.add(className);
        String displayName = null;
        if (c.getAnnotationByClass(DisplayName.class).isPresent()) {
            displayName = getDisplayNameWithoutAnnotation(c.getAnnotationByClass(DisplayName.class).get().toString());
            classItem.add(displayName);
        }
        collector.addAll(classItem);

    }

    private static String getDisplayNameWithoutAnnotation(String displayName) {
        return displayName.substring(14, displayName.length() - 2);
    }

}
