package org.facboy;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ResourceList;
import io.github.classgraph.ScanResult;
import java.io.IOException;

public class DuplicateClasspathBug {

    public static void main(String[] args) throws IOException {
        try (
                ScanResult scanResult = new ClassGraph().whitelistPaths("META-INF/maven/io.github.classgraph/classgraph/").scan();
                ResourceList xmlResources = scanResult.getResourcesWithLeafName("pom.xml")
        ) {
            assert !xmlResources.isEmpty() : "No pom.xml resources found";
            assert xmlResources.findDuplicatePaths().isEmpty() : "Found duplicate path!";
        }
    }
}
