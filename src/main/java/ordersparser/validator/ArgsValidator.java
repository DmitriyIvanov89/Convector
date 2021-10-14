package ordersparser.validator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ArgsValidator {

    public void validateArgs(String[] args) {
        Map<String, String> files = new HashMap<>();
        for (String path : args) {
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("File: " + path + " not found");
            } else {
                if (!getFileExtension(file)) {
                    System.out.println("Unknown file extension: " + file.getName());
                }
            }
            // передача в приложение прошедших валидацию файлов

        }
    }

    private boolean getFileExtension(File file) {
        String fileExtension = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
        if (fileExtension.equals("csv") || fileExtension.equals("jsonl")) {
            return true;
        }
        return false;
    }
}
