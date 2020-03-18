package com.oris.edu.patterns.strategy;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLFilterImpl;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class SAXAverageGradeAnalyzer implements AverageGradeAnalyzerStrategy {

    @Override
    public void fixAverageGrade(File input, int accuracyOfAverage) throws ParserConfigurationException, SAXException, IOException {

        XMLReader xr = new XMLFilterImpl(XMLReaderFactory.createXMLReader()) {
            boolean student = false;
            boolean average = false;
            double evaluatedAverage = 0;
            double realAverage = 0;
            int counter = 0;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                if (qName.equalsIgnoreCase("student")) {
                    student = true;
                } else if (student && qName.equalsIgnoreCase("subject")) {
                    int mark = Integer.parseInt(attributes.getValue("mark"));
                    evaluatedAverage += mark;
                    counter++;
                } else if (student && qName.equalsIgnoreCase("average")) {
                    average = true;
                }
                super.startElement(uri, localName, qName, attributes);
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                if (average) {
                    evaluatedAverage = evaluatedAverage / counter;
                    if (evaluatedAverage != realAverage) {
                        ch = String.valueOf(evaluatedAverage).toCharArray();
                        start = 0;
                        length = ch.length;
                    }
                    average = false;
                }
                super.characters(ch, start, length);
            }
        };

        Source src = new SAXSource(xr, new InputSource(new FileInputStream(input)));
        StringWriter stringWriter = new StringWriter();
        Result res = new StreamResult(stringWriter);
        try {
            TransformerFactory.newInstance().newTransformer().transform(src, res);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        FileWriter fw = new FileWriter(input);
        fw.write(stringWriter.toString());
        fw.close();
    }
}
