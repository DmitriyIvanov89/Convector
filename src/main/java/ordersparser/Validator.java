package ordersparser;

import java.io.File;

public class Validator {

    public static void validateArgs(String[] args) {
        for (String path : args) {
            if (new File(path).exists()) {
                System.out.println("Ok");
            } else {
                System.out.println("file not found");
            }
        }
    }
}
