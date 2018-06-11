package training.json;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.codehaus.jackson.type.TypeReference;
import org.codehaus.jackson.util.DefaultPrettyPrinter;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonHandler {

    public String getJsonFromUrl(String url){
        if (Objects.nonNull(url)) {
            StringBuilder builder = new StringBuilder();
            try (InputStream inputStream = new URL(url).openStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.defaultCharset()))) {
                String data;
                while ((data = reader.readLine()) != null) {
                    builder.append(data);
                }
            }
            catch (MalformedURLException e) {
                throw new RuntimeException(ExceptionMessages.NOT_URL);
            }
            catch (IOException e) {
                e.printStackTrace(System.err);
            }
            return builder.toString();
        }
        else{
            throw new RuntimeException(ExceptionMessages.NULL_URL);
        }
    }

    public List<Currency> getListOfCurrencies(String url) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(getJsonFromUrl(url), new TypeReference<List<Currency>>() {
            });
        }
        catch(JsonParseException e){
            throw new RuntimeException(ExceptionMessages.NOT_CORRECT_SITE);
        }
    }

    public List<Currency> filterListOfCurrencies(List<Currency> list){
        if (Objects.nonNull(list)) {
            List<Currency> filteredList = new ArrayList<>();
            for (Currency currency : list) {
                if (currency.getCc().equals(Constants.EUR) || currency.getCc().equals(Constants.USD)
                        || currency.getCc().equals(Constants.RUB)) {
                    filteredList.add(currency);
                }
            }
            return filteredList;
        }
        else {
            throw new RuntimeException(ExceptionMessages.NULL_LIST);
        }
    }

    public void writeJsonToFile(List<Currency> list, String fileName) throws IOException{
        if (Objects.nonNull(list) && Objects.nonNull(fileName)) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(new File(fileName), list);
        }
        else {
            throw new RuntimeException(ExceptionMessages.NULL_PARAMETER);
        }
    }

    public List<Currency> readJsonFromFile(String fileName) throws IOException{
            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(new FileReader(fileName), new TypeReference<List<Currency>>() {
                });
            }
            catch(NullPointerException e) {
                throw new RuntimeException(ExceptionMessages.NULL_FILENAME);
            }
            catch(FileNotFoundException e) {
                throw new RuntimeException(ExceptionMessages.FILE_NOT_FOUND);
            }
    }
}
