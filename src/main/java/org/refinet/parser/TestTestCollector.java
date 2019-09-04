package org.refinet.parser;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.refinet.api.TestItem;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TestTestCollector extends VoidVisitorAdapter<List<TestItem>> {

    TestItem tc = new TestItem();

    public void visit(MethodDeclaration md, List<TestItem> collector) {
        super.visit(md, collector);
        if (md.getAnnotationByClass(Test.class).isPresent()) {
            TestItem ti = TestItemCollector.collect(md);
            collector.add(ti);
        }
    }

}
