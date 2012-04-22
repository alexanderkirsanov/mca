package ru.kirsanov.mca.analitics;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * User: akirsanov
 * Date: 21.04.12 23:39
 */
public class MetricsReader {

    private String path;


    public MetricsReader(String path) {
        this.path = path;
    }

    private Document getDocument() throws Exception {
        try {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            f.setValidating(false);
            DocumentBuilder builder = f.newDocumentBuilder();
            Document doc = builder.parse(new File(path));
            doc.getDocumentElement().normalize();
            return doc;
        } catch (Exception exception) {
            String message = "XML parsing error!";
            throw new Exception(message);
        }
    }


    public void loadMetrics() {
        Document document;
        try {
            document = getDocument();
            NodeList nl =  document.getElementsByTagName("Metric");
            NodeList classNodes = document.getChildNodes();

            for(int i = 0; i < nl.getLength(); i++) {
                Element element = (Element)nl.item(i);
                if (element.getAttribute("id").equals("NORM")){
                    MetricsFactory.createPerTypeMetricReader("NORM",element.getChildNodes()).readMetric();
                }
                //System.out.println(element.getAttribute("id"));
            }

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
