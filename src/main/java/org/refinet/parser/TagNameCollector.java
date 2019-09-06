package org.refinet.parser;

import java.util.ArrayList;
import java.util.Hashtable;
import org.junit.jupiter.api.Test;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

public class TagNameCollector extends VoidVisitorAdapter<ArrayList<Hashtable<String, String>>> {


    public void visit(MethodDeclaration md, ArrayList<Hashtable<String, String>> collector) {
        super.visit(md, collector);

        ArrayList<Hashtable<String, String>> fertigeTags = new ArrayList<>();
        Hashtable<String, String> tags = new Hashtable<>();
        ArrayList<Integer> tagNumbers = new ArrayList<>();

        if (md.getAnnotationByClass(Test.class).isPresent()) {
            for (int i = 0; i < md.getAnnotations().size(); i++) {
                if (md.getAnnotations().get(i).getNameAsString().equals("Tag")) {
                    tagNumbers.add(i);
                }
            }
            
            for (int i = 0; i < tagNumbers.size(); i++) {
                String tag = getTagNameWithoutAnnotation(md.getAnnotations().get(tagNumbers.get(i)).toString());
                System.out.println(tag);
                tags.putAll(splittedTags(tag));
            }
            
            fertigeTags.add(tags);
            System.out.println(md.getDeclarationAsString());
            System.out.println(fertigeTags);
            tagNumbers.clear();
        }
        
        collector.addAll(fertigeTags);
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

	private String getTagNameWithoutAnnotation(String tagName) {
        return tagName.substring(6, tagName.length() - 2);
    }

}
