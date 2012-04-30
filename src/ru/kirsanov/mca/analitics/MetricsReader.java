package ru.kirsanov.mca.analitics;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import ru.kirsanov.mca.analitics.metricreader.IMetricReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * User: akirsanov
 * Date: 21.04.12 23:39
 */
public class MetricsReader {

    private String path;
    private Map<String, IMetricReader> mapOfReaders = new HashMap<String, IMetricReader>();

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
            NodeList nl = document.getElementsByTagName("Metric");
            NodeList classNodes = document.getChildNodes();

            for (int i = 0; i < nl.getLength(); i++) {
                Element element = (Element) nl.item(i);
                if (element.getAttribute("id").equals("NORM")) {
                    mapOfReaders.put("NORM", MetricsFactory.createPerTypeMetricReader("NORM", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("NOF")) {
                    mapOfReaders.put("NOF", MetricsFactory.createPerTypeMetricReader("NOF", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("NSC")) {
                    mapOfReaders.put("NSC", MetricsFactory.createPerTypeMetricReader("NSC", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("NOC")) {
                    mapOfReaders.put("NOC", MetricsFactory.createPerPackageMetricReader("NOC", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("MLOC")) {
                    mapOfReaders.put("MLOC", MetricsFactory.createPerMethodMetricReader("MLOC", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("NOM")) {
                    mapOfReaders.put("NOM", MetricsFactory.createPerTypeMetricReader("NOM", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("NBD")) {
                    mapOfReaders.put("NBD", MetricsFactory.createPerMethodMetricReader("NBD", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("CA")) {
                    mapOfReaders.put("CA", MetricsFactory.createPerPackageMetricReader("CA", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("NOI")) {
                    mapOfReaders.put("NOI", MetricsFactory.createPerPackageMetricReader("NOI", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("VG")) {
                    mapOfReaders.put("VG", MetricsFactory.createPerMethodMetricReader("VG", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("RMI")) {
                    mapOfReaders.put("RMI", MetricsFactory.createPerPackageMetricReader("RMI", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("PAR")) {
                    mapOfReaders.put("PAR", MetricsFactory.createPerMethodMetricReader("PAR", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("LCOM")) {
                    mapOfReaders.put("LCOM", MetricsFactory.createPerTypeMetricReader("LCOM", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("CE")) {
                    mapOfReaders.put("CE", MetricsFactory.createPerPackageMetricReader("CE", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("NSM")) {
                    mapOfReaders.put("NSM", MetricsFactory.createPerTypeMetricReader("NSM", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("RMD")) {
                    mapOfReaders.put("RMD", MetricsFactory.createPerPackageMetricReader("RMD", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("RMA")) {
                    mapOfReaders.put("RMA", MetricsFactory.createPerPackageMetricReader("RMA", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("SIX")) {
                    mapOfReaders.put("SIX", MetricsFactory.createPerTypeMetricReader("SIX", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("WMC")) {
                    mapOfReaders.put("WMC", MetricsFactory.createPerTypeMetricReader("WMC", element.getChildNodes()));
                }
                if (element.getAttribute("id").equals("NSF")) {
                    mapOfReaders.put("NSF", MetricsFactory.createPerTypeMetricReader("NSF", element.getChildNodes()));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Map<String, IMetricReader> getMapOfReaders() {
        return mapOfReaders;
    }
}
