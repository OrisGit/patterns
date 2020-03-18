package com.oris.edu.patterns.strategy;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public interface AverageGradeAnalyzerStrategy {
    void fixAverageGrade(File input, int accuracyOfAverage) throws IOException, SAXException, ParserConfigurationException;
}
