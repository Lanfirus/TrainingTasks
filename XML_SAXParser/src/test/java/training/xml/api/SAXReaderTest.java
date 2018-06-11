package training.xml.api;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import training.xml.Person;
import training.xml.TestConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SAXReaderTest {

    private SAXReader saxReader = new SAXReader();
    private String fileName = TestConstants.TEST_XML_FILE;
    private static List<Person> persons;

    @BeforeAll
    public static void createListOfPersons() throws IOException{
        persons = new ArrayList<>();
        persons.add(new Person(1,"John", "New York, 5th ave, 45", 100500, "it"));
        persons.add(new Person(2,"Mike", "New Jersey, Clive str, 1405", 94924, "telecom"));
        persons.add(new Person(3,"Rob", "Minneapolis, WWII Heroes str,  25, app.1264", 5300,
                "agriculture"));
       new JDOMWriter().writeXMLToFile(persons, TestConstants.TEST_XML_FILE);
    }

    @AfterAll
    public static void deleteFile() throws IOException{
        Files.deleteIfExists(Paths.get(TestConstants.TEST_XML_FILE));
    }

    @Test
    public void readDataFromXMLCorrect() throws IOException{
        List<Person> readPersons = saxReader.readDataFromXML(fileName);

        assertTrue(persons.equals(readPersons));
        Files.deleteIfExists(Paths.get(TestConstants.TEST_XML_FILE));
    }

    @Test
    public void readDataFromXMLIncorrectNullInputData() throws IOException{
        persons.add(new Person(4,null, "New York, 5th ave, 45", 100500, "it"));
        JDOMWriter writer = new JDOMWriter();
        writer.writeXMLToFile(persons, TestConstants.TEST_XML_FILE);

        assertThrows(RuntimeException.class, () -> saxReader.readDataFromXML(fileName));
        persons.remove(3);
        writer.writeXMLToFile(persons, TestConstants.TEST_XML_FILE);
    }
}
