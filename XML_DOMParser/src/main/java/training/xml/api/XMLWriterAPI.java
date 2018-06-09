package training.xml.api;

import training.xml.Person;

import java.io.IOException;
import java.util.List;

public interface XMLWriterAPI {

    void writeXMLToFile(List<Person> persons, String fileName) throws IOException;
}
