package ordersparser;

import java.io.File;

public class ArgsValidator {

    public void validateArgs(String[] args) {
        for (String path : args) {
            if (!new File(path).exists()) {
                System.out.println("File: " + path + " not found");
            } else {
                if (!getFileExtension(new File(path))) {
                    System.out.println("Unknown file extension");
                }
            }
        }
    }

    private boolean getFileExtension(File file) {
        String filename = file.getName();
        String fileExtension = filename.substring(filename.lastIndexOf(".") + 1);
        if (filename.lastIndexOf(".") == -1 && filename.lastIndexOf(".") == 0) {
            return false;
        }
        if (fileExtension.equals("csv") || fileExtension.equals("jsonl")) {
            System.out.println(fileExtension);
            return true;
        } else {
            return false;
        }
    }
}
