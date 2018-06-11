package training.xml.api;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import training.xml.Constants;
import training.xml.NullInputDataException;
import training.xml.Person;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class JDOMWriter implements XMLWriterAPI{

    public void writeXMLToFile(List<Person> persons, String fileName) throws IOException {
        XMLOutputter xmlWriter = new XMLOutputter(Format.getPrettyFormat());
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        xmlWriter.output(createXMLDocument(persons), fileOutputStream);
        fileOutputStream.close();
    }

    public Document createXMLDocument(List<Person> persons){
        if (Objects.nonNull(persons)) {
            Document document = new Document();
            document.setRootElement(new Element(Constants.CATALOG));
            Element notebookElement = new Element(Constants.NOTEBOOK);
            document.getRootElement().addContent(notebookElement);
            for (Person person : persons) {
                Element personElement = new Element(Constants.PERSON);
                personElement.setAttribute(Constants.ID, String.valueOf(person.getId()));
                personElement.addContent(new Element(Constants.NAME).setText(person.getName()));
                personElement.addContent(new Element(Constants.ADDRESS).setText(person.getAddress()));
                personElement.addContent(new Element(Constants.CASH).setText(String.valueOf(person.getCash())));
                personElement.addContent(new Element(Constants.EDUCATION).setText(person.getEducation()));
                notebookElement.addContent(personElement);
            }
            return document;
        }
        throw new NullInputDataException();
    }
}
