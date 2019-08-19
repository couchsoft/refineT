package org.refinet.api;

import java.util.ArrayList;

public class TestCase {

	public ArrayList<TestItem> init  = new ArrayList<>(); // method that is annotated with @BeforeAll
	public ArrayList<TestItem>  test = new ArrayList<>();; // method @test
	public ArrayList<TestItem> preparation = new ArrayList<>();; // method that is annotated with @BeforeEach
	public ArrayList<TestItem>  wrapup = new ArrayList<>();; // method that is annotated with @AfterEach
	public ArrayList<TestItem>  destroy = new ArrayList<>();; // method that is annotated with @AfterAll
	
	
	@Override
	public String toString() {
		return "\nTestCase [init=" + init + ", \n test=" + test + ",  \n preparation=" + preparation + ", \n wrapup=" + wrapup
				+ ", \n destroy=" + destroy + "]\n";
	}
	
	
	
	

}


