package training.json;

import java.io.IOException;
import java.util.List;

/**
* Creates 1 file in the root folder as a result of successful data processing:
* - somedata.json that is JSON file with filtered currency's information;
*
*/
public class Main {

    public static void main(String[] args) throws IOException{
        JsonHandler handler = new JsonHandler();
        List<Currency> currencyList = handler.getListOfCurrencies(Constants.URL);
        currencyList = handler.filterListOfCurrencies(currencyList);
        handler.writeJsonToFile(currencyList, Constants.JSON_FILE);
        currencyList = handler.readJsonFromFile(Constants.JSON_FILE);
        System.out.println(currencyList);
    }
}
