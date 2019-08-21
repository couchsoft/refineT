package org.refinet;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.junit.jupiter.api.Test;
import org.refinet.parser.JUnitTestParser;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.github.javaparser.ast.comments.JavadocComment;
import com.google.j2objc.annotations.ReflectionSupport;


public class testmain {

	public static void main(String[] args) {
	

    Reflections reflections = new Reflections("org.refinet.tests", new SubTypesScanner(false));
    
    Set<Class<? extends Object>> classes = 
        reflections.getSubTypesOf(Object.class);
    
  
    Object[] array = classes.toArray();
    
    
    for (int i = 0; i < array.length; i++) {
    	array[i] = "src/main/java/" + array[i].toString().substring(6, array[i].toString().length()).replace(".", "/") + ".java";
    	
	}
    
    for (int j = 0; j < array.length; j++) {
    	if (array[j].toString().substring(array[j].toString().length()-10, array[j].toString().length()).equals("Tests.java")) {
    	JUnitTestParser.parse(new File(array[j].toString()));
    	System.out.println(array[j]);
    	}else {
    		System.out.println(array[j]);
    	}
	}
    

	// JFileChooser-Objekt erstellen	
    //JFileChooser chooser = new JFileChooser();
    //int file = chooser.showOpenDialog(null);
    //if(file == JFileChooser.APPROVE_OPTION){
    //String a = chooser.getSelectedFile().getAbsolutePath();
    //System.out.println(a);
	//JUnitTestParser.parse(new File(a));
    //}
	}
    
  


}
