package ordersparser.validator;

import java.io.File;

public class ArgsValidator {

    public void validateArgs(String[] args) {
        for (String path : args) {
            File file = new File(path);
            if (!file.exists()) {
                System.out.println("File: " + path + " not found");
            } else {
                if (!getFileExtension(file)) {
                    System.out.println("Unknown file extension: " + file.getName());
                }
            }
        }
    }

    private boolean getFileExtension(File file) {
        String fileExtension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if (fileExtension.equals("csv") || fileExtension.equals("jsonl")) {
            return true;
        }
        return false;
    }
}
