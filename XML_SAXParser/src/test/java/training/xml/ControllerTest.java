/*
package training.xml;

import org.junit.jupiter.api.Test;
import training.xml.api.DOMReader;
import training.xml.api.JDOMWriter;


import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class ControllerTest {

    private Controller controller = new Controller();
    private Writer writer = mock(Writer.class);
    private DOMReader domReader = mock(DOMReader.class);
    private JDOMWriter jdomWriter = mock(JDOMWriter.class);
    private List<Person> persons;

    @Test
    public void createXMLFileCorrect() throws IOException{
        doNothing().when(jdomWriter).writeXMLToFile(any(), anyString());
        controller.setXmlWriterAPI(jdomWriter);
        controller.createXMLFile(persons);

        verify(jdomWriter, times(1)).writeXMLToFile(any(), anyString());
    }

    @Test
    public void readDataFromXMLCorrect() throws IOException{
        given(domReader.readDataFromXML(anyString())).willReturn(persons);
        controller.setXmlReaderAPI(domReader);
        controller.readDataFromXML();

        verify(domReader, times(1)).readDataFromXML(anyString());
    }

    @Test
    public void writeDataCorrect() throws IOException{
        doNothing().when(writer).setFileWriter(any());
        doNothing().when(writer).writeDataToConsoleAndFile(any());
        given(domReader.readDataFromXML(anyString())).willReturn(persons);
        controller.setXmlReaderAPI(domReader);
        controller.setWriter(writer);
        controller.writeData();

        verify(writer, times(1)).setFileWriter(any());
        verify(writer, times(1)).writeDataToConsoleAndFile(any());
    }
}
*/
