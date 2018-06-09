package training.xml;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WriterTest {

    private Writer writer = new Writer();
    private static List<Person> persons;

    @BeforeAll
    public static void init() throws IOException{
        Files.deleteIfExists(Paths.get(TestConstants.TEST_TEXT_FILE));
        persons = new ArrayList<>();
        persons.add(new Person(1,"John", "New York, 5th ave, 45", 100500, "it"));
        persons.add(new Person(2,"Mike", "New Jersey, Clive str, 1405", 94924, "telecom"));
        persons.add(new Person(3,"Rob", "Minneapolis, WWII Heroes str,  25, app.1264", 5300,
                "agriculture"));
    }

    @AfterEach
    public void deleteFile() throws IOException{
        Files.deleteIfExists(Paths.get(TestConstants.TEST_TEXT_FILE));
    }

    @Test
    public void writeDataToConsoleAndFileCorrect() throws IOException{
        writer.setFileWriter(new FileWriter(TestConstants.TEST_TEXT_FILE));
        writer.writeDataToConsoleAndFile(persons);

        assertTrue(Files.exists(Paths.get(TestConstants.TEST_TEXT_FILE)));
    }

    @Test
    public void writeDataToConsoleAndFileIncorrectNullInputData() throws IOException{
        writer.setFileWriter(new FileWriter(TestConstants.TEST_TEXT_FILE));
        writer.writeDataToConsoleAndFile(null);

        assertTrue(Files.readAllBytes(Paths.get(TestConstants.TEST_TEXT_FILE)).length == 0);
    }
}
