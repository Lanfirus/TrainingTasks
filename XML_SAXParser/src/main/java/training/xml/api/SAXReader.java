package training.xml.api;

import org.xml.sax.SAXException;
import training.xml.Constants;
import training.xml.NullInputDataException;
import training.xml.Person;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class SAXReader implements XMLReaderAPI {

    private SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    private SAXHandler handler;

    public List<Person> readDataFromXML(String fileName) {
        if (Objects.nonNull(fileName)) {
            try {
                SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                Schema schema = schemaFactory.newSchema(new File(Constants.CATALOG_SCHEMA));
                saxParserFactory.setSchema(schema);
                saxParserFactory.setValidating(false);
                SAXParser saxParser = saxParserFactory.newSAXParser();
                setHandler(new SAXHandler());
                saxParser.parse(new File(fileName), handler);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
            return handler.getPersons();
        }
        else {
            throw new NullInputDataException();
        }
    }

    public void setHandler(SAXHandler handler) {
        this.handler = handler;
    }
}
