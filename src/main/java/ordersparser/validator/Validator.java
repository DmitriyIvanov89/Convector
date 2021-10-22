package ordersparser.validator;

import ordersparser.producer.ProducerType;

import java.io.File;


public class Validator {

    public boolean validate(String[] args) {
        for (String path : args) {
            File file = new File(path);
            String fileExtension = file.getName().substring(file.getName().lastIndexOf(".") + 1).toUpperCase();
            if (!file.exists()) {
                System.out.println("File: " + file.getName() + " not found");
                return false;
            }
            if (!fileExtension.equals(ProducerType.CSV.getType()) && !fileExtension.equals(ProducerType.JSONL.getType())) {
                System.out.println("Unknown file extension: " + fileExtension + " " + path);
                return false;
            }
        }

        return true;
    }
}
