package training.xml.api;

import training.xml.Person;

import java.io.FileNotFoundException;
import java.util.List;

public interface XMLReaderAPI {

    List<Person> readDataFromXML(String filename) throws FileNotFoundException;
}
