package com.oris.edu.patterns.strategy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DOMAverageGradeAnalyzer implements AverageGradeAnalyzerStrategy {
    @Override
    public void fixAverageGrade(File input, int accuracyOfAverage) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(input);
        Element student = document.getDocumentElement();
        NodeList subjects = student.getElementsByTagName("subject");
        double calculatedAverage = evaluateAverage(subjects);
        Node average = student.getElementsByTagName("average").item(0);
        double realAverage = Double.parseDouble(average.getTextContent());
        if(realAverage!=calculatedAverage){
            average.setTextContent(String.valueOf(calculatedAverage));
            writeDocument(document, input);
        }
    }

    private double evaluateAverage(NodeList subjects){
        double average = 0;
        for (int i = 0; i < subjects.getLength(); i++) {
            Node subject = subjects.item(i);
            Node mark = subject.getAttributes().getNamedItem("mark");
            average += Integer.parseInt(mark.getNodeValue());
        }
        return average/subjects.getLength();
    }

    private static void writeDocument(Document document, File output) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(output);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
}
