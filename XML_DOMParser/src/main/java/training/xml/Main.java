package training.xml;

import training.xml.api.DOMReader;
import training.xml.api.JDOMWriter;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        Controller controller = new Controller();
        controller.setXmlWriterAPI(new JDOMWriter());
        controller.setXmlReaderAPI(new DOMReader());
        controller.setWriter(new Writer());
        List<Person> persons = controller.createListOfPersons();
        controller.createXMLFile(persons);
        controller.readDataFromXML();
        controller.writeData();
    }
}
