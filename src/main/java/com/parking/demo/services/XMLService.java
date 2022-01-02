package com.parking.demo.services;

import com.parking.demo.models.Location;
import com.parking.demo.models.Parking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class XMLService {
    private final Logger logger = LoggerFactory.getLogger(XMLService.class);

    public Parking parseParking() {
        Parking parking = null;
        List listeParkings = new ArrayList();
        try {
            String XML_LINK = "http://data.lacub.fr/wfs?key=9Y2RU3FTE8&SERVICE=WFS&VERSION=1.1.0&REQUEST=GetFeature&TYPENAME=ST_PARK_P&SRSNAME=EPSG:4326";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(XML_LINK);

            // normalize XML response
            doc.getDocumentElement().normalize();

            //read parking details first
            NodeList nodeList = doc.getElementsByTagName("gml:featureMember");

            //create an empty list for parkings
            List<Location> locations = new ArrayList<>();


            //loop all available parking nodes
            for (int i = 0; i < nodeList.getLength(); i++) {
                parking = new Parking();

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    int nbPlacesLibre = elem.getElementsByTagName("bm:LIBRES").item(0).getTextContent().length();
                    Location location = new Location(
                            Integer.parseInt(elem.getElementsByTagName("bm:GID").item(0).getTextContent()),
                            elem.getElementsByTagName("bm:NOM").item(0).getTextContent(),
                            elem.getElementsByTagName("bm:LIBRES").item(0).getTextContent(),
                            elem.getElementsByTagName("bm:TOTAL").item(0).getTextContent(),
                            elem.getElementsByTagName("gml:pos").item(0).getTextContent(),
                            elem.getElementsByTagName("bm:ADRESSE").item(0).getTextContent(),
                            elem.getElementsByTagName("bm:TA_TYPE").item(0).getTextContent(),
                            elem.getElementsByTagName("bm:ETAT").item(0).getTextContent()
                    );
                    if(nbPlacesLibre >0) {
                        locations.add(location);
                    }
                    listeParkings.add(location);

                }

            }

        parking.setLocations(locations);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        System.out.println(parking);
        return parking;
    }
}


