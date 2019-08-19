package org.refinet;

import java.io.File;
import org.refinet.parser.JUnitTestParser;

public class testmain {

	public static void main(String[] args) {
	JUnitTestParser.parse(new File("src/org/refinet/tests/CalculatorTests.java"));
	
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
