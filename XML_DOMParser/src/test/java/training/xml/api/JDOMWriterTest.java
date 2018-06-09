package training.xml.api;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;
import org.jdom2.input.sax.XMLReaderXSDFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import training.xml.Constants;
import training.xml.NullInputDataException;
import training.xml.Person;
import training.xml.TestConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JDOMWriterTest {

    private JDOMWriter jdomWriter = new JDOMWriter();
    private static List<Person> persons;

    @BeforeAll
    public static void createListOfPersons(){
        persons = new ArrayList<>();
        persons.add(new Person(1,"John", "New York, 5th ave, 45", 100500, "it"));
        persons.add(new Person(2,"Mike", "New Jersey, Clive str, 1405", 94924, "telecom"));
        persons.add(new Person(3,"Rob", "Minneapolis, WWII Heroes str,  25, app.1264", 5300,
                "agriculture"));
    }

    @AfterEach
    public void deleteFile() throws IOException{
        Files.deleteIfExists(Paths.get(TestConstants.TEST_XML_FILE));
    }

    @Test
    public void createXMLDocumentCorrect(){
        Document document = jdomWriter.createXMLDocument(persons);

        assertNotNull(document);
    }

    @Test
    public void createXMLDocumentIncorrectNullInputData(){
        assertThrows(NullInputDataException.class, () -> jdomWriter.createXMLDocument(null));
    }

    @Test
    public void writeXMLToFileCorrectFileCreated() throws IOException{
        jdomWriter.writeXMLToFile(persons, TestConstants.TEST_XML_FILE);

        assertTrue(Files.exists(Paths.get(TestConstants.TEST_XML_FILE)));
    }

    @Test
    public void writeXMLToFileCorrectFileValidated() throws IOException, JDOMException{
        jdomWriter.writeXMLToFile(persons, TestConstants.TEST_XML_FILE);

        XMLReaderJDOMFactory factory = new XMLReaderXSDFactory(Constants.CATALOG_SCHEMA);
        SAXBuilder builder = new SAXBuilder(factory);
        builder.build(TestConstants.TEST_XML_FILE);
    }

}
