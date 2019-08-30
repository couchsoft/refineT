package org.refinet.parser;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.refinet.api.TestItem;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TagNameCollector extends VoidVisitorAdapter <ArrayList<Hashtable<String, String>>> {

	
	public void visit(MethodDeclaration md, ArrayList<Hashtable<String, String>> collector) {
		super.visit(md, collector);
		ArrayList<Hashtable<String, String>> fertigeTags = new ArrayList<>();
		Hashtable<String, String> tags = new Hashtable<>();
		ArrayList<Integer> tagNumber = new ArrayList<>();

		
		if (md.getAnnotationByClass(Test.class).isPresent()) {
			for (int i = 0; i < md.getAnnotations().size(); i++) {
				if (md.getAnnotations().get(i).getNameAsString().equals("Tag")) {
					tagNumber.add(i);
				}
			}
			for (int i = 0; i < tagNumber.size(); i++) {
				String tag = getTagNameWithoutAnnotation(md.getAnnotations().get(tagNumber.get(i)).toString());
				String[] tagsplit = tag.split(":");
				if (tagsplit.length > 1) {
					tags.put(tagsplit[0],tagsplit[1]);
				}else {
				tags.put(tagsplit[0], "");
				}
			}
			fertigeTags.add(tags);
			tagNumber.clear();
		}


		collector.addAll(fertigeTags);
}

	private String getTagNameWithoutAnnotation(String tagName) {
		return tagName.substring(6, tagName.length()-2);
	}
	
}
