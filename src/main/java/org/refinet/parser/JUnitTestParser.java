package org.refinet.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.refinet.api.TestCase;
import org.refinet.api.TestItem;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class JUnitTestParser<JavaSymbolSolver> {

    public static List<TestCase> parse(Path path) {

        List<TestCase> testcase = new ArrayList<>();
    
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path, "*.{java,class,jar}")) {
            for (Path entry : stream) {
                File f = new File(path.toString() + "/" + entry.getFileName());
                testcase.addAll(parse(f));
               
            }  
        } catch (NotDirectoryException e) {
        	File f = new File(path.toString());
       	 testcase.addAll(parse(f));
        } catch(Exception e) {
        	System.out.println("File or directory " + path.toString() + " could not be parsed");
        	System.out.println(e.getMessage());
        }
       
        return testcase;
    }

    private static List<TestCase> parse(File file) {
        String junitFile = "";
        // file in String umwandeln
        try (Stream<String> lines = Files.lines(Paths.get(file.getPath()))) {
            junitFile = lines.collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        // file einlesen
        return parse(junitFile);
    }


    public static List<TestCase> parse(String junitTests) {

        List<TestCase> tests = new ArrayList<>();

        CompilationUnit cu = StaticJavaParser.parse(junitTests);
        
        ArrayList<Hashtable<String, String>> tags = parseTags(cu);
     
        List<TestItem> test = parseTests(cu);

        for (int j = 0; j < test.size(); j++) {
            TestCase t = new TestCase();
            t.init = parseInit(cu);
            t.preparation =  parsePreparation(cu);
            t.wrapup =  parseWrapup(cu);
            t.destroy = parseDestroy(cu);
            t.test.add(test.get(j));
            tests.add(t);
            t.suite = parseClassName(cu).get(0);
            t.suiteDescription = parseClassName(cu).get(1);
            t.tag = tags.get(j);
        }
        

        return tests;

    }
    
    private static List<TestItem> parseTests(CompilationUnit cu) {
        List<TestItem> test = new ArrayList<>();
        new TestTestCollector().visit(cu, test);
        return test;

	}

	private static List<TestItem> parseDestroy(CompilationUnit cu) {
        List<TestItem> destroy = new ArrayList<>();
        new TestDestroyCollector().visit(cu, destroy);
        return destroy;
	}

	private static List<TestItem> parseWrapup(CompilationUnit cu) {
    	  List<TestItem> wrapup = new ArrayList<>();
          new TestWrapupCollector().visit(cu, wrapup);
          return wrapup;
	}

	private static List<TestItem> parsePreparation(CompilationUnit cu) {
        List<TestItem> preparation = new ArrayList<>();
        new TestPreparationCollector().visit(cu, preparation);
        return preparation;
	}

	private static List<TestItem> parseInit(CompilationUnit cu) {
    	List<TestItem> init = new ArrayList<>();
        new TestInitCollector().visit(cu, init);
        return init;
	}

	private static ArrayList<Hashtable<String, String>> parseTags(CompilationUnit cu) {
        ArrayList<Hashtable<String, String>> tags = new ArrayList<>();
        new TagNameCollector().visit(cu, tags);
		return tags;
	}

	private static ArrayList<String> parseClassName(CompilationUnit cu) {  
        ArrayList<String> className = new ArrayList<>();
    	new ClassNameCollector().visit(cu, className);
		return className;
	}


	


}


