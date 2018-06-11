package training.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;

public class SimpleErrorHandler implements ErrorHandler {
    public void warning(SAXParseException e) throws SAXParseException{
        System.out.println(e.getMessage());
    }

    public void error(SAXParseException e) throws SAXParseException{
        System.out.println(e.getMessage());
        throw new RuntimeException(e.getMessage());
    }

    public void fatalError(SAXParseException e) throws SAXParseException{
        System.out.println(e.getMessage());
        throw new RuntimeException(e.getMessage());
    }
}
