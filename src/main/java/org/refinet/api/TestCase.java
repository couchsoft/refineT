package org.refinet.api;

import java.util.ArrayList;
import java.util.List;

public class TestCase {

	public List<TestItem> init  = new ArrayList<>(); // method that is annotated with @BeforeAll
	public List<TestItem>  test = new ArrayList<>();; // method @test
	public List<TestItem> preparation = new ArrayList<>();; // method that is annotated with @BeforeEach
	public List<TestItem>  wrapup = new ArrayList<>();; // method that is annotated with @AfterEach
	public List<TestItem>  destroy = new ArrayList<>();; // method that is annotated with @AfterAll
	
	
	@Override
	public String toString() {
		return "\nTestCase [init=" + init + ", \n test=" + test + ",  \n preparation=" + preparation + ", \n wrapup=" + wrapup
				+ ", \n destroy=" + destroy + "]\n";
	}
	
	
	
	

}


