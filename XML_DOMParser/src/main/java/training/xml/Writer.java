package training.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Writer {

    private FileWriter writer;

    public void setFileWriter(FileWriter writer){
        this.writer = writer;
    }

    public void writeDataToConsoleAndFile(List<Person> persons) throws IOException {
        if (Objects.nonNull(persons)) {
            for (Person person : persons) {
                if (person.getCash() >= Constants.CASH_CONDITION) {
                    System.out.println(person.toString());
                    writer.append(person.toString());
                    writer.append('\n');
                }
            }
        }
        writer.close();
    }
}
