package training.xml.api;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;
import training.xml.Constants;
import training.xml.Person;

import java.util.ArrayList;
import java.util.List;

public class SAXHandler extends DefaultHandler{

    private List<Person> persons = new ArrayList<>();
    private Person person = null;

    private boolean bName = false;
    private boolean bAddress = false;
    private boolean bCash = false;

    public List<Person> getPersons() {
        return persons;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase(Constants.PERSON)) {
            String id = attributes.getValue(Constants.ID);
            person = new Person();
            person.setId(Integer.parseInt(id));
        } else if (qName.equalsIgnoreCase(Constants.NAME)) {
            bName = true;
        } else if (qName.equalsIgnoreCase(Constants.ADDRESS)) {
            bAddress = true;
        } else if (qName.equalsIgnoreCase(Constants.CASH)) {
            bCash = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase(Constants.PERSON)) {
            persons.add(person);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) {
        if (bName) {
            person.setName(new String(ch, start, length));
            bName = false;
        }
        else if (bAddress) {
            person.setAddress(new String(ch, start, length));
            bAddress = false;
        }
        else if (bCash) {
            person.setCash(Integer.parseInt(new String(ch, start, length)));
            bCash = false;
        }
    }

}
