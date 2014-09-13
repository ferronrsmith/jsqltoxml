package com.uwi.comparator;

import com.thoughtworks.qdox.model.JavaMethod;

import java.util.Comparator;

/**
 * @internal
 */
public class NameComparator implements Comparator<JavaMethod> {

    public int compare(JavaMethod pO1, JavaMethod pO2) {
        int nameCompare = pO1.getName().compareTo(pO2.getName());
        if (nameCompare == 0) {
            return pO1.getParameters().toString().compareTo(pO2.getParameters().toString());
        }
        return nameCompare;
    }
}
