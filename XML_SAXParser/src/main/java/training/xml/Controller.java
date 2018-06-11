package training.xml;

import training.xml.api.JDOMWriter;
import training.xml.api.XMLReaderAPI;
import training.xml.api.XMLWriterAPI;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates several Persons, writes them into XML file using JDOM then reads them back using DOM.
 * Writes read data to Console and File.
 */
public class Controller {

    private Writer writer;
    private XMLReaderAPI xmlReaderAPI;
    private XMLWriterAPI xmlWriterAPI;

    public void createXMLFile(List<Person> persons) throws IOException{
        xmlWriterAPI.writeXMLToFile(persons, Constants.XML_FILENAME);
    }

    /**
     * Reads data from XML file saving it to List<Person> collection
     * @return      List of Persons
     */
    public List<Person> readDataFromXML() throws FileNotFoundException{
        return xmlReaderAPI.readDataFromXML(Constants.XML_FILENAME);
    }

    /**
     * Writes Data to Console and File
     * @throws IOException
     */
    public void writeData() throws IOException{
        writer.setFileWriter(new FileWriter(Constants.PARSED_XML_FILENAME));
        writer.writeDataToConsoleAndFile(readDataFromXML());
    }

    public List<Person> createListOfPersons(){
        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1,"John", "New York, 5th ave, 45", 100500, "it"));
        persons.add(new Person(2,"Mike", "New Jersey, Clive str, 1405", 94924, "telecom"));
        persons.add(new Person(3,"Rob", "Minneapolis, WWII Heroes str,  25, app.1264", 5300,
                "agriculture"));
        return persons;
    }

    public Writer getWriter() {
        return writer;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public XMLReaderAPI getXmlReaderAPI() {
        return xmlReaderAPI;
    }

    public void setXmlReaderAPI(XMLReaderAPI xmlReaderAPI) {
        this.xmlReaderAPI = xmlReaderAPI;
    }

    public XMLWriterAPI getXmlWriterAPI() {
        return xmlWriterAPI;
    }

    public void setXmlWriterAPI(JDOMWriter xmlWriterAPI) {
        this.xmlWriterAPI = xmlWriterAPI;
    }
}
