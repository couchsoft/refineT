package org.refinet.api;

public class TestItem {

	public String id; // method name
	public String name; // @DisplayName value
	public String description; // comment

	@Override
	public String toString() {
		return "TestItem [id=" + id + ", name=" + name + // ", description=" + description +
				"]";
	}
}
