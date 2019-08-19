package org.refinet.api;


public class TestStep {

  public String collection; // class name where step is defined
  public String name; // method name
  public String description; // comment
  
  
public TestStep(String collection, String name, String description) {
	this.collection = collection;
	this.name = name;
	this.description = description;
}

@Override
public String toString() {
	return "TestStep [collection=" + collection + ", name=" + name + ", description=" + description + "]";
}

  
  
  
}