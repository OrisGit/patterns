package com.oris.edu.patterns.strategy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Main {
    private static AverageGradeAnalyzerStrategy averageGradeAnalyzerStrategy;

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        File file = new File(args[1]);
        if(args[0].equalsIgnoreCase("DOM")){
            System.out.println("DOM");
            averageGradeAnalyzerStrategy = new DOMAverageGradeAnalyzer();
        }else{
            System.out.println("SAX");
            averageGradeAnalyzerStrategy = new SAXAverageGradeAnalyzer();
        }

        averageGradeAnalyzerStrategy.fixAverageGrade(file, 2);
    }
}
