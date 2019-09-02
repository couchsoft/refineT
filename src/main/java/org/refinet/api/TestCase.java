package org.refinet.api;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class TestCase {

	public String suite;
	public String suiteDescription;
	public List<TestItem> init  = new ArrayList<>(); // method that is annotated with @BeforeAll
	public List<TestItem>  test = new ArrayList<>();; // method @test
	public List<TestItem> preparation = new ArrayList<>();; // method that is annotated with @BeforeEach
	public List<TestItem>  wrapup = new ArrayList<>();; // method that is annotated with @AfterEach
	public List<TestItem>  destroy = new ArrayList<>();; // method that is annotated with @AfterAll
	public Hashtable<String, String> tag = new Hashtable<>();
	
	@Override
	public String toString() {
		return "\nTestCase [ Suite=" + suite + ", SuiteDescription= " + suiteDescription + " \n ,init=" + init + ", \n test=" + test + ",  \n preparation=" + preparation + ", \n wrapup=" + wrapup
				+ ", \n destroy=" + destroy + ", \n Tag=" + tag + "]\n";
	}
	
	
	
	

}


