package training.xml.api;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import training.xml.Constants;
import training.xml.NullInputDataException;
import training.xml.Person;
import training.xml.SimpleErrorHandler;

public class DOMReader implements XMLReaderAPI {

    public List<Person> readDataFromXML(String fileName){
        if (Objects.nonNull(fileName)) {
            List<Person> persons = new ArrayList<>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            SchemaFactory schemaFactory = SchemaFactory.newInstance(Constants.SCHEMA);
            try (FileInputStream xmlFile = new FileInputStream(fileName)){
                factory.setSchema(schemaFactory.newSchema(new Source[]{new StreamSource(Constants.CATALOG_SCHEMA)}));
                DocumentBuilder builder = factory.newDocumentBuilder();
                builder.setErrorHandler(new SimpleErrorHandler());
                Document document = builder.parse(xmlFile);
                document.getDocumentElement().normalize();
                NodeList nodeList = document.getElementsByTagName(Constants.PERSON);

                for (int i = 0; i < nodeList.getLength(); i++) {
                    persons.add(getPerson(nodeList.item(i)));
                }
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }

            return persons;
        }
        else {
            throw new NullInputDataException();
        }
    }

    private Person getPerson(Node node) {
        Person person = new Person();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            person.setName(getTagValue(Constants.NAME, element));
            person.setAddress(getTagValue(Constants.ADDRESS, element));
            person.setCash(Integer.parseInt(getTagValue(Constants.CASH, element)));
        }
        return person;
    }

    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        if (Objects.nonNull(node)) {
            return node.getNodeValue();
        }
        else {
            throw new NullInputDataException();
        }
    }
}
