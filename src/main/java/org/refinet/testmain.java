package org.refinet;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.junit.jupiter.api.Test;
import org.refinet.api.TestCase;
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
	
	static String FILE_TO_PARSE = "src/main/resources/tests";
	public static void main(String[] args) {
		
		Path path = Paths.get(FILE_TO_PARSE);
		List<TestCase> tests = JUnitTestParser.parse(path);
		System.out.println(tests);

		
		

	}
    
  


}
