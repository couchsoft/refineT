package org.refinet;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.refinet.api.TestCase;
import org.refinet.parser.JUnitTestParser;

public class CliTestParser {

    static String FILE_TO_PARSE = "src/main/resources/tests";

    public static void main(String[] args) {
        Path path;
        if (args.length == 0) {
            path = Paths.get(FILE_TO_PARSE);
        } else {
            path = Paths.get(args[0]);
        }

        List<TestCase> tests = JUnitTestParser.parse(path);
        System.out.println(tests);

    }
}
