package org.refinet.parser;

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.jupiter.api.Test;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TagNameCollector extends VoidVisitorAdapter<ArrayList<Hashtable<String, String>>> {

	public void visit(MethodDeclaration md, ArrayList<Hashtable<String, String>> collector) {
		super.visit(md, collector);
		ArrayList<Hashtable<String, String>> sortedTags = new ArrayList<>();
		if (md.getAnnotationByClass(Test.class).isPresent()) {
			sortedTags = new ArrayList<>();
			ArrayList<Integer> tagNumbers = searchTags(md);
			Hashtable<String, String> tags = sortTags(tagNumbers, md);
			sortedTags.add(tags);
		}
		collector.addAll(sortedTags);
	}

	private Hashtable<String, String> sortTags(ArrayList<Integer> tagNumbers, MethodDeclaration md) {
		Hashtable<String, String> tags = new Hashtable<>();
		for (int i = 0; i < tagNumbers.size(); i++) {
			String tag = getTagNameWithoutAnnotation(md, tagNumbers.get(i));
			tags.putAll(splittedTags(tag));
		}
		return tags;
	}

	private String getTagNameWithoutAnnotation(MethodDeclaration md, int tagNumber) {
		String tagName = md.getAnnotations().get(tagNumber).toString();
		return tagName.substring(6, tagName.length() - 2);
	}

	private ArrayList<Integer> searchTags(MethodDeclaration md) {
		ArrayList<Integer> tagNumbers = new ArrayList<>();
		if (md.getAnnotationByClass(Test.class).isPresent()) {
			for (int i = 0; i < md.getAnnotations().size(); i++) {
				if (md.getAnnotations().get(i).getNameAsString().equals("Tag")) {
					tagNumbers.add(i);
				}
			}
		}
		return tagNumbers;
	}

	private Hashtable<String, String> splittedTags(String tag) {
		Hashtable<String, String> tags = new Hashtable<>();
		String[] tagsplit = tag.split(":");
		if (tagsplit.length > 1) {
			tags.put(tagsplit[0], tagsplit[1]);
		} else {
			tags.put(tagsplit[0], "");
		}
		return tags;
	}
}
