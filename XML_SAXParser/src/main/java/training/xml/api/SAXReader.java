package training.xml.api;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import training.xml.Constants;
import training.xml.ExceptionMessages;
import training.xml.Person;
import training.xml.SimpleErrorHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SAXReader implements XMLReaderAPI {

    private SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    private SAXHandler handler;

    public List<Person> readDataFromXML(String fileName) {
        if (Objects.nonNull(fileName)) {
            try {
                saxParserFactory.setValidating(true);
                setHandler(new SAXHandler());
                SAXParser saxParser = saxParserFactory.newSAXParser();
                saxParser.setProperty(Constants.SCHEMA_LANGUAGE, Constants.SCHEMA);
                saxParser.setProperty(Constants.SCHEMA_SOURCE, Constants.CATALOG_SCHEMA);
                XMLReader xmlReader = saxParser.getXMLReader();
                xmlReader.setContentHandler(handler);
                xmlReader.setErrorHandler(new SimpleErrorHandler());
                xmlReader.parse(Constants.FILE + fileName);
            }
            catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
            catch (NumberFormatException e) {
                throw new RuntimeException(ExceptionMessages.NULL_PARAMETER);
            }
            return handler.getPersons();
        }
        else {
            throw new RuntimeException(ExceptionMessages.NULL_FILENAME);
        }
    }

    private void setHandler(SAXHandler handler) {
        this.handler = handler;
    }
}
