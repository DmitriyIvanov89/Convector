package ordersparser.consoleprint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ordersparser.model.OrderOut;

public class ConsolePrint {

    ObjectMapper mapper = new ObjectMapper();

    public String printResult(OrderOut messageOut) {
        String parseResult = null;

        try {
            parseResult = mapper.writeValueAsString(messageOut);
            System.out.println(parseResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return parseResult;
    }
}
