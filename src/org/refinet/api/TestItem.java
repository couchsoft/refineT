package org.refinet.api;

import java.util.ArrayList;
import java.util.List;

public class TestItem {

	  public String suite; // file name where test is defined
	  public String id; // method name
	  public String name; // @DisplayName value
	  public String description; // comment

	  public List<TestStep> steps = new ArrayList<>();

	@Override
	public String toString() {
		return "TestItem [suite=" + suite + ", id=" + id + ", name=" + name + ", description=" + description
				+ ", steps=" + steps + "]";
	}

	}


