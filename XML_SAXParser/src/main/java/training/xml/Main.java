package training.xml;

import training.xml.api.JDOMWriter;
import training.xml.api.SAXReader;

import java.io.IOException;
import java.util.List;

/**
 * Creates 2 files in root folder as a result of successful data processing:
 * - persons.xml that is XML file to be parsed;
 * - parsed_persons.txt that is a result of analysing parsed XML file and applying condition to have at least 10000
 * cash value for Person to be added into this file;
 */
public class Main {

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        controller.setXmlWriterAPI(new JDOMWriter());
        controller.setXmlReaderAPI(new SAXReader());
        controller.setWriter(new Writer());
        List<Person> persons = controller.createListOfPersons();
        controller.createXMLFile(persons);
        controller.readDataFromXML();
        controller.writeData();
    }
}
