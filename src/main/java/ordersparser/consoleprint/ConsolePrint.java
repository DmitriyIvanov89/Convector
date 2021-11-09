package ordersparser.consoleprint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ordersparser.model.OrderOut;

public class ConsolePrint {

    public void printResult(OrderOut messageOut) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String result = mapper.writeValueAsString(messageOut);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
