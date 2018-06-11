package training.json;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonHandlerTest {

    private JsonHandler handler = new JsonHandler();
    private static List<Currency> list = new ArrayList<>();

    @BeforeAll
    static void init() throws IOException{
        Files.deleteIfExists(Paths.get(TestConstants.TEST_FILENAME));
        list.add(new Currency("10.06.2018", "0.4234", "643", "RUB", "Російський рубль"));
        list.add(new Currency("10.06.2018", "26.17167", "643", "USD", "Російський рубль"));
        list.add(new Currency("10.06.2018", "26.17167", "643", "EUR", "Російський рубль"));
        list.add(new Currency("10.06.2018", "26.17167", "643", "TNZ", "Російський рубль"));
    }

    @AfterEach
    void deleteFile() throws IOException{
        Files.deleteIfExists(Paths.get(TestConstants.TEST_FILENAME));
    }

    @Test
    void getJsonFromUrlCorrect(){
        String jsonData = handler.getJsonFromUrl(Constants.URL);
        assertTrue(jsonData.contains(Constants.USD) && jsonData.contains(Constants.EUR));
    }

    @Test
    void getJsonFromUrlIncorrectNullUrl(){
        String actuals = null;
        try {
            handler.getJsonFromUrl(null);
        }
        catch (RuntimeException e){
            actuals = e.getMessage();
        }
        assertTrue(ExceptionMessages.NULL_URL.equals(actuals));
    }

    @Test
    void getJsonFromUrlIncorrectNotValidUrl(){
        String jsonData = handler.getJsonFromUrl(TestConstants.GOOGLE);
        assertFalse(jsonData.contains(Constants.USD) && jsonData.contains(Constants.EUR));
    }

    @Test
    void getJsonFromUrlIncorrectNotUrl(){
        String actuals = null;
        try {
            handler.getJsonFromUrl(TestConstants.NOT_URL);
        }
        catch (RuntimeException e){
            actuals = e.getMessage();
        }
        assertTrue(ExceptionMessages.NOT_URL.equals(actuals));
    }

    @Test
    void getListOfCurrenciesCorrect() throws IOException{
        List<Currency> actuals = handler.getListOfCurrencies(Constants.URL);
        assertTrue(!actuals.isEmpty());
    }

    @Test
    void getListOfCurrenciesIncorrectNotValidSite() throws IOException{
        String actuals = null;
        try {
            handler.getListOfCurrencies(TestConstants.GOOGLE);
        }
        catch (RuntimeException e){
            actuals = e.getMessage();
        }
        assertTrue(ExceptionMessages.NOT_CORRECT_SITE.equals(actuals));
    }

    @Test
    void filterListOfCurrenciesCorrect(){
        List<Currency> actuals = handler.filterListOfCurrencies(list);
        assertTrue(actuals.size() == 3);
    }

    @Test
    void filterListOfCurrenciesIncorrectNullList(){
        String actuals = null;
        try {
            handler.filterListOfCurrencies(null);
        }
        catch (RuntimeException e){
            actuals = e.getMessage();
        }
        assertTrue(ExceptionMessages.NULL_LIST.equals(actuals));
    }

    @Test
    void writeJsonToFileCorrect() throws IOException{
        handler.writeJsonToFile(list, TestConstants.TEST_FILENAME);
        assertTrue(Files.exists(Paths.get(TestConstants.TEST_FILENAME)));
    }

    @Test
    void writeJsonToFileIncorrectNullList() throws IOException{
        String actuals = null;
        try {
            handler.writeJsonToFile(null, TestConstants.TEST_FILENAME);
        }
        catch (RuntimeException e){
            actuals = e.getMessage();
        }
        assertTrue(ExceptionMessages.NULL_PARAMETER.equals(actuals));
    }

    @Test
    void writeJsonToFileIncorrectNullFileName() throws IOException{
        String actuals = null;
        try {
            handler.writeJsonToFile(list, null);
        }
        catch (RuntimeException e){
            actuals = e.getMessage();
        }
        assertTrue(ExceptionMessages.NULL_PARAMETER.equals(actuals));
    }

    @Test
    void readJsonFromFileCorrect() throws IOException{
        handler.writeJsonToFile(list, TestConstants.TEST_FILENAME);
        List<Currency> actuals = handler.readJsonFromFile(TestConstants.TEST_FILENAME);
        assertTrue(list.equals(actuals));
    }

    @Test
    void readJsonFromFileIncorrectNullFileName() throws IOException{
        String actuals = null;
        try {
            handler.readJsonFromFile(null);
        }
        catch (RuntimeException e){
            actuals = e.getMessage();
        }
        assertTrue(ExceptionMessages.NULL_FILENAME.equals(actuals));
    }

    @Test
    void readJsonFromFileIncorrectFileNotFound() throws IOException{
        String actuals = null;
        try {
            handler.readJsonFromFile(TestConstants.NOT_EXISTING_FILENAME);
        }
        catch (RuntimeException e){
            actuals = e.getMessage();
        }
        assertTrue(ExceptionMessages.FILE_NOT_FOUND.equals(actuals));
    }
}
