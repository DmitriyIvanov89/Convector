package ordersparser.consoleprint;

import com.google.gson.Gson;
import ordersparser.model.OrderOut;


public class ConsolePrint {

    public void printResult(OrderOut orderOut) {

        try {
            System.out.println(new Gson().toJson(orderOut, OrderOut.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
