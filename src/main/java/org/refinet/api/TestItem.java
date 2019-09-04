package org.refinet.api;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class TestItem {

    public String id; // method name
    public String name; // @DisplayName value
    public String description; // comment

    @Override
    public String toString() {
        return "TestItem [id=" + id + ", name=" + name + //", description=" + description +
                "]";
    }

}


