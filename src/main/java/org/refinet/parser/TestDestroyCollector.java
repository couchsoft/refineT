package org.refinet.parser;

import java.util.List;

import org.junit.jupiter.api.AfterAll;

import org.refinet.api.TestItem;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TestDestroyCollector extends VoidVisitorAdapter<List<TestItem>> {

    public void visit(MethodDeclaration md, List<TestItem> collector) {
        super.visit(md, collector);
        if (md.getAnnotationByClass(AfterAll.class).isPresent()) {
            TestItem ti = TestItemCollector.collect(md);
            collector.add(ti);
        }

    }
}