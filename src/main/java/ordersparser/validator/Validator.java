package ordersparser.validator;

import ordersparser.producer.ProducerType;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Validator {

    public boolean validateArgs(String[] args) {
        for (String path : args) {
            Path filePath = Paths.get(path);
            String fileExtension = path.substring(path.lastIndexOf(".") + 1).toUpperCase();
            if (!Files.exists(filePath)) {
                System.out.printf("File: %s not found.", filePath.getFileName());
                return false;
            }
            if (!fileExtension.equals(ProducerType.CSV.getType()) && !fileExtension.equals(ProducerType.JSONL.getType())) {
                System.out.printf("Unknown file extension: %s on the path: %s", fileExtension, path);
                return false;
            }
        }
        return true;
    }
}
